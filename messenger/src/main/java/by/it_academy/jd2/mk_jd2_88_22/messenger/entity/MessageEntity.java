package by.it_academy.jd2.mk_jd2_88_22.messenger.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages", schema = "app")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "app.messages_id_seq")
    private int id;
    private String recipient;
    private String sender;
    private String message;

    @Column(name = "send_date")
    private LocalDateTime date;

    public MessageEntity() {
    }

    public MessageEntity(int id, String recipient, String sender, String message, LocalDateTime date) {
        this.id = id;
        this.recipient = recipient;
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public static class Builder {

        private int id;
        private String recipient;
        private String sender;
        private String message;
        private LocalDateTime date;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public static Builder build() {
            return new Builder();
        }

        public MessageEntity createMessage() {
            return new MessageEntity(id, recipient, sender, message, date);
        }
    }
}
