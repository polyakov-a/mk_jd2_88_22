package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.MessageConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("dbChatStorage")
public class DBChatStorage implements IChatStorage {

    private final IUserStorage userStorage;
    private final ComboPooledDataSource dataSource;
    private final MessageConverter converter = new MessageConverter();
    private final UserConverter userConverter = new UserConverter();
    private final String INSERT_MESSAGE_SQL = "INSERT INTO app.messages (recipient, sender, message, send_date) " +
            "VALUES (?, ?, ?, ?);";
    private final String GET_MESSAGE_BY_RECEIVER_SQL = "SELECT id, recipient, sender, message, send_date FROM app.messages " +
            "WHERE recipient = ?;";

    public DBChatStorage(IUserStorage dbUserStorage,
                         ComboPooledDataSource dataSource) {
        this.userStorage = dbUserStorage;
        this.dataSource = dataSource;
    }


    @Override
    public void add(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be NULL");
        }
        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_MESSAGE_SQL)) {
            MessageEntity entity = this.converter.convertToEntity(message);
            ;
            ps.setObject(1, entity.getRecipient().getLogin());
            ps.setObject(2, entity.getSender().getLogin());
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
                        .setRecipient(this.userConverter.convertToEntity(this.userStorage.getUserByLogin(rs.getString(2))))
                        .setSender(this.userConverter.convertToEntity(this.userStorage.getUserByLogin(rs.getString(3))))
                        .setMessage(rs.getString(4))
                        .setDate(rs.getTimestamp(5).toLocalDateTime())
                        .createMessage();
                entity.setId(rs.getInt(1));

                Message message = this.converter.convertToDTO(entity);
                messages.add(message);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }
}
