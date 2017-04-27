package com.stefani.webhooks.service.file;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stefani.webhooks.model.WebhookLog;

@RunWith(MockitoJUnitRunner.class)
public class WebhookFileReaderTest {
	@InjectMocks
	private WebhookFileReader reader;
	@Mock
	private FileCatcher fileCatcher;

	@Test
	public void deveExecutarChamadaDeExecucao() throws IOException, URISyntaxException {

		Mockito.when(fileCatcher.search(Mockito.anyString())).thenReturn(Arrays.asList(
				"level=info response_body=\" request_to=\"https://grotesquemoon.de\" response_headers=map[Server:[nginx] X-Request-Id:[1381e8cb388db085cdc3bac457dab9bf] Date:[Tue, 07 Jul 2015 18:29:52 GMT] Content-Type:[text/html; charset=utf-8] X-Powered-By:[Phusion Passenger (mod_rails/mod_rack) 3.0.17] X-Rack-Cache:[invalidate, pass] X-Runtime:[0.034645] Connection:[keep-alive] Set-Cookie:[X-Mapping-fjhppofk=A67D55AC8119CAD031E35EC35B4BCFFD; path=/] Keep-Alive:[timeout=20] Cache-Control:[max-age=0, private, must-revalidate] Status:[200] Etag:[7215ee9c7d9dc229d2921a40e899ec5f] Vary:[Accept-Encoding] X-Ua-Compatible:[IE=Edge,chrome=1]] response_status=\"201\"",
				"level=info response_body=\" request_to=\"https://woodenoyster.com.br\" response_headers=map[Content-Type:[application/json; charset=utf-8] Connection:[keep-alive] Runscope-Message-Id:[fb814900-c6bc-4002-8007-e7e06b52abb0] Access-Control-Allow-Credentials:[true] Server:[Runscope-Gateway/1.0]] response_status=\"503\""));

		// when
		WebhookLog info = reader.read();

		// then
		Mockito.verify(fileCatcher, Mockito.only()).search(Mockito.anyString());
		assertTrue("https://grotesquemoon.de".equals(info.getLine().get(0).getRequestTo()));
		assertTrue("201".equals(info.getLine().get(0).getResponseStatus()));
		assertTrue("https://woodenoyster.com.br".equals(info.getLine().get(1).getRequestTo()));
		assertTrue("503".equals(info.getLine().get(1).getResponseStatus()));
	}

}
