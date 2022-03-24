package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserAuditEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserAuditConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api.SQLMessengerInitializer;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBUserAuditStorage implements IUserAuditStorage {


    private static final DBUserAuditStorage instance = new DBUserAuditStorage();
    private final DataSource dataSource;

    public DBUserAuditStorage() {
        this.dataSource = SQLMessengerInitializer.getInstance();
    }

    @Override
    public void create(UserAudit audit) {
        if (audit == null) {
            throw new IllegalArgumentException("Audit cannot be NULL");
        }
        UserAuditEntity entity = new UserAuditConverter().convertBackward(audit);
        String sql = "INSERT INTO app.users_audit (text, author_login, dt_create, user_login) " +
                     "VALUES (?, ?, ?, ?);";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getText());
            ps.setObject(2, entity.getAuthorLogin() != null ? entity.getAuthorLogin() : null);
            ps.setObject(3, entity.getDtCreate());
            ps.setObject(4, entity.getUserLogin());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void create(UserAudit audit1, UserAudit audit2) {
        if (audit1 == null || audit2 == null) {
            throw new IllegalArgumentException("Audit cannot be NULL");
        }
        UserAuditEntity entity1 = new UserAuditConverter().convertBackward(audit1);
        UserAuditEntity entity2 = new UserAuditConverter().convertBackward(audit2);

        String sql = "INSERT INTO app.users_audit " +
                     "(text, author_login, dt_create, user_login) " +
                     "VALUES (?, ?, ?, ?);" +
                     "INSERT INTO app.users_audit " +
                     "(text, author_login, dt_create, user_login) " +
                     "VALUES (?, ?, ?, ?);";

        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            ps.setString(1, entity1.getText());
            ps.setObject(2, entity1.getAuthorLogin() != null ? entity1.getAuthorLogin() : null);
            ps.setObject(3, entity1.getDtCreate());
            ps.setObject(4, entity1.getUserLogin());
            ps.setString(5, entity2.getText());
            ps.setObject(6, entity2.getAuthorLogin() != null ? entity2.getAuthorLogin() : null);
            ps.setObject(7, entity2.getDtCreate());
            ps.setObject(8, entity2.getUserLogin());
            ps.executeUpdate();
            conn.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<UserAudit> read(Pageable pageable) {
        List<UserAudit> audits = new ArrayList<>();

        String sql = "SELECT\n" +
                "    audit.id AS audit_id,\n" +
                "    audit.text AS audit_text,\n" +
                "    audit.author_login AS audit_author,\n" +
                "    audit.dt_create AS audit_dt_create,\n" +
                "    audit.user_login AS audit_user,\n" +
                "    obj.login AS user_login,\n" +
                "    obj.last_name AS user_last_name,\n" +
                "    obj.password AS user_password,\n" +
                "    obj.first_name AS user_first_name,\n" +
                "    obj.middle_name AS user_middle_name,\n" +
                "    obj.birthday AS user_birthday,\n" +
                "    obj.dt_reg AS user_dt_reg,\n" +
                "    author.login AS author_login,\n" +
                "    author.password AS author_password,\n" +
                "    author.last_name AS author_last_name,\n" +
                "    author.first_name AS author_first_name,\n" +
                "    author.middle_name AS author_middle_name,\n" +
                "    author.birthday AS author_birthday,\n" +
                "    author.dt_reg AS author_dt_reg\n" +
                "FROM\n" +
                "    app.users_audit AS audit\n" +
                "    LEFT JOIN app.users AS author ON audit.author_login = author.login\n" +
                "    LEFT JOIN app.users AS obj ON audit.user_login = obj.login ";

        if (pageable != null) {
            int limit = pageable.getSize();
            int offset = (pageable.getPage() - 1) * limit;

            sql += "\n LIMIT " + limit;
            sql += "\n OFFSET " + offset;
        }
        sql += ";";

        try (Connection conn = SQLMessengerInitializer.getInstance().getConnection();
             Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Long id = rs.getLong("audit_id");
                LocalDateTime dateTime = rs.getTimestamp("audit_dt_create").toLocalDateTime();
                String text = rs.getString("audit_text");
                User user = new UserConverter().convert(UserEntity.Builder.build()
                        .setFirstName(rs.getString("user_first_name"))
                        .setLastName(rs.getString("user_last_name"))
                        .setMiddleName(rs.getString("user_middle_name"))
                        .setLogin(rs.getString("user_login"))
                        .setPassword(rs.getString("user_password"))
                        .setBirthday(rs.getDate("user_birthday") != null ?
                                rs.getDate("user_birthday").toLocalDate() : null)
                        .setRegistration(rs.getTimestamp("user_dt_reg") != null ?
                                rs.getTimestamp("user_dt_reg").toLocalDateTime() : null)
                        .createUserEntity());
                User author = new UserConverter().convert(UserEntity.Builder.build()
                        .setFirstName(rs.getString("author_first_name"))
                        .setLastName(rs.getString("author_last_name"))
                        .setMiddleName(rs.getString("author_middle_name"))
                        .setPassword(rs.getString("author_password"))
                        .setLogin(rs.getString("author_login"))
                        .setBirthday(rs.getDate("author_birthday") != null ?
                                rs.getDate("author_birthday").toLocalDate() : null)
                        .setRegistration(rs.getTimestamp("author_dt_reg") != null ?
                                rs.getTimestamp("author_dt_reg").toLocalDateTime() : null)
                        .createUserEntity());
                UserAuditEntity entity = UserAuditEntity.Builder.build()
                        .setId(id)
                        .setDt_create(dateTime)
                        .setText(text)
                        .setUserLogin(user.getLogin())
                        .setAuthorLogin(author.getLogin())
                        .createUserAuditEntity();

                UserAudit userAudit = new UserAuditConverter().convert(entity);
                audits.add(userAudit);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return audits;
    }

    public static DBUserAuditStorage getInstance() {
        return instance;
    }
}
