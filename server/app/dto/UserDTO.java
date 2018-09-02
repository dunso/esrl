package dto;

import play.data.validation.Constraints;

public class UserDTO {

    @Constraints.Required(message = "Please input your email")
    @Constraints.Email(message = "It is not an email")
    private String email;

    @Constraints.MaxLength(value = 20, message = "The nickname must be less than 20 characters")
    @Constraints.Required(message = "Please input your nickname")
    private String nickName;

    @Constraints.MinLength(value = 6, message = "The password must be more then 6 characters")
    @Constraints.MaxLength(value = 255, message = "The password must be less then 255 characters")
    @Constraints.Required(message = "Please input your password")
    private String password;

    private byte[] photo;


    public UserDTO() {
    }

    public UserDTO(String email, String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public UserDTO(String email, String nickName, byte[] photo) {
        this.email = email;
        this.nickName = nickName;
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
