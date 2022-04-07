package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.MessageConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateDataSource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HibernateChatStorage implements IChatStorage {

    private final HibernateDataSource hibernateDataSource;
    private final MessageConverter converter = new MessageConverter();
    private final UserConverter userConverter = new UserConverter();


    public HibernateChatStorage(HibernateDataSource hibernateDataSource) {
        this.hibernateDataSource = hibernateDataSource;
    }

    @Override
    public void add(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be NULL");
        }
        EntityManager entityManager = hibernateDataSource.getEntityManager();
        entityManager.getTransaction().begin();

        MessageEntity entity = this.converter.convertToEntity(message);
        entityManager.persist(entity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Message> getAllByRecipient(String recipient) {
        EntityManager entityManager = hibernateDataSource.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(root).where(cb.like(root.get("recipient").get("login"), recipient));

        List<MessageEntity> entities = entityManager.createQuery(query).getResultList();

        return entities.stream()
                .map(this.converter::convertToDTO)
                .collect(Collectors.toList());
    }
}
