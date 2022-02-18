package by.it_academy.jd2.mk_jd2_88_22.dto;

import java.time.LocalDateTime;

public class Message {

    LocalDateTime date;
    String sender;
    String message;

    public Message(LocalDateTime date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

}
