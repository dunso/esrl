package services;

import com.google.inject.ImplementedBy;
import dto.SignInDTO;
import dto.UserDTO;
import services.impl.UserServiceImpl;

import java.util.Optional;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {

    Optional<UserDTO> findUserByEmail(String email);

    Optional<UserDTO> findUserByNickName(String nickName);

    boolean authenticateUser(SignInDTO signInDTO);

    boolean saveUser(UserDTO userDTO);

}

