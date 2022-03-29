package by.it_academy.jd2.mk_jd2_88_22.messenger.view;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.ChoiceFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IChatService;

import java.util.List;

public class ChatService implements IChatService {

    private static final ChatService instance = new ChatService();
    private final IChatStorage storage;

    private ChatService() {
        this.storage = ChoiceFactoryStorage.getInstance().getChatStorage();
    }

    @Override
    public void save(Message message) {
        this.storage.add(message);
    }

    @Override
    public List<Message> getAllByRecipient(String login) {
        return this.storage.getAllByRecipient(login);
    }

    public static ChatService getInstance() {
        return instance;
    }
}
