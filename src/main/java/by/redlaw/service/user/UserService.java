package by.redlaw.service.user;

import by.redlaw.dto.UserDTO;
import by.redlaw.entity.UserEntity;

/**
 * Created by Redlaw on 10.09.2016.
 */

public interface UserService {

    UserEntity getUserByLogin(String login);

    void createUser(UserEntity profile);

    void updateUser(UserEntity user);

    void deleteUser(UserEntity profile);

    boolean isPasswpodlCorrect(UserDTO user);

    boolean isLoginExists(String login);

    boolean isEmailExists(String email);
}
