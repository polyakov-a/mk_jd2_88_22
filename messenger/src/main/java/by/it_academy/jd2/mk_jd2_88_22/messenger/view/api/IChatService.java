package by.it_academy.jd2.mk_jd2_88_22.messenger.view.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;

import java.util.List;

public interface IChatService {

    void save(Message message);

    List<Message> getAll(String login);
}
