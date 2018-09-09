package services;

import com.google.inject.ImplementedBy;
import dto.PostDTO;
import enums.PostStatus;
import services.impl.PostServiceImpl;
import utils.Pager;

import java.util.Optional;

@ImplementedBy(PostServiceImpl.class)
public interface PostService {

    Pager getPagePosts(int pageSize, int currentPage, PostStatus postStatus);

    Pager getPagePostsByEmail(int pageSize, int currentPage, PostStatus postStatus, String email);

    Optional<PostDTO> getPostById(Long postId);

    boolean savePost(PostDTO postDTO, String email);

    boolean updatePost(PostDTO postDTO);

    void deletePost(Long postId);
}
