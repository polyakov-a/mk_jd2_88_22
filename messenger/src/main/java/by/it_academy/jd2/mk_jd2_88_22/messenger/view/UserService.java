package by.it_academy.jd2.mk_jd2_88_22.messenger.view;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.ChoiceFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserService;

import java.util.List;

public class UserService implements IUserService {

    private static final UserService instance = new UserService();
    private final IUserStorage userStorage;

    private UserService() {
        this.userStorage = ChoiceFactoryStorage.getInstance().getUserStorage();
    }

    @Override
    public void signUp(User user) {
        this.userStorage.createUser(user);
    }

    @Override
    public List<User> getAll() {
        return this.userStorage.getUsers();
    }

    @Override
    public boolean ifUserExists(String login) {
        return this.userStorage.ifUserExists(login);
    }

    @Override
    public boolean isPasswordCorrect(String login, String password) {
        return this.userStorage.isPasswordCorrect(login, password);
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userStorage.getUserByLogin(login);
    }

    public static UserService getInstance() {
        return instance;
    }
}
