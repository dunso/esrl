package models;

import enums.BrowserType;
import enums.OSType;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @Column(nullable = false)
    public String content;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private BrowserType browserType;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private OSType osType;

    @Column(length = 15)
    private String ip;

    @Column(nullable = false, insertable = false, updatable = false)
    @CreatedTimestamp
    public LocalDateTime createTime;

    @ManyToOne
    @JsonIgnore
    public Post post;

    @ManyToOne
    public User user;

    public static final Finder<Long, Comment> find = new Finder<>(Comment.class);

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
    }

    public OSType getOsType() {
        return osType;
    }

    public void setOsType(OSType osType) {
        this.osType = osType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


