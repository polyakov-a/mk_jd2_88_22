package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.MessageConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateChatStorage implements IChatStorage {

    private static final HibernateChatStorage instance = new HibernateChatStorage();
    private final HibernateMessengerInitializer DBInitializer;


    private HibernateChatStorage() {
        this.DBInitializer = HibernateMessengerInitializer.getInstance();
    }

    @Override
    public void save(Message message) {
        EntityManager entityManager = DBInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        MessageEntity entity = new MessageConverter().convertBackward(message);
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public List<Message> getAllByRecipient(String recipient) {
        EntityManager entityManager = DBInitializer.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<MessageEntity> query = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = query.from(MessageEntity.class);
        query.select(root).where(cb.like(root.get("recipient"), recipient));
        List<MessageEntity> entities = entityManager.createQuery(query).getResultList();

        return entities.stream()
                .map(entity -> new MessageConverter().convert(entity))
                .collect(Collectors.toList());
    }

    public static HibernateChatStorage getInstance() {
        return instance;
    }
}
