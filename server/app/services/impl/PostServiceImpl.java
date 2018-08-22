package services.impl;

import com.typesafe.config.Config;
import dao.PostDao;
import dao.UserDao;
import dto.PostDTO;
import enums.PostStatus;
import models.Post;
import services.PostService;
import utils.PostsPager;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

public class PostServiceImpl implements PostService {

    @Inject
    private static Config config;

    @Inject
    private PostDao postDao;

    @Inject
    private UserDao userDao;

    private static final int defaultPageSize = config.getInt("play.pager.post.pageSize");


    @Override
    public PostsPager getPagePosts(int pageSize, int currentPage, PostStatus postStatus) {

        pageSize = pageSize < 1 ? defaultPageSize : pageSize;
        currentPage = currentPage < 1 ? 1 : currentPage;
        return new PostsPager(postDao.getPagePosts(pageSize, currentPage, postStatus));
    }

    @Override
    public PostsPager getPagePostsByNickName(int pageSize, int currentPage, PostStatus postStatus, String nickName) {

        pageSize = pageSize < 1 ? defaultPageSize : pageSize;
        currentPage = currentPage < 1 ? 1 : currentPage;
        return new PostsPager(postDao.getPagePostsByNickName(pageSize, currentPage, nickName, postStatus));
    }

    @Override
    public Optional<PostDTO> getPostById(Long postId) {
        return postDao.getPostById(postId)
                .map(this::convertToDTO);
    }

    @Override
    public boolean savePost(PostDTO postDTO) {

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setPostAbstract(postDTO.getPostAbstract());
        post.setPostStatus(postDTO.getPostStatus());

        LocalDateTime now = LocalDateTime.now();

        post.setCreateTime(now);
        post.setLastModifyTime(now);

        post.setUser(userDao.findUserByNickName(postDTO.getNickName()).orElse(null));
        post.setCategories(postDTO.getCategories());
        post.setCommentCount(0L);

        return postDao.save(post);
    }

    @Override
    public boolean updatePost(PostDTO postDTO) {
        return postDao.getPostById(postDTO.id)
                .map(post -> {
                    post.setTitle(postDTO.getTitle());
                    post.setContent(postDTO.getContent());
                    post.setPostAbstract(postDTO.getPostAbstract());
                    post.setPostStatus(postDTO.getPostStatus());
                    post.setLastModifyTime(LocalDateTime.now());
                    post.setCategories(postDTO.getCategories());
                    return postDao.update(post);
                }).orElse(false);
    }

    @Override
    public void deletePost(Long postId) {
        postDao.delete(postId);
    }

    @Override
    public PostDTO convertToDTO(Post post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getPostAbstract(),
                post.getPostStatus(),
                post.getLastModifyTime(),
                post.getUser().getNickName(),
                post.getCategories(),
                post.getCommentCount()
        );
    }

}

