package dto;

import play.data.validation.Constraints;

public class SignInDTO {

    @Constraints.Email(message = "*不是一个邮箱")
    @Constraints.Required(message = "*请输入邮箱")
    private String email;

    @Constraints.MinLength(value = 6, message = "*密码至少为6位")
    @Constraints.Required(message = "*请输入密码")
    private String password;

    public SignInDTO() {}

    public SignInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
