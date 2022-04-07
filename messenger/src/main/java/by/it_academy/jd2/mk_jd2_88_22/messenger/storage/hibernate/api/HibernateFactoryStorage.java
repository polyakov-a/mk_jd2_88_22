package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import org.springframework.stereotype.Component;

@Component
public class HibernateFactoryStorage implements IFactoryStorage {

    private final IUserStorage userStorage;
    private final IChatStorage chatStorage;
    private final IUserAuditStorage userAuditStorage;

    public HibernateFactoryStorage(IUserStorage hibernateUserStorage,
                                   IChatStorage hibernateChatStorage,
                                   IUserAuditStorage hibernateUserAuditStorage) {
        this.userStorage = hibernateUserStorage;
        this.chatStorage = hibernateChatStorage;
        this.userAuditStorage = hibernateUserAuditStorage;
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
