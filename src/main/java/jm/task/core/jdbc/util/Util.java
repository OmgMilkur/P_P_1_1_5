package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    public static SessionFactory getSession() {
        Properties properties = new Properties();

        try (InputStream is = new FileInputStream("src/main/resources/hibernate.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SessionFactory factory = new Configuration().addProperties(properties).addAnnotatedClass(User.class)
                .buildSessionFactory();

        return factory;
    }
}
