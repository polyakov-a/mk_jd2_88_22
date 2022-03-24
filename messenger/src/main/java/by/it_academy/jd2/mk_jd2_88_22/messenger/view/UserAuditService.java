package by.it_academy.jd2.mk_jd2_88_22.messenger.view;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.ChoiceFactoryStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.view.api.IUserAuditService;

import java.util.List;

public class UserAuditService implements IUserAuditService {

    private static final UserAuditService instance = new UserAuditService();
    private final IUserAuditStorage storage;

    private UserAuditService() {
        this.storage = ChoiceFactoryStorage.getInstance().getUserAuditStorage();
    }

    @Override
    public void create(UserAudit audit) {
        this.storage.create(audit);
    }

    @Override
    public List<UserAudit> getAll(Pageable pageable) {
        return this.storage.read(pageable);
    }

    public static UserAuditService getInstance() {
        return instance;
    }
}
