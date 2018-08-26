package dao.impl;

import dao.PostDao;
import dao.UserDao;
import enums.PostStatus;
import io.ebean.ExpressionList;
import io.ebean.PagedList;
import models.Post;
import play.Logger;

import javax.inject.Inject;
import java.util.Optional;

public class PostDaoImpl implements PostDao {

    @Inject
    private UserDao userDao;

    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Override
    public PagedList<Post> getPagePosts(int pageSize, int currentPage, PostStatus postStatus) {
        ExpressionList<Post> expressionList = Post.find.query().where();
        if (postStatus != null) {
            expressionList = expressionList.eq("post_status", postStatus);
        }
        return expressionList
                .orderBy("create_time desc")
                .setFirstRow(pageSize * (currentPage - 1))
                .setMaxRows(pageSize)
                .findPagedList();
    }

    @Override
    public PagedList<Post> getPagePostsByEmail(int pageSize, int currentPage, String email, PostStatus postStatus) {
        ExpressionList<Post> expressionList = Post.find.query().where();
        if (postStatus != null) {
            expressionList = expressionList.eq("post_status", postStatus);
        }
        return expressionList
                .eq("user_id", userDao.findUserByEmail(email).map(u -> u.getId()))
                .orderBy("create_time desc")
                .setFirstRow(pageSize * (currentPage - 1))
                .setMaxRows(pageSize)
                .findPagedList();
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return Post.find.query().where()
                .eq("id", postId)
                .findOneOrEmpty();
    }

    @Override
    public boolean save(Post post) {
        try {
            post.save();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Post post) {
        try {
            post.update();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public void delete(Long postId) {
        Post.find.deleteById(postId);
    }
}
