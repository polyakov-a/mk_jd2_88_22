package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api;

import by.it_academy.jd2.mk_jd2_88_22.booking.storage.api.DataSourceCreator;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class SQLMessengerInitializer {

    private static volatile SQLMessengerInitializer instance;
    private ComboPooledDataSource cpds;

    private SQLMessengerInitializer() {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/messenger");
        cpds.setUser("postgres");
        cpds.setPassword("postgres");
        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(180);
    }

    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSourceCreator.class) {
                if (instance == null) {
                    instance = new SQLMessengerInitializer();
                }
            }
        }
        return instance.cpds;
    }
}
