package com.dimensiondata.hibernate;

import com.dimensiondata.hibernate.entity.Server;

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
