package by.it_academy.jd2.mk_jd2_88_22.messenger.model;

import java.time.LocalDateTime;

public class Message {

    private int id;
    private User recipient;
    private User sender;
    private String message;
    private LocalDateTime date;

    public Message() {
    }

    public Message(User recipient, User sender, String message, LocalDateTime date) {
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

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
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
        return "Message{" +
                "id=" + id +
                ", recipient='" + recipient + '\'' +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    public static class Builder {

        private User recipient;
        private User sender;
        private String message;
        private LocalDateTime date;

        private Builder() {
        }

        public Builder setRecipient(User recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder setSender(User sender) {
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

        public Message createMessage() {
            return new Message(recipient, sender, message, date);
        }
    }
}
