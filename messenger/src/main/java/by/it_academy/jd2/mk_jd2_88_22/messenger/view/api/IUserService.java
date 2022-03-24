package by.it_academy.jd2.mk_jd2_88_22.messenger.view.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;

import java.util.List;

public interface IUserService {

    void signUp(User user);

    List<User> getAll();

    boolean ifUserExists(String login);

    boolean isPasswordCorrect(String login, String password);

    User getUserByLogin(String login);
}
