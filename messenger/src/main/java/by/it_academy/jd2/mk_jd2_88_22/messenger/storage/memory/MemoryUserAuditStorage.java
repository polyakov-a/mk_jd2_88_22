package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.memory;

import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MemoryUserAuditStorage implements IUserAuditStorage {

    private List<UserAudit> audits;

    public MemoryUserAuditStorage() {
        this.audits = new ArrayList<>();
    }

    @Override
    public void add(UserAudit audit) {
        if (audit == null) {
            throw new IllegalArgumentException("Audit cannot be NULL");
        } else {
            this.audits.add(audit);
        }
    }

    @Override
    public void add(UserAudit audit1, UserAudit audit2) {
        if (audit1 == null || audit2 == null) {
            throw new IllegalArgumentException("Audit cannot be NULL");
        } else {
            this.audits.add(audit1);
            this.audits.add(audit2);
        }
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
}
