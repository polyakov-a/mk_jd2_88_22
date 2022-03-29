package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateUserStorage implements IUserStorage {

    private static final HibernateUserStorage instance = new HibernateUserStorage();
    private final HibernateMessengerInitializer DBInitializer;
    private final IUserAuditStorage userAuditStorage = HibernateUserAuditStorage.getInstance();
    private final UserConverter converter = new UserConverter();


    public HibernateUserStorage() {
        this.DBInitializer = HibernateMessengerInitializer.getInstance();
    }

    @Override
    public void add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be NULL");
        }
        if (this.ifUserExists(user.getLogin())) {
            throw new IllegalArgumentException("User with login " + user.getLogin() + " already exists");
        } else {
            EntityManager entityManager = this.DBInitializer.getEntityManager();
            entityManager.getTransaction().begin();

            UserEntity entity = this.converter.convertToEntity(user);
            entityManager.persist(entity);

            entityManager.getTransaction().commit();
            entityManager.close();

            UserAudit audit = UserAudit.Builder.build()
                    .setDt_create(LocalDateTime.now())
                    .setText("User registered")
                    .setUser(user)
                    .setAuthor(null)
                    .createUserAudit();
            this.userAuditStorage.add(audit);
        }
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        List<UserEntity> entities = entityManager.createQuery(query).getResultList();

        entityManager.close();

        return entities.stream()
                .map(this.converter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean ifUserExists(String login) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        UserEntity entity = entityManager.find(UserEntity.class, login);

        entityManager.close();

        return entity != null;
    }

    @Override
    public boolean isPasswordCorrect(String login, String password) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(cb.and(cb.like(root.get("login"), login)), cb.like(root.get("password"), password));
        UserEntity entity = entityManager.createQuery(query).getResultStream().findFirst().orElse(null);

        entityManager.close();

        return entity != null;
    }

    @Override
    public User getUserByLogin(String login) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(cb.like(root.get("login"), login));
        UserEntity entity = entityManager.createQuery(query).getResultStream().findFirst().orElse(null);

        entityManager.close();

        return this.converter.convertToDTO(entity);
    }

    public static HibernateUserStorage getInstance() {
        return instance;
    }
}
