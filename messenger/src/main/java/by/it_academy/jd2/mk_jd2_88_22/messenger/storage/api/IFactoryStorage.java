package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api;

public interface IFactoryStorage {

    IUserStorage getUserStorage();

    IChatStorage getChatStorage();

    IUserAuditStorage getUserAuditStorage();
}
