package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.UserAuditEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Pageable;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.UserAudit;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserAuditConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserAuditStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateDataSource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HibernateUserAuditStorage implements IUserAuditStorage {

    private final HibernateDataSource hibernateDataSource;
    private final UserAuditConverter converter = new UserAuditConverter();

    public HibernateUserAuditStorage(HibernateDataSource hibernateDataSource) {
        this.hibernateDataSource = hibernateDataSource;
    }

    @Override
    public void add(UserAudit audit) {
        if (audit == null) {
            throw new IllegalArgumentException("Audit cannot be NULL");
        }
        EntityManager entityManager = this.hibernateDataSource.getEntityManager();
        entityManager.getTransaction().begin();

        UserAuditEntity entity = this.converter.convertToEntity(audit);
        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void add(UserAudit audit1, UserAudit audit2) {
        if (audit1 == null || audit2 == null) {
            throw new IllegalArgumentException("Audit cannot be NULL");
        }
        EntityManager entityManager = this.hibernateDataSource.getEntityManager();
        entityManager.getTransaction().begin();

        UserAuditEntity entity1 = this.converter.convertToEntity(audit1);
        entityManager.persist(entity1);

        UserAuditEntity entity2 = this.converter.convertToEntity(audit2);
        entityManager.persist(entity2);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<UserAudit> getAll(Pageable pageable) {
        EntityManager entityManager = this.hibernateDataSource.getEntityManager();

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

        entityManager.close();

        return entities.stream()
                .map(this.converter::convertToDTO)
                .collect(Collectors.toList());
    }
}
