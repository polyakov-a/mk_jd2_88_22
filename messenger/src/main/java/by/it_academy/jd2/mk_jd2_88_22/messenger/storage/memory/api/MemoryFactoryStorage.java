package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.MemoryChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.MemoryUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.MemoryUserStorage;

public class MemoryFactoryStorage implements IFactoryStorage {

    private static final MemoryFactoryStorage instance = new MemoryFactoryStorage();
    private final IUserStorage userStorage;
    private final IChatStorage chatStorage;
    private final IUserAuditStorage userAuditStorage;

    public MemoryFactoryStorage() {
        this.userStorage = MemoryUserStorage.getInstance();
        this.chatStorage = MemoryChatStorage.getInstance();
        this.userAuditStorage = MemoryUserAuditStorage.getInstance();
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

    public static MemoryFactoryStorage getInstance() {
        return instance;
    }
}
