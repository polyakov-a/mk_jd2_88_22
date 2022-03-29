package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.MessageConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.UserConverter;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IChatStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.api.IUserStorage;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateChatStorage implements IChatStorage {

    private static final HibernateChatStorage instance = new HibernateChatStorage();
    private final HibernateMessengerInitializer DBInitializer;
    private final IUserStorage userStorage;
    private final MessageConverter converter = new MessageConverter();
    private final UserConverter userConverter = new UserConverter();


    private HibernateChatStorage() {
        this.DBInitializer = HibernateMessengerInitializer.getInstance();
        this.userStorage = HibernateUserStorage.getInstance();
    }

    @Override
    public void add(Message message) {
        EntityManager entityManager = DBInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        MessageEntity entity = this.converter.convertToEntity(message);
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
        query.select(root).where(cb.like(root.get("recipient").get("login"), recipient));

        List<MessageEntity> entities = entityManager.createQuery(query).getResultList();

        return entities.stream()
                .map(this.converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static HibernateChatStorage getInstance() {
        return instance;
    }
}
