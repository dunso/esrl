package dto;

import enums.CustomCode;

public class Response<T> {

    private int code;
    private String msg;
    private T data;

    Response(CustomCode customCode) {
        this.code = customCode.getCode();
        this.msg = customCode.getMsg();
    }

    Response(CustomCode customCode, T data) {
        this(customCode);
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return new Response(CustomCode.SUCCESS, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
