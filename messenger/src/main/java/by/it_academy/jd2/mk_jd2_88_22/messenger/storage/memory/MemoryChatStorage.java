package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryChatStorage implements IChatStorage {

    private static final MemoryChatStorage instance = new MemoryChatStorage();
    private List<Message> messages;

    private MemoryChatStorage() {
        messages = new ArrayList<>();
    }

    @Override
    public void add(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be NULL");
        } else {
            this.messages.add(message);
        }
    }

    @Override
    public List<Message> getAllByRecipient(String login) {
        return this.messages.stream()
                .filter(message -> message.getRecipient().equals(login))
                .collect(Collectors.toList());
    }

    public static MemoryChatStorage getInstance() {
        return instance;
    }
}
