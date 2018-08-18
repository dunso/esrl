package services.impl;

import dao.UserDao;
import dto.SignInDTO;
import dto.UserDTO;
import enums.Role;
import models.User;
import play.Logger;
import services.UserService;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Inject
    private UserDao userDao;


    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        return userDao.findUserByEmail(email)
                .map(this::convertToDTO);
    }

    @Override
    public Optional<UserDTO> findUserByNickName(String nickName) {
        return userDao.findUserByNickName(nickName)
                .map(this::convertToDTO);
    }

    @Override
    public boolean authenticateUser(SignInDTO signInDTO) {
        return userDao.findUserByEmail(signInDTO.getEmail())
                .map(user -> user.getPassword().equals(signInDTO.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setNickName(userDTO.getNickName());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.CONTRIBUTOR);
        user.setRegisterTime(LocalDateTime.now());
        user.setLastModifyTime(LocalDateTime.now());
        return userDao.save(user);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getEmail(), user.getNickName(), user.getPhoto());
    }
}
