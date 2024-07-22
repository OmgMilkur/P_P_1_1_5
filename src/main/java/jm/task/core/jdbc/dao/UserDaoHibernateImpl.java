package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private final SessionFactory sessionFactory;
    private String sql;


    public UserDaoHibernateImpl() {
        sessionFactory = getSession();
    }


    @Override
    public void createUsersTable() {
        sql = """
            CREATE TABLE IF NOT EXISTS Users (`ID` INT NOT NULL AUTO_INCREMENT ,
             `Name` VARCHAR(15),
             `LastName` VARCHAR(25),
             `Age` INT, PRIMARY KEY (`ID`));
            """;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ignore) {}
    }

    @Override
    public void dropUsersTable() {
        sql = "DROP TABLE IF EXISTS Users";

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ignore) {}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException ignore) {}
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException ignore) {}
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User ", User.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException ignore) {}

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ignore) {}
    }
}
