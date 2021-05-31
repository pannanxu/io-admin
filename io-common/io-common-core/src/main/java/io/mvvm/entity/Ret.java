package io.mvvm.entity;

import io.mvvm.enums.RetTypeEnum;

/**
 * @program: io-admin
 * @description: 响应实体
 * @author: Mr. Pan
 * @create: 2021-05-23 21:10
 **/
@SuppressWarnings("unused")
public class Ret<T> {

    private int code;
    private String msg;
    private T data;

    public Ret() {
    }

    public Ret(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Ret(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Ret<T> success() {
        return new Ret<T>().code(RetTypeEnum.SUCCESS.getCode()).msg(RetTypeEnum.SUCCESS.getMsg());
    }

    public static <T> Ret<T> success(T data) {
        return new Ret<T>().code(RetTypeEnum.SUCCESS.getCode()).msg(RetTypeEnum.SUCCESS.getMsg()).data(data);
    }

    public static <T> Ret<T> success(RetTypeEnum type, T data) {
        return new Ret<T>().code(type.getCode()).msg(type.getMsg()).data(data);
    }

    public static <T> Ret<T> fail() {
        return new Ret<T>().code(RetTypeEnum.FAIL.getCode()).msg(RetTypeEnum.FAIL.getMsg());
    }

    public static <T> Ret<T> fail(T data) {
        return new Ret<T>().code(RetTypeEnum.FAIL.getCode()).msg(RetTypeEnum.FAIL.getMsg()).data(data);
    }

    public static <T> Ret<T> fail(RetTypeEnum type, T data) {
        return new Ret<T>().code(type.getCode()).msg(type.getMsg()).data(data);
    }

    public static <T> Ret<T> err() {
        return new Ret<T>().code(RetTypeEnum.ERROR.getCode()).msg(RetTypeEnum.ERROR.getMsg());
    }

    public static <T> Ret<T> err(T data) {
        return new Ret<T>().code(RetTypeEnum.ERROR.getCode()).msg(RetTypeEnum.ERROR.getMsg()).data(data);
    }

    public static <T> Ret<T> err(RetTypeEnum type, T data) {
        return new Ret<T>().code(type.getCode()).msg(type.getMsg()).data(data);
    }

    public static <T> Ret<T> result(boolean res) {
        return res ? success() : fail();
    }

    public static <T> Ret<T> type(RetTypeEnum type) {
        return new Ret<>(type.getCode(), type.getMsg());
    }

    public static <T> Ret<T> type(RetTypeEnum type, T data) {
        return new Ret<T>(type.getCode(), type.getMsg()).data(data);
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    public Ret<T> code(int code) {
        this.code = code;
        return this;
    }

    public Ret<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Ret<T> data(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
