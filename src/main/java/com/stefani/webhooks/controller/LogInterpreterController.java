package com.stefani.webhooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stefani.webhooks.service.LogReader;

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
	private LogReader logReader;

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read() {
		return logReader.read();
	}

}
