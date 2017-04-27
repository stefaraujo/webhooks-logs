package com.stefani.webhooks.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stefani.webhooks.model.LogDataInfo;
import com.stefani.webhooks.service.LogInterpreter;

/**
 * Endpoint responsável por receber requisição de inicio de interpretação de
 * logs
 * 
 * @author stefani
 *
 */
@RestController
@RequestMapping("/logs")
public class LogInterpreterController {

	@Autowired
	private LogInterpreter logReader;

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public LogDataInfo read() throws IOException, URISyntaxException {
		return logReader.read();
	}

}
