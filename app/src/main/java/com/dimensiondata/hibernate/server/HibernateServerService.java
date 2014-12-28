package com.dimensiondata.hibernate.server;

import com.dimensiondata.hibernate.callback.HibernateSession;
import com.dimensiondata.hibernate.callback.HibernateTransactionSession;
import com.dimensiondata.hibernate.callback.SessionCallback;
import com.dimensiondata.hibernate.server.entity.Server;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HibernateServerService implements ServerService {

    @Override
    public void create(final Server server) {
        new HibernateTransactionSession<Void>().callback(new SessionCallback<Void>() {
            @Override
            public Void callback(Session session) {
                session.save(server);
                return null;
            }
        });
    }

    //more efficient write but if 1 element already exists the whole write will fail
    @Override
    public void create(final List<Server> servers) {
        new HibernateTransactionSession<Void>().callback(new SessionCallback<Void>() {
            @Override
            public Void callback(Session session) {
                for (Server server : servers) {
                    session.save(server);
                }
                return null;
            }
        });
    }

    @Override
    public Server getById(final String id) {
        return new HibernateTransactionSession<Server>().callback(new SessionCallback<Server>() {
            @Override
            public Server callback(Session session) {
                return (Server) session.get(Server.class, id);
            }
        });
    }

    @Override
    public void update(final Server server) {
        new HibernateTransactionSession<Void>().callback(new SessionCallback<Void>() {
            @Override
            public Void callback(Session session) {
                session.update(server);
                return null;
            }
        });
    }

    @Override
    public void delete(final String id) {
        new HibernateTransactionSession<Void>().callback(new SessionCallback<Void>() {
            @Override
            public Void callback(Session session) {
                Server server = new Server();
                server.setId(id);
                session.delete(server);
                return null;
            }
        });
    }

    @Override
    public List<Server> getAll() {
        return new HibernateSession<List<Server>>().callback(new SessionCallback<List<Server>>() {
            @Override
            public List<Server> callback(Session session) {
                Query query = session.createQuery("from Server");
                return query.list();
            }
        });
    }

    @Override
    public int count() {
        return new HibernateSession<Integer>().callback(new SessionCallback<Integer>() {
            @Override
            public Integer callback(Session session) {
                return ((Long) session.createQuery("select count(*) from Server").uniqueResult()).intValue();
            }
        });
    }
}
