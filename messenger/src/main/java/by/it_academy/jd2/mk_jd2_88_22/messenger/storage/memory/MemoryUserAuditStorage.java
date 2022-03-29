package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryUserAuditStorage implements IUserAuditStorage {

    private static final MemoryUserAuditStorage instance = new MemoryUserAuditStorage();
    private List<UserAudit> audits;

    public MemoryUserAuditStorage() {
        this.audits = new ArrayList<>();
    }

    @Override
    public void add(UserAudit audit) {
        this.audits.add(audit);
    }

    @Override
    public void add(UserAudit audit1, UserAudit audit2) {
        this.audits.add(audit1);
        this.audits.add(audit2);
    }

    @Override
    public List<UserAudit> getAll(Pageable pageable) {
        if (pageable != null) {
            return this.audits.stream()
                    .skip((long) (pageable.getPage() - 1) * pageable.getSize())
                    .limit(pageable.getSize())
                    .collect(Collectors.toList());
        } else {
            return this.audits;
        }
    }

    public static MemoryUserAuditStorage getInstance() {
        return instance;
    }
}
