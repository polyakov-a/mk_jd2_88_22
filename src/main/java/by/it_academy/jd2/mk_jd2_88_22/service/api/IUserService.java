package by.it_academy.jd2.mk_jd2_88_22.service.api;

import java.util.List;

public interface IUserService<T> {

    public void createUser(T user);

    public List<T> getUsers();

    boolean isUserExists(String login);

    boolean isPasswordCorrect(String login, String password);

    T getUserByLogin(String login);
}
