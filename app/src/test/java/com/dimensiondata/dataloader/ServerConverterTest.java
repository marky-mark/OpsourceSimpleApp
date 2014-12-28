package com.dimensiondata.dataloader;

import com.dimensiondata.jaxb.model.Server;
import com.dimensiondata.jaxb.model.Servers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ServerConverterTest {

    @Test
    public void shouldReturnEmptyListIfNull() throws Exception {
        List<com.dimensiondata.hibernate.server.entity.Server> serverList = ServerConverter.convert(null);
        assertTrue(serverList.isEmpty());
    }

    @Test
    public void shouldConvertListOfServers() throws Exception {
        List<Server> serversToConvert = new ArrayList<Server>();
        serversToConvert.add(new Server("foo", "bar"));
        serversToConvert.add(new Server("fooz", "barz"));
        Servers servers = new Servers(serversToConvert);

        List<com.dimensiondata.hibernate.server.entity.Server> serverList = ServerConverter.convert(servers);

        assertThat(serverList.size(), is(2));
        assertThat(serverList.get(0).getId(), is("foo"));
        assertThat(serverList.get(0).getName(), is("bar"));
        assertThat(serverList.get(1).getId(), is("fooz"));
        assertThat(serverList.get(1).getName(), is("barz"));
    }

    @Test
    public void shouldConvertEmptyList() throws Exception {
        List<com.dimensiondata.hibernate.server.entity.Server> serverList = ServerConverter.convert(new Servers());
        assertTrue(serverList.isEmpty());
    }
}
