package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api.IConverter;

public class UserConverter implements IConverter<User, UserEntity> {

    @Override
    public User convertToDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        } else {
            User user = User.Builder.build()
                    .setFirstName(entity.getFirstName())
                    .setLastName(entity.getLastName())
                    .setMiddleName(entity.getMiddleName() != null ? entity.getMiddleName() : null)
                    .setLogin(entity.getLogin())
                    .setPassword(entity.getPassword())
                    .setBirthday(entity.getBirthday())
                    .setRegistration(entity.getRegistration())
                    .createUser();
            user.setId(entity.getId());
            return user;
        }
    }

    @Override
    public UserEntity convertToEntity(User user) {
        if (user == null) {
            return null;
        } else {
            return UserEntity.Builder.build()
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setMiddleName(user.getMiddleName() != null ? user.getMiddleName() : null)
                    .setLogin(user.getLogin())
                    .setPassword(user.getPassword())
                    .setBirthday(user.getBirthday())
                    .setRegistration(user.getRegistration())
                    .createUserEntity();
        }
    }
}
