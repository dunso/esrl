package dto;

import play.data.validation.Constraints;

public class UserDTO {

    @Constraints.Email(message = "*不是一个邮箱")
    @Constraints.Required(message = "*请输入邮箱")
    private String email;

    @Constraints.MaxLength(value = 20, message = "*最长20个字符")
    @Constraints.Required(message = "*请输入昵称")
    private String nickName;

    @Constraints.MinLength(value = 6, message = "*密码至少为6位")
    @Constraints.Required(message = "*请输入密码")
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
