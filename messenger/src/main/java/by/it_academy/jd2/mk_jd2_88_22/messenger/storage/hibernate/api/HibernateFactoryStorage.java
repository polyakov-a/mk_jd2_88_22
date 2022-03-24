package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.HibernateChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.HibernateUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.HibernateUserStorage;

public class HibernateFactoryStorage implements IFactoryStorage {

    private static final HibernateFactoryStorage instance = new HibernateFactoryStorage();
    private final IUserStorage userStorage;
    private final IChatStorage chatStorage;
    private final IUserAuditStorage userAuditStorage;


    public HibernateFactoryStorage() {
        this.userStorage = HibernateUserStorage.getInstance();
        this.chatStorage = HibernateChatStorage.getInstance();
        this.userAuditStorage = HibernateUserAuditStorage.getInstance();
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

    public static HibernateFactoryStorage getInstance() {
        return instance;
    }
}
