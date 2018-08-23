package controllers;

import annotations.Authenticated;
import com.google.common.base.Strings;
import dto.Response;
import dto.SignInDTO;
import dto.UserDTO;
import enums.CustomCode;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class UserController extends Controller {

    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    private UserService userService;

    @Inject
    private FormFactory formFactory;

    public UserController() {
    }

    public Result signUp() {
        Form<UserDTO> signUpForm = formFactory.form(UserDTO.class).bindFromRequest();
        if (signUpForm.hasErrors()) {
            return badRequest(signUpForm.errorsAsJson());
        }

        UserDTO userDTO = signUpForm.get();

        Optional<UserDTO> existingUser = userService.findUserByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return ok(Json.toJson(new Response<>(CustomCode.EMAIL_ALREADY_USED)));
        }
        existingUser = userService.findUserByNickName(userDTO.getNickName());
        if (existingUser.isPresent()) {
            return ok(Json.toJson(new Response<>(CustomCode.NICKNAME_ALREADY_EXIST)));
        }
        if (userService.saveUser(userDTO)) {
            session().clear();
            session("email", userDTO.getEmail());
            session("nickName", userDTO.getNickName());
            session("photo", String.valueOf(userDTO.getPhoto()));
            return ok(Json.toJson(Response.success()));
        } else {
            return ok(Json.toJson(new Response<>(CustomCode.FAILED_REGISTER)));
        }
    }

    public Result signIn() {
        Form<SignInDTO> signInForm = formFactory.form(SignInDTO.class).bindFromRequest();
        if (signInForm.hasErrors()) {
            return badRequest(signInForm.errorsAsJson());
        }
        return userService.authenticateUser(signInForm.get())
                .map(userDTO -> {
                    session().clear();
                    session("email", userDTO.getEmail());
                    return ok(Json.toJson(Response.success()));
                })
                .orElse(ok(Json.toJson(new Response<>(CustomCode.FAILED_SIGNIN))));
    }

    @Authenticated
    public Result logout() {
        session().clear();
        return ok(Json.toJson(Response.success()));
    }

    public Result isAuthenticated() {
        if (Strings.isNullOrEmpty(session().get("email"))) {
            return unauthorized();
        } else {
            UserDTO userDto = new UserDTO(session().get("email"), session().get("nickName"));
            try {
                userDto.setPhoto(session().get("photo").getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
            return ok(Json.toJson(Response.success(userDto)));
        }
    }
}
