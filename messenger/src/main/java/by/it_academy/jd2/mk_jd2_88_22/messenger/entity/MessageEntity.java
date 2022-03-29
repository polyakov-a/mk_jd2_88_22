package by.it_academy.jd2.mk_jd2_88_22.messenger.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages", schema = "app")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipient")
    private UserEntity recipient;

    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity sender;
    private String message;

    @Column(name = "send_date")
    private LocalDateTime date;

    public MessageEntity() {
    }

    public MessageEntity(UserEntity recipient, UserEntity sender, String message, LocalDateTime date) {
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

    public UserEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(UserEntity recipient) {
        this.recipient = recipient;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
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

        private UserEntity recipient;
        private UserEntity sender;
        private String message;
        private LocalDateTime date;

        private Builder() {
        }

        public Builder setRecipient(UserEntity recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder setSender(UserEntity sender) {
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
            return new MessageEntity(recipient, sender, message, date);
        }
    }
}
