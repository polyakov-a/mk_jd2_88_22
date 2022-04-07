package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class HibernateDataSource implements AutoCloseable {

    private final EntityManagerFactory factory;

    public HibernateDataSource() {
        this.factory = Persistence.createEntityManagerFactory("by.it_academy.jd2.mk_jd2_88_22.messenger.jpa");
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.factory;
    }

    @Override
    public void close() throws Exception {
        if (this.factory.isOpen()) {
            factory.close();
        }
    }
}
