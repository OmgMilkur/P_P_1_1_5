package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    private Session session;
    private String sql;
    private List<User> userList;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        session = getSession().getCurrentSession();
        sql = """
            CREATE TABLE IF NOT EXISTS Users (`ID` INT NOT NULL AUTO_INCREMENT ,
             `Name` VARCHAR(15),
             `LastName` VARCHAR(25),
             `Age` INT, PRIMARY KEY (`ID`));
            """;

        session.beginTransaction();
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = getSession().getCurrentSession();
        sql = "DROP TABLE IF EXISTS Users";

        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = getSession().getCurrentSession();

        User user = new User(name, lastName, age);

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = getSession().getCurrentSession();

        session.beginTransaction();
        User user = session.find(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = getSession().getCurrentSession();

        session.beginTransaction();
        userList = session.createQuery("from User ", User.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session = getSession().getCurrentSession();

        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
