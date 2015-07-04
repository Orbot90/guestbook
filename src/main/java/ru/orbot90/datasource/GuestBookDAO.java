package ru.orbot90.datasource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.orbot90.message.Message;

import java.util.List;

/**
 * Created by orbot on 04.07.15.
 */
public class GuestBookDAO {
    private SessionFactory sessionFactory;
    private static GuestBookDAO instance = null;
    private Transaction transaction;

    private GuestBookDAO() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static GuestBookDAO getInstance() {
        if(null == instance) {
            instance = new GuestBookDAO();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void saveMessage(Message m) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(m);
        transaction.commit();
        session.close();
    }

    public List<Message> getMessages(int first) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List messages = session.createQuery("from Message m order by m.id desc")
                .setFirstResult(first).setMaxResults(5).list();
        transaction.commit();
        session.close();
        return messages;
    }

    public long getRowNum() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        long rowCount = (Long)session.createQuery("select count(m) from Message m").uniqueResult();
        transaction.commit();
        session.close();
        return rowCount;
    }
}
