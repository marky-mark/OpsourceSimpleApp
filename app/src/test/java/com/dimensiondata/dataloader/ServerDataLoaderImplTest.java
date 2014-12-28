package com.dimensiondata.dataloader;

import com.dimensiondata.hibernate.server.ServerService;
import com.dimensiondata.jaxb.model.Server;
import com.dimensiondata.jaxb.model.Servers;
import com.dimensiondata.jaxb.unmarshaller.UnMarshaller;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.is;

public class ServerDataLoaderImplTest {

    private ServerDataLoader serverDataLoader;

    private UnMarshaller mockedJaxUnmarshaller;
    private ServerService mockedDatabaseServerService;

    @Before
    public void setup() {
        mockedJaxUnmarshaller = mock(UnMarshaller.class);
        mockedDatabaseServerService = mock(ServerService.class);
        serverDataLoader = new ServerDataLoaderImpl(mockedJaxUnmarshaller, mockedDatabaseServerService);
    }

    @Test
    public void shouldReadServersFromDefaultLocationAndInsertIntoDatabase() throws Exception {
        List<Server> serverData = new ArrayList<Server>();
        serverData.add(new Server("foo", "bar"));
        serverData.add(new Server("fooz", "barz"));
        Servers servers = new Servers(serverData);
        when(mockedJaxUnmarshaller.unMarshal(eq(Servers.class), any(InputStream.class))).thenReturn(servers);

        serverDataLoader.loadServerDataFromDefaultLocation();

        ArgumentCaptor listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockedDatabaseServerService)
                .create((List<com.dimensiondata.hibernate.server.entity.Server>) listArgumentCaptor.capture());
        List<com.dimensiondata.hibernate.server.entity.Server> persistedData
                = (List<com.dimensiondata.hibernate.server.entity.Server>) listArgumentCaptor.getValue();

        assertThat(persistedData.size(), is(2));
        assertThat(persistedData.get(0).getId(), is("foo"));
        assertThat(persistedData.get(0).getName(), is("bar"));
        assertThat(persistedData.get(1).getId(), is("fooz"));
        assertThat(persistedData.get(1).getName(), is("barz"));
    }
}
