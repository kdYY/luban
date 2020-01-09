package com.cloud.core.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.base.entity.SysDept;
import com.cloud.base.entity.SysMenu;
import com.cloud.base.vo.DeptTreeVo;
import com.cloud.base.vo.MenuTreeVo;
import com.cloud.base.vo.SysDeptTreeVo;
import lombok.experimental.UtilityClass;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName MmcUtil
 * @Description: TODO
 * @Author kevins
 * @Date 2019/9/21
 * @Version V1.0
 **/
@UtilityClass
public class MmcUtil {
    /**
     * 遍历菜单
     *
     * @param menuList
     * @param menus
     * @param menuType
     */
    public void findChildren(List<SysMenu> menuList, List<SysMenu> menus, int menuType) {
        for (SysMenu sysMenu : menuList) {
            sysMenu.setKey(sysMenu.getMenuId());
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if (menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if (sysMenu.getMenuId() != null && sysMenu.getMenuId().equals(menu.getParentId())) {
                    menu.setParentName(sysMenu.getName());
                    menu.setLevel(sysMenu.getLevel() + 1);
                    menu.setKey(menu.getMenuId());
                    if (exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            sysMenu.setChildren(children);
            children.sort(Comparator.comparing(SysMenu::getSort));
            findChildren(children, menus, menuType);
        }
    }

    public void findChildren1(List<MenuTreeVo> menuList, List<SysMenu> menus, int menuType) {
        for (MenuTreeVo sysMenu : menuList) {
            List<SysMenu> children = new ArrayList<>();
            List<MenuTreeVo> children1 = new ArrayList<>();
            for (SysMenu menu : menus) {
                MenuTreeVo menuTreeVo = new MenuTreeVo();
                BeanUtil.copyProperties(menu, menuTreeVo);
                menuTreeVo.setTitle(menu.getName());
                menuTreeVo.setKey(menu.getMenuId());
                menuTreeVo.setValue(String.valueOf(menu.getMenuId()));
                if (menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue;
                }
                if (sysMenu.getMenuId() != null && sysMenu.getMenuId().equals(menu.getParentId())) {
                    menuTreeVo.setParentName(sysMenu.getName());
                    menuTreeVo.setLevel(sysMenu.getLevel() + 1);
                    menuTreeVo.setTitle(menu.getName());
                    menuTreeVo.setKey(menu.getMenuId());
                    menuTreeVo.setValue(String.valueOf(menu.getMenuId()));
                    if (exists(children, menu)) {
                        children1.add(menuTreeVo);
                    }
                }
            }
            sysMenu.setChildren(children1);
            children.sort(Comparator.comparing(SysMenu::getSort));
            findChildren1(children1, menus, menuType);
        }
    }

    /**
     * 构建部门tree
     *
     * @param sysDepts
     * @param depts
     */
    public void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {

        for (SysDept sysDept : sysDepts) {
            DeptTreeVo deptTreeVo = new DeptTreeVo();
            deptTreeVo.setKey(sysDept.getDeptId());
            deptTreeVo.setValue(String.valueOf(sysDept.getDeptId()));
            deptTreeVo.setTitle(sysDept.getName());
            List<SysDept> children = new ArrayList<>();
            List<DeptTreeVo> children1 = new ArrayList<>();
            for (SysDept dept : depts) {
                if (sysDept.getDeptId() != null && sysDept.getDeptId().equals(dept.getParentId())) {
                    dept.setParentName(sysDept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    DeptTreeVo deptTreeVo1 = new DeptTreeVo();
                    deptTreeVo1.setTitle(dept.getName());
                    deptTreeVo1.setKey(dept.getDeptId());
                    deptTreeVo1.setValue(String.valueOf(dept.getDeptId()));
                    children.add(dept);
                    children1.add(deptTreeVo1);
                }
            }
            sysDept.setChildren(children);
            deptTreeVo.setChildren(children1);
            findChildren(children, depts);
        }
    }


    public void findDeptTreeChildren(List<SysDeptTreeVo> sysDepts, List<SysDept> depts) {

        for (SysDeptTreeVo sysDept : sysDepts) {
            sysDept.setKey(sysDept.getDeptId());
            sysDept.setValue(String.valueOf(sysDept.getDeptId()));
            sysDept.setTitle(sysDept.getName());
            List<SysDeptTreeVo> children = new ArrayList<>();
            for (SysDept dept : depts) {
                SysDeptTreeVo sysDeptTreeVo = new SysDeptTreeVo();
                BeanUtil.copyProperties(dept, sysDeptTreeVo);
                if (sysDept.getDeptId() != null && sysDept.getDeptId().equals(dept.getParentId())) {
                    sysDeptTreeVo.setParentName(sysDept.getName());
                    sysDeptTreeVo.setLevel(sysDept.getLevel() + 1);

                    DeptTreeVo deptTreeVo1 = new DeptTreeVo();
                    deptTreeVo1.setTitle(dept.getName());
                    deptTreeVo1.setKey(dept.getDeptId());
                    deptTreeVo1.setValue(String.valueOf(dept.getDeptId()));
                    children.add(sysDeptTreeVo);
                }
            }
            sysDept.setChildren(children);
            findDeptTreeChildren(children, depts);
        }
    }


    /**
     * 判断菜单是否存在
     *
     * @param sysMenus
     * @param sysMenu
     * @return
     */
    public boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for (SysMenu menu : sysMenus) {
            if (menu.getMenuId().equals(sysMenu.getMenuId())) {
                exist = true;
            }
        }
        return !exist;
    }


    /**
     * 获取菜单JSON数组
     *
     * @param jsonArray
     * @param metaList
     * @param parentJson
     */
    public void getPermissionJsonArray(JSONArray jsonArray, List<SysMenu> metaList, JSONObject parentJson) {
        for (SysMenu permission : metaList) {
            if (permission.getType() == null) {
                continue;
            }
            Integer tempPid = permission.getParentId();
            JSONObject json = getPermissionJsonObject(permission);
            if (json == null) {
                continue;
            }
            if (parentJson == null && tempPid.equals(0)) {
                jsonArray.add(json);
                getPermissionJsonArray(jsonArray, metaList, json);
            } else if (parentJson != null && tempPid != 0 && tempPid.equals(Integer.parseInt(parentJson.getString("id")))) {
                // 类型( 0：一级菜单 1：子菜单 2：按钮 )
                if (permission.getType().equals(1) || permission.getType().equals(0)) {
                    if (parentJson.containsKey("children")) {
                        parentJson.getJSONArray("children").add(json);
                    } else {
                        JSONArray children = new JSONArray();
                        children.add(json);
                        parentJson.put("children", children);
                    }
                    getPermissionJsonArray(jsonArray, metaList, json);
                }
            }

        }
    }


    public JSONObject getPermissionJsonObject(SysMenu permission) {
        JSONObject json = new JSONObject();
        // 类型(0：一级菜单 1：子菜单 2：按钮)
        if (permission.getType().equals(2)) {
            return null;
        } else if (permission.getType().equals(0) || permission.getType().equals(1)) {
            json.put("id", permission.getMenuId());
            if (isUrl(permission.getPath())) {
                json.put("path", permission.getPath());
            } else {
                json.put("path", permission.getPath());
            }

            // 重要规则：路由name (通过URL生成路由name,路由name供前端开发，页面跳转使用)
            if (StrUtil.isNotEmpty(permission.getComponent())) {
                json.put("name", urlToRouteName(permission.getName()));
            }

            // 是否隐藏路由，默认都是显示的
            if (permission.getHidden()) {
                json.put("hidden", true);
            }
            // 聚合路由
            if (permission.getAlwaysShow()) {
                json.put("alwaysShow", true);
            }

            if (permission.getParentId().equals(0)) {
                //一级目录需要加斜杠，不然访问 会跳转404页面
                json.put("component", StrUtil.isEmpty(permission.getComponent()) ? "Layout" : permission.getComponent());
            } else if (!StrUtil.isEmpty(permission.getComponent())) {
                json.put("component", permission.getComponent());
            }

            JSONObject meta = new JSONObject();
            // 由用户设置是否缓存页面 用布尔值
            if (permission.getKeepAlive()) {
                meta.put("keepAlive", true);
            } else {
                meta.put("keepAlive", false);
            }

            meta.put("title", permission.getName());
            if (permission.getParentId() == 0) {
                // 一级菜单跳转地址
                json.put("redirect", permission.getRedirect());
                if (StrUtil.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            } else {
                if (StrUtil.isNotEmpty(permission.getIcon())) {
                    meta.put("icon", permission.getIcon());
                }
            }
            if (isUrl(permission.getPath())) {
                meta.put("url", permission.getPath());
            }
            json.put("meta", meta);
        }

        return json;
    }

    /**
     * 判断是否外网URL
     *
     * @return
     */
    private boolean isUrl(String url) {
        return url != null && (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("{{"));
    }

    /**
     * 路由名称
     *
     * @param url
     * @return
     */
    private String urlToRouteName(String url) {
        if (StrUtil.isNotEmpty(url)) {
            int i = url.lastIndexOf("/");
            return url.substring(i + 1);
        } else {
            return null;
        }
    }

    /**
     * @return java.lang.String
     * @Author kevins
     * @Description 汉字转拼音
     * @Date 4:23 下午 2019/10/10
     * @Param [chinese]
     **/
    public static String ToPinyin(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }


    public static void main(String[] args) {
//        String aa123456 = encode("Aa123456");
//        System.out.println(aa123456);
    }
}
