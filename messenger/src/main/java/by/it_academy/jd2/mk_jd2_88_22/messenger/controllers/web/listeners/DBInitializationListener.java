package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.listeners;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateMessengerInitializer;
import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api.SQLMessengerInitializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBInitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            HibernateMessengerInitializer.getInstance().close();
            SQLMessengerInitializer.getInstance().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
