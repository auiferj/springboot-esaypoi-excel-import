package com.example.demo.common;

/**
 * @author lenovo
 */

public enum ResultCode {
    C200(200, "操作成功"),
    C401(401, "未授权"),
    C403(403, "访问受限"),
    C404(404, "资源未找到"),
    C400(400, "参数列表错误"),
    C500(500, "系统内部错误"),
    C301(301, "资源已被移除"),
    C303(303, "重定向"),
    C501(501, "接口未实现"),
    C429(429, "请求过多被限制"),
    C9999(9999, "业务异常"),
    C415(415, "不支持的数据（媒体类型）");

    private int code;
    private String desc;

    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCode format(String name) {
        ResultCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultCode value = var1[var3];
            if (name.equals(value.toString())) {
                return value;
            }
        }

        return null;
    }

    public static ResultCode formatName(int valKey) {
        ResultCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResultCode value = var1[var3];
            if (valKey == value.getCode()) {
                return value;
            }
        }

        return null;
    }

    public int getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
