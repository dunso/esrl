package dto;

import play.data.validation.Constraints;

public class SignInDTO {

    @Constraints.Email(message = "*It is not an email")
    @Constraints.Required(message = "*Please input your email")
    private String email;

    @Constraints.MinLength(value = 6, message = "*The password must be more then 6 characters")
    @Constraints.MaxLength(value = 255, message = "*The password must be less then 255 characters")
    @Constraints.Required(message = "*Please input your password")
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
