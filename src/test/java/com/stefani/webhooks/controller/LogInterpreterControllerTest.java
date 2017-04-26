package com.stefani.webhooks.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.stefani.webhooks.service.LogReader;


@RunWith(MockitoJUnitRunner.class)
public class LogInterpreterControllerTest {
	
	@InjectMocks
	private LogInterpreterController controller;
	@Mock
	private LogReader logReader;
	
	@Test
	public void deveExecutarChamadaDeExecucao() throws IOException, URISyntaxException {

		// when
		controller.read();

		// then
		Mockito.verify(logReader, Mockito.only()).read();

	}

}
