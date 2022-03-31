package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory.api.MemoryFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api.SQLFactoryStorage;

public class ChoiceFactoryStorage implements IFactoryStorage {

    private static final ChoiceFactoryStorage instance = new ChoiceFactoryStorage();
    private final IFactoryStorage memoryFS;
    private final IFactoryStorage sqlFS;
    private final IFactoryStorage hibernateFS;
    private String chosenFactory;

    public ChoiceFactoryStorage() {
        this.memoryFS = MemoryFactoryStorage.getInstance();
        this.sqlFS = SQLFactoryStorage.getInstance();
        this.hibernateFS = HibernateFactoryStorage.getInstance();
        this.chosenFactory = "hibernate";
    }

    @Override
    public IUserStorage getUserStorage() {
        return switch (this.chosenFactory) {
            case "memory" -> memoryFS.getUserStorage();
            case "sql" -> sqlFS.getUserStorage();
            default -> hibernateFS.getUserStorage();
        };
    }

    @Override
    public IChatStorage getChatStorage() {
        return switch (this.chosenFactory) {
            case "memory" -> memoryFS.getChatStorage();
            case "sql" -> sqlFS.getChatStorage();
            default -> hibernateFS.getChatStorage();
        };
    }

    @Override
    public IUserAuditStorage getUserAuditStorage() {
        return switch (this.chosenFactory) {
            case "memory" -> memoryFS.getUserAuditStorage();
            case "sql" -> sqlFS.getUserAuditStorage();
            default -> hibernateFS.getUserAuditStorage();
        };
    }

    public String getChosenFactory() {
        return chosenFactory;
    }

    public void setChosenFactory(String chosenFactory) {
        if (chosenFactory != null) {
            this.chosenFactory = chosenFactory;
        }
    }

    public static ChoiceFactoryStorage getInstance() {
        return instance;
    }
}
