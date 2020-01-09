package com.cloud.file.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.base.entity.SysUser;
import com.cloud.base.vo.SysUserImportVo;
import com.cloud.core.exception.BaseException;
import com.cloud.core.exception.CheckedException;
import com.cloud.core.utils.MmcUtil;
import com.cloud.core.utils.RSAUtil;
import com.cloud.file.handler.SysUserExcelHandler;
import com.cloud.file.mapper.UserMapper;
import com.cloud.file.service.IDeptService;
import com.cloud.file.service.IJobService;
import com.cloud.file.service.IPersonnelImport;
import com.cloud.file.utils.DictUtils;
import com.cloud.file.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName PersonnelImportImpl
 * @Description: TODO
 * @Author kevins
 * @Date 2019/10/10
 * @Version V1.0
 **/
@Service
@Slf4j
public class PersonnelImportImpl extends ServiceImpl<UserMapper, SysUser> implements IPersonnelImport {
    private static final String passwordEncoder = new BCryptPasswordEncoder().encode("123456");
    /**
     * EXCEL2003后缀名
     */
    private final static String EXCEL_VERSION_2003_SUFFIX = "xls";

    @Autowired
    private IJobService iJobService;
    @Autowired
    private IDeptService iDeptService;
    @Autowired
    SysUserExcelHandler sysUserExcelHandler;
    @Autowired
    DictUtils dictUtils;
    @Autowired
    UserUtils userUtils;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<SysUserImportVo> personnelExcelImport(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (EXCEL_VERSION_2003_SUFFIX.equals(suffix)) {
            throw new CheckedException("文件格式不正确或选择使用2007版excel");
        }
        ImportParams importParams = new ImportParams();
        // 数据处理
        sysUserExcelHandler.setNeedHandlerFields(new String[]{"警衔", "人员类型", "政治面貌", "民族"});// 注意这里对应的是excel的列名。也就是对象上指定的列名。
        importParams.setDataHanlder(sysUserExcelHandler);
        // 需要验证
        importParams.setNeedVerfiy(true);
        String[] fieldsName = sysUserExcelHandler.getNeedHandlerFields();
        List<String> listStrings = Stream.of(fieldsName).collect(Collectors.toList());
        try {
            ExcelImportResult<SysUserImportVo> result = ExcelImportUtil.importExcelMore(file.getInputStream(), SysUserImportVo.class,
                    importParams);
            List<SysUserImportVo> userList = result.getList();
            List<SysUserImportVo> failList = result.getFailList();
            //RSA验证
            if (!RSAUtil.userVerifySign()) {
                throw new BaseException("签名错误");
            }
            int topLimit = RSAUtil.userDecode();
            //目前人员数量
            int count = this.count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getDelFlag, 0));
            //系统容量大于等于最低限制
            if (count + userList.size() >= topLimit) {
                throw new BaseException("人员数量已到达上限");
            }

            //如果有错误信息将错误信息返回，终止导入。
            if (failList.size() != 0) {
                return failList;
            }
            //校验重复人员，把重复人员返回返回，终止导入。
            List<SysUserImportVo> allList = new ArrayList<>();
            allList.addAll(userList);
            allList.addAll(failList);
            //已存在所有证件号码
            HashSet<String> iPersonnelList = userUtils.getIPersonnel();
            if (iPersonnelList.size() != 0) {
                List<SysUserImportVo> filterList = allList.stream().filter(a -> iPersonnelList.contains(a.getIdentificationNumber())).collect(Collectors.toList());
                if (filterList.size() != 0) {
                    return filterList;
                }
            }
            List<SysUser> list = new ArrayList<>();
            for (SysUserImportVo user : userList) {
                SysUser sysUser = new SysUser();
                BeanUtils.copyProperties(user, sysUser);
                sysUser.setDeptId(getDepIdByLevelName(user.getDeptName()));
                sysUser.setPassword(passwordEncoder);
                sysUser.setJobId(iJobService.selectJobIdByJobName(user.getJobName()));
                sysUser.setUsername(MmcUtil.ToPinyin(user.getName()));
                sysUser.setPoliceRank(dictUtils.getSysDictItem().get(listStrings.get(0)).stream().filter(a -> a.getItemValue().equals(user.getPoliceRank())).collect(Collectors.toList()).get(0).getId());//警衔
                sysUser.setType(dictUtils.getSysDictItem().get(listStrings.get(1)).stream().filter(a -> a.getItemValue().equals(user.getType())).collect(Collectors.toList()).get(0).getId());//人员类型
                sysUser.setPoliticalOutlook(dictUtils.getSysDictItem().get(listStrings.get(2)).stream().filter(a -> a.getItemValue().equals(user.getPoliticalOutlook())).collect(Collectors.toList()).get(0).getId());//政治面貌
                sysUser.setNation(dictUtils.getSysDictItem().get(listStrings.get(3)).stream().filter(a -> a.getItemValue().equals(user.getNation())).collect(Collectors.toList()).get(0).getId());//民族
                list.add(sysUser);
            }
            log.info("从Excel成功导入数据一共 {} 行 ", userList.size());
            this.saveBatch(list);
            return failList;

        } catch (IOException e) {
            log.error("导入失败：{}", e.getMessage());
            throw new BaseException("请检查填写内容，检查是否有空格,或检查文件填写是否规范。" + e);
        } catch (Exception e1) {
            e1.printStackTrace();
            log.error("导入失败：{}", e1.getMessage());
            throw new BaseException("请检查填写内容，检查是否有空格,或检查文件填写是否规范。" + e1);

        }
    }

    @Override
    public List<SysUser> findIdentification() {
        return this.list(Wrappers.<SysUser>lambdaQuery().select(SysUser::getIdentificationNumber).eq(SysUser::getDelFlag, 0));
    }


    /**
     * @return java.lang.Integer
     * @Author kevins
     * @Description 根据部门层级名字获取最后一层部门id
     * @Date 5:07 下午 2019/10/10
     * @Param [levelName]
     **/
    private Integer getDepIdByLevelName(String levelName) {
        boolean child = levelName.contains("/");
        if (child) {
            String[] names = levelName.split("/");
            List<String> list = Stream.of(names).collect(Collectors.toList());
            Integer id = 0;
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    id = iDeptService.selectIdByParentIdName(0, list.get(i));
                } else {
                    id = iDeptService.selectIdByParentIdName(id, list.get(i));
                }
            }
            return id;
        } else {
            return iDeptService.selectIdByParentIdName(0, levelName);
        }
    }
}

