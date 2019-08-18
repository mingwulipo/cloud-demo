package org.study.cloud.common.model;

import lombok.Data;
import org.study.cloud.common.enums.ResultCodeEnum;
import org.study.cloud.common.exception.BizException;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static Result ok() {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return result;
    }

    public static Result okMsg(String msg) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result ok(Object data) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static Result from(ResultCodeEnum codeEnum) {
        Result<Object> result = new Result<>();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getMsg());
        return result;
    }

    public static Result error() {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnum.FAILURE.getCode());
        result.setMsg("error");
        return result;
    }

    public static Result error(String msg) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnum.FAILURE.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result error(int code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(BizException e) {
        Result<Object> result = new Result<>();
        result.setCode(e.getCode());
        result.setMsg(e.getMessage());
        return result;
    }

    public static Result error(int code, String msg, Object data) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
