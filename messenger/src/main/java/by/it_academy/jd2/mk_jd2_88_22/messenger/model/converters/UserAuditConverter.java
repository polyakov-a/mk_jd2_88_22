package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserAuditEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api.IConverter;

public class UserAuditConverter implements IConverter<UserAudit, UserAuditEntity> {

    @Override
    public UserAudit convert(UserAuditEntity entity) {
        return UserAudit.Builder.build()
                .setId(entity.getId())
                .setDt_create(entity.getDtCreate())
                .setText(entity.getText())
                .setUserLogin(entity.getUserLogin())
                .setAuthorLogin(entity.getAuthorLogin())
                .createUserAudit();
    }

    @Override
    public UserAuditEntity convertBackward(UserAudit audit) {
        return UserAuditEntity.Builder.build()
                .setId(audit.getId())
                .setDt_create(audit.getDt_create())
                .setText(audit.getText())
                .setUserLogin(audit.getUserLogin())
                .setAuthorLogin(audit.getAuthorLogin())
                .createUserAuditEntity();
    }
}
