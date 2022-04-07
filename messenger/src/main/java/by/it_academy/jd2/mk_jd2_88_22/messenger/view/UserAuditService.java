package by.it_academy.jd2.mk_jd2_88_22.messenger.view;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuditService implements IUserAuditService {

    private final IUserAuditStorage userAuditStorage;

    private UserAuditService(IUserAuditStorage userAuditStorage) {
        this.userAuditStorage = userAuditStorage;
    }

    @Override
    public void create(UserAudit audit) {
        this.userAuditStorage.add(audit);
    }

    @Override
    public List<UserAudit> getAll(Pageable pageable) {
        return this.userAuditStorage.getAll(pageable);
    }
}
