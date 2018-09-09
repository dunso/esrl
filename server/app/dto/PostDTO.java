package dto;

import models.Post;
import play.data.validation.Constraints;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostDTO {

    public Long id;

    @Constraints.MaxLength(value = 255, message = "*The title must be less than 255 characters")
    @Constraints.Required(message = "*Please input title")
    private String title;

    @Constraints.Required(message = "*Please input content")
    private String content;

    private String postAbstract;

    private String postStatus;

    private LocalDateTime lastModifyTime;

    private String email;

    private String nickName;

    private List<CommentDTO> comments;

    private Long commentCount;

    public PostDTO() {
    }

    public PostDTO(Post post, boolean isContainComments) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postAbstract = post.getPostAbstract();
        this.postStatus = post.getPostStatus().toString();
        this.lastModifyTime = post.getLastModifyTime();
        this.email = post.getUser().getEmail();
        this.nickName = post.getUser().getNickName();
        this.commentCount = post.getCommentCount();

        if(isContainComments){
            post.getComments().stream().forEach(comment -> this.comments.add(new CommentDTO(comment)));
        }

    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.postAbstract = post.getPostAbstract();
        this.postStatus = post.getPostStatus().toString();
        this.lastModifyTime = post.getLastModifyTime();
        this.email = post.getUser().getEmail();
        this.nickName = post.getUser().getNickName();
        this.commentCount = post.getCommentCount();
    }

    public static List<PostDTO> toPostDTOList(List<Post> postList) {
        return Optional.ofNullable(postList)
                .map(posts -> posts.stream()
                        .map(PostDTO::new)
                        .collect(Collectors.toList()))
                .orElse(null);
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

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}
