package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api.SQLMessengerInitializer;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBUserStorage implements IUserStorage {

    private static final DBUserStorage instance = new DBUserStorage();
    private final IUserAuditStorage userAuditStorage = DBUserAuditStorage.getInstance();
    private final UserConverter converter = new UserConverter();
    private final DataSource dataSource;
    private final String INSERT_USER_SQL = "INSERT INTO " +
            "app.users (login, password, last_name, first_name, middle_name, birthday, dt_reg) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String GET_ALL_USERS_SQL = "SELECT id, login, password, last_name, first_name, middle_name, birthday, dt_reg " +
            "FROM app.users;";
    private final String USER_EXISTS = "SELECT login FROM app.users WHERE login = ?;";
    private final String CORRECT_PASSWORD_SQL = "SELECT login, password FROM app.users WHERE login = ? AND password = ?;";
    private final String GET_USER_BY_LOGIN_SQL = "SELECT id, login, password, last_name, first_name, middle_name, birthday, dt_reg " +
            "FROM app.users WHERE login = ?;";

    public DBUserStorage() {
        this.dataSource = SQLMessengerInitializer.getInstance();
    }

    @Override
    public void add(User user) {
        if (!ifUserExists(user.getLogin())) {
            try (Connection conn = SQLMessengerInitializer.getInstance().getConnection();
                 PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL)) {

                UserEntity entity = this.converter.convertToEntity(user);
                ps.setString(1, entity.getLogin());
                ps.setString(2, entity.getPassword());
                ps.setString(3, entity.getLastName());
                ps.setString(4, entity.getFirstName());
                ps.setString(5, entity.getMiddleName());
                ps.setObject(6, entity.getBirthday());
                ps.setObject(7, LocalDateTime.now());
                ps.execute();

                UserAudit audit = UserAudit.Builder.build()
                        .setDt_create(LocalDateTime.now())
                        .setText("User registered")
                        .setUser(user)
                        .setAuthor(null)
                        .createUserAudit();
                this.userAuditStorage.add(audit);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("User with login " + user.getLogin() + " already exists");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = SQLMessengerInitializer.getInstance().getConnection();
             Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_USERS_SQL)) {
            while (rs.next()) {
                UserEntity entity = UserEntity.Builder.build()
                        .setLogin(rs.getString(2))
                        .setPassword(rs.getString(3))
                        .setLastName(rs.getString(4))
                        .setFirstName(rs.getString(5))
                        .setMiddleName(rs.getString(6))
                        .setBirthday(rs.getDate(7).toLocalDate())
                        .setRegistration(rs.getTimestamp(8).toLocalDateTime())
                        .createUserEntity();
                entity.setId(rs.getInt(1));
                User user = this.converter.convertToDTO(entity);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean ifUserExists(String login) {
        boolean isUserExists = false;
        try (Connection conn = SQLMessengerInitializer.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(USER_EXISTS)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                isUserExists = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUserExists;
    }

    @Override
    public boolean isPasswordCorrect(String login, String password) {
        boolean isPasswordCorrect = false;
        try (Connection conn = SQLMessengerInitializer.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(CORRECT_PASSWORD_SQL)) {

            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                isPasswordCorrect = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isPasswordCorrect;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Connection conn = SQLMessengerInitializer.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_USER_BY_LOGIN_SQL)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserEntity entity = UserEntity.Builder.build()
                        .setLogin(rs.getString(2))
                        .setPassword(rs.getString(3))
                        .setLastName(rs.getString(4))
                        .setFirstName(rs.getString(5))
                        .setMiddleName(rs.getString(6))
                        .setBirthday(rs.getDate(7).toLocalDate())
                        .setRegistration(rs.getTimestamp(8).toLocalDateTime())
                        .createUserEntity();
                entity.setId(rs.getInt(1));
                user = this.converter.convertToDTO(entity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public static DBUserStorage getInstance() {
        return instance;
    }
}
