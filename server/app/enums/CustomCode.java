package enums;

public enum CustomCode {

    SUCCESS(20000, "Success"),
    EMAIL_ALREADY_USED(40001, "Email has been registered"),
    NICKNAME_ALREADY_EXIST(40002, "Nickname already exists"),
    FAILED_REGISTER(40003, "Register failed"),
    FAILED_SIGNIN(40004,"Email or password is error"),
    FORBIDDEN(40300, "Forbidden"),
    ARTICLE_NOT_FOUND(40401, "Article is not found");

    private int code;
    private String msg;

    CustomCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
