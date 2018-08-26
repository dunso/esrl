package dao;

import com.google.inject.ImplementedBy;
import dao.impl.PostDaoImpl;
import enums.PostStatus;
import io.ebean.PagedList;
import models.Post;

import java.util.Optional;

@ImplementedBy(PostDaoImpl.class)
public interface PostDao {

    PagedList<Post> getPagePosts(int pageSize, int currentPage, PostStatus postStatus);

    PagedList<Post> getPagePostsByEmail(int pageSize, int currentPage, String email, PostStatus postStatus);

    Optional<Post> getPostById(Long postId);

    boolean save(Post post);

    boolean update(Post post);

    void delete(Long postId);
}
