package com.wp.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "返回JSON数据模型")
public class JsonResult<T> implements Serializable {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int ERROR = 2;

    @ApiModelProperty(name = "状态", example = "0=成功;1=失败;2=错误")
    private int state;

    @ApiModelProperty(name = "数据", example = "[{},{}]")
    private T data;

    @ApiModelProperty(name = "信息", example = "用户名或密码错误")
    private String message = "";

    public JsonResult() {}
    public JsonResult(int state, T data, String message) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResult success(T data, String message) {
        return new JsonResult(SUCCESS, data, message);
    }

    public static <T> JsonResult fail(T data, String message) {
        return new JsonResult(FAIL, data, message);
    }

    public static <T> JsonResult error(T data, String message) {
        return new JsonResult(ERROR, data, message);
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
