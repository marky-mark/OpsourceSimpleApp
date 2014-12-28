package com.dimensiondata.hibernate.server;

import com.dimensiondata.hibernate.BaseDatabaseIT;
import com.dimensiondata.hibernate.HibernateServiceException;
import com.dimensiondata.hibernate.HibernateUtil;
import com.dimensiondata.hibernate.server.entity.Server;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class HibernateServerServiceIT extends BaseDatabaseIT {

    private ServerService hibernateServerService;

    public HibernateServerServiceIT() {
        hibernateServerService = new HibernateServerService();
    }

    @After
    public void tearDown() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteAllQuery = session.createSQLQuery("delete from Server");
        deleteAllQuery.executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void shouldWriteEntity() throws Exception {
        Server server = new Server("123", "test");
        hibernateServerService.create(server);

        Server serverInDb = hibernateServerService.getById("123");
        assertThat(server, is(serverInDb));
    }

    @Test(expected = HibernateServiceException.class)
    public void shouldThrowExceptionWhenDuplicateEntry() throws Exception {
        Server server = new Server("123", "test");
        hibernateServerService.create(server);
        hibernateServerService.create(server);
    }

    @Test
    public void shouldWriteListOfEntities() throws Exception {
        List<Server> serverList = new ArrayList<Server>();
        serverList.add(new Server("123", "test1"));
        serverList.add(new Server("1234", "test2"));
        serverList.add(new Server("12345", "test3"));
        hibernateServerService.create(serverList);

        Server serverInDb = hibernateServerService.getById("123");
        assertThat(serverList.get(0), is(serverInDb));
        serverInDb = hibernateServerService.getById("1234");
        assertThat(serverList.get(1), is(serverInDb));
        serverInDb = hibernateServerService.getById("12345");
        assertThat(serverList.get(2), is(serverInDb));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {
        Server server = new Server("1234", "test");
        hibernateServerService.create(server);
        server.setName("testUpdated");
        hibernateServerService.update(server);

        Server serverInDb = hibernateServerService.getById("1234");
        assertThat(server, is(serverInDb));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {
        Server server = new Server("pleaseDelete", "test");
        hibernateServerService.create(server);

        hibernateServerService.delete("pleaseDelete");

        Server serverInDb = hibernateServerService.getById("pleaseDelete");
        assertThat(serverInDb, is(nullValue()));
    }

    @Test
    public void shouldListEntities() throws Exception {
        hibernateServerService.create(new Server("123", "test1"));
        hibernateServerService.create(new Server("1234", "test2"));

        List<Server> serversInDb = hibernateServerService.getAll();

        assertThat(serversInDb.size(), is(2));
    }

    @Test
    public void shouldCountTheEntities() throws Exception {
        hibernateServerService.create(new Server("123", "test"));
        hibernateServerService.create(new Server("1234", "test2"));
        hibernateServerService.create(new Server("12345", "test3"));

        int numberOfEntities = hibernateServerService.count();
        assertThat(numberOfEntities, is(3));
    }
}
