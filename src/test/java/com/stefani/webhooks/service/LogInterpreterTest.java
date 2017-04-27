package com.stefani.webhooks.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stefani.webhooks.model.LogDataInfo;
import com.stefani.webhooks.model.StatusCode;
import com.stefani.webhooks.model.UrlRequested;
import com.stefani.webhooks.model.WebhookLog;
import com.stefani.webhooks.service.file.WebhookFileReader;


@RunWith(MockitoJUnitRunner.class)
public class LogInterpreterTest {

	@InjectMocks
	private LogInterpreter logInterpreter;
	@Mock
	private WebhookFileReader fileReader;
	@Mock
	private FrequencyDefiner definer;

	@Test
	public void deveExecutarChamadaDeExecucao() throws IOException, URISyntaxException {

		Mockito.when(fileReader.read()).thenReturn(new WebhookLog());
		Mockito.when(definer.defineFrequency(Matchers.any())).thenReturn(getLogDataInfo());

		// when
		LogDataInfo info = logInterpreter.read();

		// then
		Mockito.verify(fileReader, Mockito.only()).read();
		Mockito.verify(definer, Mockito.only()).defineFrequency(Mockito.any());
		assertTrue("http://stefani.com".equals(info.getUrlData().get(0).getUrl()));
	}

	private LogDataInfo getLogDataInfo() {

		LogDataInfo info = new LogDataInfo();
		info.setStatusCode(Arrays.asList(new StatusCode("200", 3)));
		info.setUrlData(
				Arrays.asList(new UrlRequested("http://teste.com", 3), new UrlRequested("http://stef.com", 12), new UrlRequested("http://stefani.com", 2)));
		return info;
	}

}
