package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api.IConverter;

public class UserConverter implements IConverter<User, UserEntity> {

    @Override
    public User convert(UserEntity entity) {
        return User.Builder.build()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setMiddleName(entity.getMiddleName())
                .setLogin(entity.getLogin())
                .setPassword(entity.getPassword())
                .setBirthday(entity.getBirthday())
                .setRegistration(entity.getRegistration())
                .createUser();
    }

    @Override
    public UserEntity convertBackward(User user) {
        return UserEntity.Builder.build()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMiddleName(user.getMiddleName())
                .setLogin(user.getLogin())
                .setPassword(user.getPassword())
                .setBirthday(user.getBirthday())
                .setRegistration(user.getRegistration())
                .createUserEntity();
    }
}
