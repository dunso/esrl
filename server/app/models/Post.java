package models;

import enums.PostStatus;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.UpdatedTimestamp;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
public class Post extends Model {

    @Id
    private Long id;

    @Column(nullable = false)
    @Constraints.MaxLength(255)
    @Constraints.Required
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Constraints.Required
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String postAbstract;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Column(nullable = false, insertable = false, updatable = false)
    @CreatedTimestamp
    private LocalDateTime createTime;

    @Column(nullable = false)
    @UpdatedTimestamp
    private LocalDateTime lastModifyTime;

    @Column(nullable = false)
    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<String> catalogies;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Comment> comments;

    private Long commentCount;

    public static final Finder<Long, Post> find = new Finder<>(Post.class);

    public Post(){}

    public Post( String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostAbstract() {
        return postAbstract;
    }

    public void setPostAbstract(String postAbstract) {
        this.postAbstract = postAbstract;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCatalogies() {
        return catalogies;
    }

    public void setCatalogies(List<String> catalogies) {
        this.catalogies = catalogies;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}