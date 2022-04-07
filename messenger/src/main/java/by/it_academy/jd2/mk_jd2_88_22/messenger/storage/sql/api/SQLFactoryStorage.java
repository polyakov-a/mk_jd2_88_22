package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import org.springframework.stereotype.Component;

@Component("sqlFactoryStorage")
public class SQLFactoryStorage implements IFactoryStorage {

    private final IUserStorage userStorage;
    private final IChatStorage chatStorage;
    private final IUserAuditStorage userAuditStorage;

    public SQLFactoryStorage(IUserStorage dbUserStorage,
                             IChatStorage dbChatStorage,
                             IUserAuditStorage dbUserAuditStorage) {
        this.userStorage = dbUserStorage;
        this.chatStorage = dbChatStorage;
        this.userAuditStorage = dbUserAuditStorage;
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
