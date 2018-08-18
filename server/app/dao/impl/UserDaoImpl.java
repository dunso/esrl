package dao.impl;

import dao.UserDao;
import models.User;
import play.Logger;

import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final Logger.ALogger logger = Logger.of(this.getClass());

    @Override
    public Optional<User> findUserByEmail(String email) {
        return User.find.query().where()
                .eq("email", email.trim().toLowerCase())
                .findOneOrEmpty();
    }

    @Override
    public Optional<User> findUserByNickName(String nickName) {
        return User.find.query().where()
                .eq("nickName", nickName.trim().toLowerCase())
                .findOneOrEmpty();
    }

    @Override
    public boolean save(User user) {
        try {
            user.save();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}
