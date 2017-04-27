package com.stefani.webhooks.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.stefani.webhooks.model.Line;
import com.stefani.webhooks.model.LogDataInfo;
import com.stefani.webhooks.model.WebhookLog;

@RunWith(MockitoJUnitRunner.class)
public class FrequencyDefinerTest {

	@InjectMocks
	private FrequencyDefiner definer;

	@Test
	public void deveExecutarChamadaDeExecucao() throws IOException, URISyntaxException {

		LogDataInfo info = definer.defineFrequency(getWebhook());

		assertTrue("test.com".equals(info.getUrlData().get(0).getUrl()));
		assertTrue("test.com.br".equals(info.getUrlData().get(1).getUrl()));
		assertTrue(info.getUrlData().get(0).getQuantity().equals(2));
		assertTrue(info.getUrlData().get(1).getQuantity().equals(1));
		assertTrue("200".equals(info.getStatusCode().get(0).getStatus()));
		assertTrue("201".equals(info.getStatusCode().get(1).getStatus()));
		assertTrue(info.getUrlData().get(0).getQuantity().equals(2));
		assertTrue(info.getUrlData().get(1).getQuantity().equals(1));
	}

	private WebhookLog getWebhook() {
		Line l1 = new Line();
		l1.setRequestTo("test.com");
		l1.setResponseStatus("200");
		Line l2 = new Line();
		l2.setRequestTo("test.com");
		l2.setResponseStatus("201");
		Line l3 = new Line();
		l3.setRequestTo("test.com.br");
		l3.setResponseStatus("200");
		List<Line> line = Arrays.asList(l1, l2, l3);
		WebhookLog wh = new WebhookLog();
		wh.setLine(line);
		return wh;
	}

}
