package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.DBChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.DBUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.DBUserStorage;

public class SQLFactoryStorage implements IFactoryStorage {

    private static final SQLFactoryStorage instance = new SQLFactoryStorage();
    private final IUserStorage userStorage;
    private final IChatStorage chatStorage;
    private final IUserAuditStorage userAuditStorage;

    public SQLFactoryStorage() {
        this.userStorage = DBUserStorage.getInstance();
        this.chatStorage = DBChatStorage.getInstance();
        this.userAuditStorage = DBUserAuditStorage.getInstance();
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

    public static SQLFactoryStorage getInstance() {
        return instance;
    }
}
