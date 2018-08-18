package controllers;

import annotations.Authenticated;
import com.google.common.base.Strings;
import dto.SignInDTO;
import dto.UserDTO;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import utils.Tools;

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
            return ok(
                    Tools.buildJsonResponse(40001, "该Email已注册过，请使用其它Email注册")
            );
        }
        existingUser = userService.findUserByNickName(userDTO.getNickName());
        if (existingUser.isPresent()) {
            return ok(
                    Tools.buildJsonResponse(40002, "该昵称已被占用，请使用其它昵称")
            );
        }
        if (userService.saveUser(userDTO)) {
            session().clear();
            session("email", userDTO.getEmail());
            session("nickName", userDTO.getNickName());
            session("photo", String.valueOf(userDTO.getPhoto()));
            return ok(Tools.buildJsonResponse(20000, "注册成功"));
        } else {
            return ok(Tools.buildJsonResponse(40003, "注册失败"));
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
                    return ok(Tools.buildJsonResponse(20000, userDTO));
                })
                .orElse(ok(Tools.buildJsonResponse(40400, "邮箱或密码输入错误")));
    }

    @Authenticated
    public Result logout() {
        session().clear();
        return ok(Tools.buildJsonResponse(20000, "注销成功"));
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
            return ok(Tools.buildJsonResponse(20000, userDto));
        }
    }
}
