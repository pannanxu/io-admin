package io.mvvm.entity;

/**
 * @program: io-admin
 * @description: 响应实体
 * @author: Mr. Pan
 * @create: 2021-05-23 21:10
 **/
public class Ret {

    private int code;
    private String msg;
    private Object data;

    public static Ret ok() {
        return new Ret().setCode(100).setMsg("success");
    }

    public static Ret ok(String msg) {
        return new Ret().setCode(100).setMsg(msg);
    }

    public static Ret okData(Object data) {
        return new Ret().setCode(100).setMsg("success").setData(data);
    }

    public static Ret fail() {
        return new Ret().setCode(101).setMsg("fail");
    }

    public static Ret fail(String msg) {
        return new Ret().setCode(101).setMsg(msg);
    }

    public static Ret err() {
        return new Ret().setCode(102).setMsg("error");
    }

    public static Ret err(String msg) {
        return new Ret().setCode(102).setMsg(msg);
    }

    public boolean isSuccess() {
        return this.code == 100;
    }

    public Ret setCode(int code) {
        this.code = code;
        return this;
    }

    public Ret setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Ret setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
