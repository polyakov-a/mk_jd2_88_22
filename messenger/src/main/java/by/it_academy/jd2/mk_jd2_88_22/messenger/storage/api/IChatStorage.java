package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;

import java.util.List;

public interface IChatStorage {

    void add(Message message);

    List<Message> getAllByRecipient(String recipient);

}
