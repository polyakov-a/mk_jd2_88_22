package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserStorage implements IUserStorage {

    private static final IUserStorage instance = new MemoryUserStorage();
    private List<User> users;

    private MemoryUserStorage() {
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
    public boolean ifUserExists(String login) {
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

    public static IUserStorage getInstance() {
        return instance;
    }
}