package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChoiceFactoryStorage implements IFactoryStorage {

    private final IFactoryStorage memoryFactoryStorage;
    private final IFactoryStorage sqlFactoryStorage;
    private final IFactoryStorage hibernateFactoryStorage;

    @Value("${factory.storage.value}")
    private String chosenFactory;

    public ChoiceFactoryStorage(IFactoryStorage memoryFactoryStorage,
                                IFactoryStorage sqlFactoryStorage,
                                IFactoryStorage hibernateFactoryStorage) {
        this.memoryFactoryStorage = memoryFactoryStorage;
        this.sqlFactoryStorage = sqlFactoryStorage;
        this.hibernateFactoryStorage = hibernateFactoryStorage;
    }

    @Override
    public IUserStorage getUserStorage() {
        return switch (this.chosenFactory) {
            case "memory" -> memoryFactoryStorage.getUserStorage();
            case "sql" -> sqlFactoryStorage.getUserStorage();
            default -> hibernateFactoryStorage.getUserStorage();
        };
    }

    @Override
    public IChatStorage getChatStorage() {
        return switch (this.chosenFactory) {
            case "memory" -> memoryFactoryStorage.getChatStorage();
            case "sql" -> sqlFactoryStorage.getChatStorage();
            default -> hibernateFactoryStorage.getChatStorage();
        };
    }

    @Override
    public IUserAuditStorage getUserAuditStorage() {
        return switch (this.chosenFactory) {
            case "memory" -> memoryFactoryStorage.getUserAuditStorage();
            case "sql" -> sqlFactoryStorage.getUserAuditStorage();
            default -> hibernateFactoryStorage.getUserAuditStorage();
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
}
