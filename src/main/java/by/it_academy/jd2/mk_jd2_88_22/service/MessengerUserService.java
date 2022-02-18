package by.it_academy.jd2.mk_jd2_88_22.service;

import by.it_academy.jd2.mk_jd2_88_22.service.api.IUserService;
import by.it_academy.jd2.mk_jd2_88_22.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessengerUserService implements IUserService<User> {

    private static final IUserService<User> instance = new MessengerUserService();
    private List<User> users;

    private MessengerUserService() {
        this.users = new ArrayList<>();
    }
    @Override
    public void createUser(User user) {
        Optional<User> first = this.users.stream().filter(u -> u.getLogin().equals(user.getLogin())).findFirst();
        if (first.isEmpty()) {
            this.users.add(user);
        }
    }

    @Override
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public boolean isUserExists(String login) {
        boolean result = false;
        Optional<User> first = this.users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
        if (first.isPresent()) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean isPasswordCorrect(String login, String password) {
        boolean result = false;
        Optional<User> first = this.users.stream().filter(u -> u.getLogin().equals(login))
                .filter(u -> u.getPassword().equals(password))
                .findFirst();
        if (first.isPresent()) {
            result = true;
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        Optional<User> first = this.users.stream().filter(u -> u.getLogin().equals(login)).findFirst();
        if (first.isPresent()) {
            user = first.get();
        }
        return user;
    }

    public static IUserService<User> getInstance() {
        return instance;
    }
}
