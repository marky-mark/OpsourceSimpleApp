package com.dimensiondata.dataloader;


import com.dimensiondata.jaxb.model.Servers;
import com.dimensiondata.hibernate.entity.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerConverter {

    public static List<Server> convert(Servers servers) {
        List<Server> databaseServers = new ArrayList<Server>();

        if (servers == null)
            return databaseServers;

        for (com.dimensiondata.jaxb.model.Server server : servers.getServer()) {
            databaseServers.add(new Server(server.getId(), server.getName()));
        }

        return databaseServers;
    }
}
