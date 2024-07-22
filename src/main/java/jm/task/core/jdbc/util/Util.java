package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class Util {
    public static SessionFactory getSession() {
        Properties properties = new Properties();

        properties.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/MyBDtest");
        properties.put(AvailableSettings.USER, "root");
        properties.put(AvailableSettings.PASS, "root");
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create");
        properties.put(AvailableSettings.SHOW_SQL, "true");


        return new Configuration().setProperties(properties).addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
