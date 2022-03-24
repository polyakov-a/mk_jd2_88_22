package by.it_academy.jd2.mk_jd2_88_22.messenger.view.api;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;

import java.util.List;

public interface IUserAuditService {

    void create(UserAudit audit);
    List<UserAudit> getAll(Pageable pageable);
}
