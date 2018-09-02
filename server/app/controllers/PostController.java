package controllers;

import annotations.Authenticated;
import annotations.PostExistsAndUserIsOwner;
import com.google.common.base.Strings;
import com.typesafe.config.Config;
import dto.PostDTO;
import dto.Response;
import enums.CustomCode;
import enums.PostStatus;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.PostService;
import utils.PostsPager;

import javax.inject.Inject;

public class PostController extends Controller {

    @Inject
    public Config config;

    @Inject
    public FormFactory formFactory;

    @Inject
    public PostService postService;

    public Result getPagePosts(Integer pageSize,
                               Integer currentPage,
                               Integer postStatus) {
        PostsPager postsPager = postService.getPagePosts(
                pageSize, currentPage, postStatus != 0 ? PostStatus.DRAFT : PostStatus.PUBLISH
        );
        return ok(Json.toJson(Response.success(postsPager)));
    }

    public Result getPagePostsByEmail(String email, Integer pageSize, Integer currentPage, Integer postStatus) {
        if (email == null) {
            email = session().get("email");
        }
        PostsPager postsPager = postService.getPagePostsByEmail(
                pageSize, currentPage, postStatus != 0 ? PostStatus.DRAFT : PostStatus.PUBLISH, email
        );
        return ok(Json.toJson(Response.success(postsPager)));
    }

    public Result getPostById(Long id) {
        return postService.getPostById(id)
                .map(post -> ok(Json.toJson(Response.success(post))))
                .orElse(notFound(Json.toJson(new Response(CustomCode.ARTICLE_NOT_FOUND))));
    }

    @Authenticated
    public Result createPost() {
        Form<PostDTO> postForm = formFactory.form(PostDTO.class).bindFromRequest();
        if (postForm.hasErrors()) {
            return badRequest(postForm.errorsAsJson());
        }

        PostDTO postDTO = postForm.get();
        String email = session("email");
        if (Strings.isNullOrEmpty(email)) {
            return forbidden(Json.toJson(Response.forbidden()));
        }

        postService.savePost(postDTO, email);

        return ok(Json.toJson(Response.success()));
    }

    @Authenticated
    @PostExistsAndUserIsOwner
    public Result editPost(Long postId) {
        Form<PostDTO> postForm = formFactory.form(PostDTO.class).bindFromRequest();
        if (postForm.hasErrors()) {
            return badRequest(postForm.errorsAsJson());
        }
        PostDTO postDTO = postForm.get();
        postDTO.id = postId;
        postService.updatePost(postDTO);
        return ok(Json.toJson(Response.success()));
    }

    @Authenticated
    @PostExistsAndUserIsOwner
    public Result deletePost(Long postId) {
        postService.deletePost(postId);
        return ok(Json.toJson(Response.success()));
    }
}
