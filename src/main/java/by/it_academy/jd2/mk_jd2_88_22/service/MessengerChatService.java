package by.it_academy.jd2.mk_jd2_88_22.service;

import by.it_academy.jd2.mk_jd2_88_22.service.api.IChatService;
import by.it_academy.jd2.mk_jd2_88_22.dto.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessengerChatService implements IChatService<Message> {

    private static final IChatService<Message> instance = new MessengerChatService();
    private Map<String, List<Message>> userMessages;

    private MessengerChatService() {
        userMessages = new HashMap<>();
    }

    @Override
    public void saveMessageToStorage(String recipient, Message message) {
        List<Message> messages = new ArrayList<>();
        if (this.userMessages.containsKey(recipient)) {
            messages = this.userMessages.get(recipient);
        }
        messages.add(message);
        this.userMessages.put(recipient, messages);
    }

    @Override
    public List<Message> getMessagesFromStorage(String login) {
        List<Message> userMessages = null;
        if (this.userMessages.containsKey(login)) {
            userMessages = this.userMessages.get(login);
        }
        return userMessages;
    }

    public static IChatService<Message> getInstance() {
        return instance;
    }
}
