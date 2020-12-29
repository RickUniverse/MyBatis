package com.mybatis.entities;

/**
 * @author lijichen
 * @date 2020/12/9 - 19:21
 */
public enum EmpEnum {
    LOGIN(100,"登录状态"),LOGOUT(200,"登出状态"),REMOVE(300,"登出状态");

    private Integer code;
    private String msg;

    EmpEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EmpEnum getEmpEnumByCode(Integer code) {
        switch (code){
            case 100:
                return LOGIN;
            case 200:
                return LOGOUT;
            case 300:
                return REMOVE;
            default:
                return LOGOUT;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
