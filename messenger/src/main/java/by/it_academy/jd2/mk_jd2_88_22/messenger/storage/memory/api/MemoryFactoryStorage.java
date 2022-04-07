package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import org.springframework.stereotype.Component;

@Component
public class MemoryFactoryStorage implements IFactoryStorage {

    private final IUserStorage userStorage;
    private final IChatStorage chatStorage;
    private final IUserAuditStorage userAuditStorage;

    public MemoryFactoryStorage(IUserStorage memoryUserStorage,
                                IChatStorage memoryChatStorage,
                                IUserAuditStorage memoryUserAuditStorage) {
        this.userStorage = memoryUserStorage;
        this.chatStorage = memoryChatStorage;
        this.userAuditStorage = memoryUserAuditStorage;
    }

    @Override
    public IUserStorage getUserStorage() {
        return this.userStorage;
    }

    @Override
    public IChatStorage getChatStorage() {
        return this.chatStorage;
    }

    @Override
    public IUserAuditStorage getUserAuditStorage() {
        return this.userAuditStorage;
    }
}
