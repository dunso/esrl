package models;

import enums.BrowserType;
import enums.OSType;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment extends Model {

    @Id
    public Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Constraints.Required
    public String content;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private BrowserType browserType;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private OSType osType;

    @Column(length = 15)
    @Constraints.MaxLength(15)
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

    public Comment(String content, Post post, User user) {
        this.content = content;
        this.post = post;
        this.user = user;
    }

}


