package by.it_academy.jd2.mk_jd2_88_22.messenger.view;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService {

    private final IChatStorage chatStorage;

    private ChatService(IChatStorage chatStorage) {
        this.chatStorage = chatStorage;
    }

    @Override
    public void save(Message message) {
        this.chatStorage.add(message);
    }

    @Override
    public List<Message> getAllByRecipient(String login) {
        return this.chatStorage.getAllByRecipient(login);
    }
}
