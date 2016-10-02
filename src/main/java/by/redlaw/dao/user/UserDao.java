package by.redlaw.dao.user;

import by.redlaw.dto.UserDTO;
import by.redlaw.entity.UserEntity;

/**
 * Created by Redlaw on 10.09.2016.
 */

public interface UserDao {

    UserEntity createUser(UserEntity user);

    void updateUser(UserEntity user);

    // удаление юзера
    void deleteUser(UserEntity user);

    // Если логин уже есть в базе
    boolean isLoginExists(String login);

    // Если юзер и его пароль уже есть в базе
    boolean isPasswpodlCorrect(UserDTO user);

    // Если юзер с заданным емейлом уже есть в базе
    boolean isEmailExists(String email);

    // Нахождение юзера по логину
    UserEntity getUserByLogin(String login);

}
