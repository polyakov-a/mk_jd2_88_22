package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateMessengerInitializer {

    private static volatile HibernateMessengerInitializer instance;
    private final EntityManagerFactory factory;

    private HibernateMessengerInitializer() {
        this.factory = Persistence.createEntityManagerFactory("by.it_academy.jd2.mk_jd2_88_22.messenger.jpa");
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.factory;
    }

    public static HibernateMessengerInitializer getInstance() {
        if (instance == null) {
            synchronized (HibernateMessengerInitializer.class) {
                if (instance == null) {
                    instance = new HibernateMessengerInitializer();
                }
            }
        }
        return instance;
    }
}
