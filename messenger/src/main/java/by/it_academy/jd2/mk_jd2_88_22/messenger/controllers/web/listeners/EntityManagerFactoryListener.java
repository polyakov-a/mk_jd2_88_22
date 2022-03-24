package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.listeners;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EntityManagerFactoryListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateMessengerInitializer.getInstance().getEntityManagerFactory().close();
    }
}
