package services;

import com.google.inject.ImplementedBy;
import dto.PostDTO;
import enums.PostStatus;
import models.Post;
import utils.PostsPager;

import java.util.Optional;

@ImplementedBy(PostService.class)
public interface PostService {

    PostsPager getPagePosts(int pageSize, int currentPage, PostStatus postStatus);

    PostsPager getPagePostsByNickName(int pageSize, int currentPage, PostStatus postStatus, String nickName);

    Optional<PostDTO> getPostById(Long postId);

    boolean savePost(PostDTO postDTO, String email);

    boolean updatePost(PostDTO postDTO);

    void deletePost(Long postId);

    PostDTO convertToDTO(Post post);
}
