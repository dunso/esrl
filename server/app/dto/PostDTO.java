package dto;

import enums.PostStatus;
import models.Comment;
import models.Post;
import play.data.validation.Constraints;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class PostDTO {

    public Long id;

    @Constraints.MaxLength(value = 255, message = "*标题最多255个字符")
    @Constraints.Required(message = "*标题不能为空")
    private String title;

    @Constraints.Required(message = "*内容不能为空")
    private String content;

    private String postAbstract;

    private PostStatus postStatus;

    private LocalDateTime lastModifyTime;

    private String nickName;

    private List<String> categories;

    private List<CommentDTO> comments;

    private Long commentCount;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String content,
                   String postAbstract, PostStatus postStatus, LocalDateTime lastModifyTime,
                   String nickName, List<String> categories, List<CommentDTO> comments, long commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postAbstract = postAbstract;
        this.postStatus = postStatus;
        this.lastModifyTime = lastModifyTime;
        this.nickName = nickName;
        this.categories = categories;
        this.comments = comments;
        this.commentCount = commentCount;
    }

    public PostDTO(Post post, boolean isContrainComments) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postAbstract = post.getPostAbstract();
        this.postStatus = post.getPostStatus();
        this.lastModifyTime = post.getLastModifyTime();
        this.nickName = post.getUser().getNickName();
        this.categories = post.getCategories();
        this.commentCount = post.getCommentCount();

        if(isContrainComments){
            post.getComments().stream().forEach(comment -> this.comments.add(new CommentDTO(comment)));
        }

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

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}
