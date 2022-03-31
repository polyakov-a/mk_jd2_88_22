package by.it_academy.jd2.mk_jd2_88_22.messenger.storage.sql.api;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

public class SQLMessengerInitializer implements AutoCloseable {

    private static volatile SQLMessengerInitializer instance;
    private ComboPooledDataSource dataSource;

    private SQLMessengerInitializer() {
        dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/messenger");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        dataSource.setMinPoolSize(3);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxStatements(180);
    }

    public ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void close() throws Exception {
        dataSource.close();
    }

    public static SQLMessengerInitializer getInstance() {
        if (instance == null) {
            synchronized (SQLMessengerInitializer.class) {
                if (instance == null) {
                    instance = new SQLMessengerInitializer();
                }
            }
        }
        return instance;
    }
}
