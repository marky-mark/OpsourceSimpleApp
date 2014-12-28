package com.dimensiondata.hibernate;

import com.dimensiondata.hibernate.entity.Server;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class HibernateServerService implements ServerService {

    @Override
    public void create(Server server) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(server);

        try {
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new HibernateServerServiceException(e);
        } finally {
            session.close();
        }
    }

    //more efficient write but if 1 element already exists the whole write will fail
    @Override
    public void create(List<Server> servers) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        for (Server server : servers) {
            session.save(server);
        }

        try {
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new HibernateServerServiceException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Server getById(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Server) session.get(Server.class, id);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Server server) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(server);

        try {
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new HibernateServerServiceException(e);
        }  finally {
            session.close();
        }
    }

    @Override
    public void delete(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Server server = new Server();
            server.setId(id);
            session.delete(server);
            session.getTransaction().commit();
        }  finally {
            session.close();
        }
    }

    @Override
    public List<Server> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from Server");
            return query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public int count() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            return ((Long) session.createQuery("select count(*) from Server").uniqueResult()).intValue();
        } finally {
            session.close();
        }
    }
}
