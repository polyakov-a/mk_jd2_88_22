package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserAuditEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api.IConverter;

public class UserAuditConverter implements IConverter<UserAudit, UserAuditEntity> {

    private final UserConverter userConverter = new UserConverter();

    @Override
    public UserAudit convertToDTO(UserAuditEntity entity) {
        if (entity == null) {
            return null;
        } else {
            UserAudit userAudit = UserAudit.Builder.build()
                    .setDt_create(entity.getDtCreate())
                    .setText(entity.getText())
                    .setUser(userConverter.convertToDTO(entity.getUser()))
                    .setAuthor(entity.getAuthor() != null ?
                            userConverter.convertToDTO(entity.getAuthor()) : null)
                    .createUserAudit();
            userAudit.setId(entity.getId());
            return userAudit;
        }
    }

    @Override
    public UserAuditEntity convertToEntity(UserAudit audit) {
        if (audit == null) {
            return null;
        } else {
            UserAuditEntity entity = UserAuditEntity.Builder.build()
                    .setDt_create(audit.getDt_create())
                    .setText(audit.getText())
                    .setUser(userConverter.convertToEntity(audit.getUser()))
                    .setAuthor(audit.getAuthor() != null ?
                            userConverter.convertToEntity(audit.getAuthor()) : null)
                    .createUserAuditEntity();
            entity.setId(audit.getId());
            return entity;
        }
    }
}
