package com.srikanth.rest;

import static org.junit.Assert.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FirstRestResourceTest {

	private HttpServer server;
	private WebTarget target;
	
	@Before
	public void setUp() throws Exception {
		server = Main.startServer();
		Client client = ClientBuilder.newClient();
		target = client.target(Main.BASE_URI);
	}
	@After
	public void tearDown() throws Exception {
		server.shutdownNow();
	}
	@Test
	public void testGetIt() {
		String responseMsg = target.path("firstrestresource").request().get(String.class);
		assertEquals("Got it!", responseMsg);
	}
}
