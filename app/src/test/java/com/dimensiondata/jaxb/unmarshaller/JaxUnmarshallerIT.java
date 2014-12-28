package com.dimensiondata.jaxb.unmarshaller;

import com.dimensiondata.jaxb.model.Server;
import com.dimensiondata.jaxb.model.Servers;
import com.dimensiondata.jaxb.unmarshaller.JaxUnmarshaller;
import com.dimensiondata.jaxb.unmarshaller.UnMarshaller;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JaxUnmarshallerIT {

	private UnMarshaller unmarshaller;

	public JaxUnmarshallerIT() {
		unmarshaller = new JaxUnmarshaller();
	}

	@Test
	public void shouldMarshallToServer() throws Exception {
		InputStream serversResourceAsStream = ClassLoader.getSystemResourceAsStream("servers.xml");
		Servers Servers = unmarshaller.unMarshal(Servers.class, serversResourceAsStream);

		assertThat(Servers.getServer().size(), is(3));

		Server server1 = Servers.getServer().get(0);
		assertThat(server1.getId(), is("1"));
		assertThat(server1.getName(), is("MyServerName"));

		Server server2 = Servers.getServer().get(1);
		assertThat(server2.getId(), is("2001"));
		assertThat(server2.getName(), is("foo"));

		Server server3 = Servers.getServer().get(2);
		assertThat(server3.getId(), is("5670001"));
		assertThat(server3.getName(), is("bar"));
	}

}
