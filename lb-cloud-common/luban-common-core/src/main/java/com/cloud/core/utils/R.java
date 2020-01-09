package com.cloud.core.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @Author kevins
 * @Description 响应信息主体
 * @Date 3:05 下午 2019/9/21
 **/
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    private int code = 200;
    private String msg;
    private T data;

    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setMsg("操作成功");
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static <T> R<T> error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static <T> R<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
