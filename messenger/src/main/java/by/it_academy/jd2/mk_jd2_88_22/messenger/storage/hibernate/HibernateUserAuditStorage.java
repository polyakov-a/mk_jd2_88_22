package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserAuditEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserAuditConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateUserAuditStorage implements IUserAuditStorage {

    private static final HibernateUserAuditStorage instance = new HibernateUserAuditStorage();
    private final HibernateMessengerInitializer DBInitializer;

    private HibernateUserAuditStorage() {
        this.DBInitializer = HibernateMessengerInitializer.getInstance();
    }

    @Override
    public void create(UserAudit audit) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        UserAuditEntity entity = new UserAuditConverter().convertBackward(audit);
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void create(UserAudit audit1, UserAudit audit2) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        UserAuditEntity entity1 = new UserAuditConverter().convertBackward(audit1);
        entityManager.persist(entity1);
        UserAuditEntity entity2 = new UserAuditConverter().convertBackward(audit2);
        entityManager.persist(entity2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<UserAudit> read(Pageable pageable) {
        EntityManager entityManager = this.DBInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserAuditEntity> cr = cb.createQuery(UserAuditEntity.class);
        Root<UserAuditEntity> root = cr.from(UserAuditEntity.class);
        cr.select(root);

        TypedQuery<UserAuditEntity> query = entityManager.createQuery(cr);
        if (pageable != null) {
            query.setFirstResult((pageable.getPage() - 1) * pageable.getSize());
            query.setMaxResults(pageable.getSize());
        }
        List<UserAuditEntity> entities = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return entities.stream()
                .map(entity -> new UserAuditConverter().convert(entity))
                .collect(Collectors.toList());
    }

    public static HibernateUserAuditStorage getInstance() {
        return instance;
    }
}
