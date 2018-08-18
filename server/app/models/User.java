package models;

import enums.Role;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.*;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User extends Model {

    @Id
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @Constraints.MaxLength(50)
    @Constraints.Required
    @Constraints.Email
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    @Constraints.MaxLength(20)
    @Constraints.Required
    private String nickName;

    @Column(nullable = false)
    @Constraints.MaxLength(32)
    @Constraints.Required
    private String password;

    @Column(length = 2048)
    @Lob
    private byte[] photo;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 10)
    private String activationCode;

    @Column(length = 4096)
    private String token;

    @Column(nullable = false, insertable = false, updatable = false)
    @CreatedTimestamp
    private LocalDateTime registerTime;

    @Column(nullable = false)
    @UpdatedTimestamp
    private LocalDateTime lastModifyTime;

    private LocalDateTime lastLoginTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public static final Finder<Long, User> find = new Finder<>(User.class);

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
