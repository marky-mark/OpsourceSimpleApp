package com.dimensiondata.dataloader;

import com.dimensiondata.hibernate.HibernateServerServiceException;
import com.dimensiondata.jaxb.model.Servers;
import com.dimensiondata.jaxb.unmarshaller.JaxUnmarshaller;
import com.dimensiondata.jaxb.unmarshaller.UnMarshaller;
import com.dimensiondata.hibernate.ServerService;

import java.io.InputStream;

public class ServerDataLoaderImpl implements ServerDataLoader {

    public static final String SERVERS_XML_DEFAULT_LOCATION = "servers.xml";

    private UnMarshaller unmarshaller;
    private ServerService serverService;

    public ServerDataLoaderImpl(ServerService serverService) {
        this(new JaxUnmarshaller(), serverService);
    }

    public ServerDataLoaderImpl(UnMarshaller unmarshaller, ServerService serverService) {
        this.unmarshaller = unmarshaller;
        this.serverService = serverService;
    }

    public void loadServerDataFromDefaultLocation() {
        InputStream serversResourceAsStream = ClassLoader.getSystemResourceAsStream(SERVERS_XML_DEFAULT_LOCATION);

        if (serversResourceAsStream == null)
            throw new DataLoaderException("File does not exist, cannot load in data");

        Servers serverList = unmarshaller.unMarshal(Servers.class, serversResourceAsStream);

        try {
            serverService.create(ServerConverter.convert(serverList));
        } catch (HibernateServerServiceException e) {
            throw new DataLoaderException(e);
        }
    }
}
