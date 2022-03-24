package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.api.MemoryFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api.SQLFactoryStorage;

public class ChoiceFactoryStorage implements IFactoryStorage {

    private static final ChoiceFactoryStorage instance = new ChoiceFactoryStorage();
    private final IFactoryStorage memoryFS;
    private final IFactoryStorage sqlFS;
    private final IFactoryStorage hibernateFS;

    public ChoiceFactoryStorage() {
        this.memoryFS = MemoryFactoryStorage.getInstance();
        this.sqlFS = SQLFactoryStorage.getInstance();
        this.hibernateFS = HibernateFactoryStorage.getInstance();
    }

    @Override
    public IUserStorage getUserStorage() {
        return this.hibernateFS.getUserStorage();
    }

    @Override
    public IChatStorage getChatStorage() {
        return this.hibernateFS.getChatStorage();
    }

    @Override
    public IUserAuditStorage getUserAuditStorage() {
        return this.hibernateFS.getUserAuditStorage();
    }

    public static ChoiceFactoryStorage getInstance() {
        return instance;
    }

}
