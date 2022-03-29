package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;

import java.util.List;

public interface IUserAuditStorage {

    void add(UserAudit audit);
    void add(UserAudit audit1, UserAudit audit2);
    List<UserAudit> getAll(Pageable pageable);

}
