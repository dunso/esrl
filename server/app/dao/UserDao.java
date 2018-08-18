package dao;


import com.google.inject.ImplementedBy;
import dao.impl.UserDaoImpl;
import models.User;

import java.util.Optional;

@ImplementedBy(UserDaoImpl.class)
public interface UserDao {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByNickName(String nickName);

    boolean save(User user);

}
