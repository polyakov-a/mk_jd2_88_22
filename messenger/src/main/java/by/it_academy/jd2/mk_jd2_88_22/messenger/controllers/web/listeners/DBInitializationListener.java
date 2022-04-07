package by.it_academy.jd2.mk_jd2_88_22.messenger.controllers.web.listeners;

import by.it_academy.jd2.mk_jd2_88_22.messenger.storage.hibernate.api.HibernateDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DBInitializationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateDataSource.class);
            HibernateDataSource hds = context.getBean(HibernateDataSource.class);
            hds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
