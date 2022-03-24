package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.User;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateUserStorage implements IUserStorage {

    private static final HibernateUserStorage instance = new HibernateUserStorage();
    private final HibernateMessengerInitializer DBInitializer;


    public HibernateUserStorage() {
        this.DBInitializer = HibernateMessengerInitializer.getInstance();
    }

    @Override
    public void createUser(User user) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        UserEntity entity = new UserConverter().convertBackward(user);
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> getUsers() {
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        List<UserEntity> entities = entityManager.createQuery(query).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return entities.stream().map(entity -> new UserConverter().convert(entity)).collect(Collectors.toList());
    }

    @Override
    public boolean ifUserExists(String login) {
        String hql = "SELECT U.login FROM UserEntity U WHERE U.login =: login";
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(hql);
        query.setParameter("login", login);
        List resultList = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList.size() != 0;
    }

    @Override
    public boolean isPasswordCorrect(String login, String password) {
        String hql = "FROM UserEntity U WHERE U.login =: login AND U.password =: password";
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(hql);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List resultList = query.getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return resultList.size() != 0;
    }

    @Override
    public User getUserByLogin(String login) {
        String hql = "FROM UserEntity U WHERE U.login =: login";
        EntityManager entityManager = this.DBInitializer.getEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(hql);
        query.setParameter("login", login);
        List resultList = query.getResultList();
        if (resultList.size() != 0) {
            return new UserConverter().convert((UserEntity) resultList.get(0));
        } else {
            return null;
        }
    }

    public static HibernateUserStorage getInstance() {
        return instance;
    }
}
