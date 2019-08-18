package org.study.cloud.common.enums;

/**
 * Created by Administrator on 2019\8\17 0017.
 */
public enum ResultCodeEnum {

    SUCCESS(0, "成功"),

    FAILURE(-1, "操作失败"),

    PARAM_ERROR(-2, "参数错误"),
    URI_ABSENT(-20, "请求地址不存在"),
    REQUEST_METHOD_ERROR(-21, "请求方法错误"),
    PARAMETER_TYPE_ERROR(-22, "参数类型错误"),


    //DB错误
    DATA_NOT_EXIST(-30,"数据不存在"),
    QUERY_ERROR(-31,"数据库查询异常"),


    //服务器错误
    SERVER_ERROR(-100, "服务器繁忙，稍后重试"),


    PARAM_INVALID(1, "参数不正确"),

    NO_LOGIN(2, "未登录"),;


    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
