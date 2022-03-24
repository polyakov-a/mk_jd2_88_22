package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.MessageConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api.SQLMessengerInitializer;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBChatStorage implements IChatStorage {

    private static final DBChatStorage instance = new DBChatStorage();
    private final DataSource dataSource;
    private final String INSERT_MESSAGE_SQL = "INSERT INTO app.messages (recipient, sender, message, send_date) " +
            "VALUES (?, ?, ?, ?);";
    private final String GET_MESSAGE_BY_RECEIVER_SQL = "SELECT id, recipient, sender, message, send_date FROM app.messages " +
            "WHERE recipient = ?;";

    private DBChatStorage() {
        this.dataSource = SQLMessengerInitializer.getInstance();
    }

    @Override
    public void save(Message message) {
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_MESSAGE_SQL)) {
            MessageEntity entity = new MessageConverter().convertBackward(message);
            ps.setString(1, entity.getRecipient());
            ps.setString(2, entity.getSender());
            ps.setString(3, entity.getMessage());
            ps.setObject(4, entity.getDate());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Message> getAllByRecipient(String recipient) {
        List<Message> messages = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_MESSAGE_BY_RECEIVER_SQL)) {

            ps.setString(1, recipient);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MessageEntity entity = MessageEntity.Builder.build()
                        .setId(rs.getInt(1))
                        .setRecipient(rs.getString(2))
                        .setSender(rs.getString(3))
                        .setMessage(rs.getString(4))
                        .setDate(rs.getTimestamp(5).toLocalDateTime())
                        .createMessage();

                Message message = new MessageConverter().convert(entity);
                messages.add(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    public static DBChatStorage getInstance() {
        return instance;
    }
}
