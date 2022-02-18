package by.it_academy.jd2.mk_jd2_88_22.service.api;

import java.util.List;

public interface IChatService<T> {

    void saveMessageToStorage(String recipient, T message);

    List<T> getMessagesFromStorage(String login);

}
