package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory factory;

    public UserDaoHibernateImpl() {

    }

    static {
        try {
            factory = Util.getSessionFactory();
        } catch (Throwable e) {
            System.out.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }
    }


    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("create table if not exists users" +
                    "(id bigint not null auto_increment," +
                    "name varchar(45) not null," +
                    "lastName varchar(45) not null," +
                    "age tinyint not null," +
                    "primary key (id))").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table if exists users").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users where id = " + id).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Session session = null;
        Transaction transaction;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from users");
            users = (List<User>) query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
