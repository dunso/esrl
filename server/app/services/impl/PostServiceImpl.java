package services.impl;

import com.google.common.base.Strings;
import com.typesafe.config.Config;
import dao.PostDao;
import dao.UserDao;
import dto.PostDTO;
import enums.PostStatus;
import models.Post;
import services.PostService;
import utils.Constants;
import utils.PostsPager;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

public class PostServiceImpl implements PostService {

    @Inject
    private Config config;

    @Inject
    private PostDao postDao;

    @Inject
    private UserDao userDao;


    @Override
    public PostsPager getPagePosts(int pageSize, int currentPage, PostStatus postStatus) {

        pageSize = pageSize < 1 ? Constants.PAGE_SIZE : pageSize;
        currentPage = currentPage < 1 ? 1 : currentPage;
        return new PostsPager(postDao.getPagePosts(pageSize, currentPage, postStatus));
    }

    @Override
    public PostsPager getPagePostsByEmail(int pageSize, int currentPage, PostStatus postStatus, String email) {

        pageSize = pageSize < 1 ? Constants.PAGE_SIZE : pageSize;
        currentPage = currentPage < 1 ? 1 : currentPage;
        return new PostsPager(postDao.getPagePostsByEmail(pageSize, currentPage, email, postStatus));
    }

    @Override
    public Optional<PostDTO> getPostById(Long postId) {
        return postDao.getPostById(postId)
                .map(post -> new PostDTO(post, true));
    }

    @Override
    public boolean savePost(PostDTO postDTO, String email) {

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());

        if (!Strings.isNullOrEmpty(postDTO.getPostAbstract())) {
            post.setPostAbstract(postDTO.getPostAbstract());
        } else {
            int postContentLength = postDTO.getContent().length();
            post.setPostAbstract(postDTO.getContent().substring(0, postContentLength > Constants.POST_ABSTRACT_MAXLENGTH
                    ? Constants.POST_ABSTRACT_MAXLENGTH : postContentLength)
            );
        }

        post.setPostStatus(PostStatus.PUBLISH.toString().equals(postDTO.getPostStatus()) ? PostStatus.PUBLISH : PostStatus.DRAFT);

        userDao.findUserByEmail(email).ifPresent(post::setUser);

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
                    post.setPostStatus(PostStatus.PUBLISH.toString().equals(postDTO.getPostStatus()) ? PostStatus.PUBLISH : PostStatus.DRAFT);
                    post.setLastModifyTime(LocalDateTime.now());
                    post.setCategories(postDTO.getCategories());
                    return postDao.update(post);
                }).orElse(false);
    }

    @Override
    public void deletePost(Long postId) {
        postDao.delete(postId);
    }

}

