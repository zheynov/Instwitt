package by.redlaw.service.user.impl;

import by.redlaw.dao.user.UserDao;
import by.redlaw.dto.UserDTO;
import by.redlaw.entity.UserEntity;
import by.redlaw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Created by Walder on 10.09.2016.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserEntity getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    @Transactional
    public void createUser(UserEntity userEntity) {
        userDao.createUser(userEntity);
    }

    @Override
    @Transactional
    public void updateUser(UserEntity user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        userDao.deleteUser(userEntity);
    }

    @Override
    @Transactional
    public boolean isPasswpodlCorrect(UserDTO user) {
        return userDao.isPasswpodlCorrect(user);
    }

    @Override
    @Transactional
    public boolean isLoginExists(String login) {
        return userDao.isLoginExists(login);
    }

    @Override
    @Transactional
    public boolean isEmailExists(String email) {
        return userDao.isEmailExists(email);
    }
}
