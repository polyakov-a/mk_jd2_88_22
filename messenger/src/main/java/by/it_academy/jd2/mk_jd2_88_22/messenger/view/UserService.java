package by.it_academy.jd2.mk_jd2_88_22.messenger.view;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserStorage userStorage;

    private UserService(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void signUp(User user) {
        this.userStorage.add(user);
    }

    @Override
    public List<User> getAll() {
        return this.userStorage.getAll();
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
}
