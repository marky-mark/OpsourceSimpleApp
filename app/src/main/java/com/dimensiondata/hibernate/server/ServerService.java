package com.dimensiondata.hibernate.server;

import com.dimensiondata.hibernate.server.entity.Server;

import java.util.List;

public interface ServerService {
    void create(Server server);
    void create(List<Server> servers);
    Server getById(String id);
    void update(Server server);
    void delete(String id);
    List<Server> getAll();
    int count();
}
