package com.stefani.webhooks.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefani.webhooks.model.LogDataInfo;
import com.stefani.webhooks.model.StatusCode;
import com.stefani.webhooks.model.UrlRequested;
import com.stefani.webhooks.model.WebhookLog;
import com.stefani.webhooks.service.file.WebhookFileReader;

/**
 * Responsável pela seleção das 3 urls para onde foram enviados os webhook e os
 * status code (e suas respectivas quantidades) retornado pelos servidores dos
 * clientes
 * 
 * @author stefani
 *
 */
@Service
public class LogInterpreter {

	@Autowired
	private WebhookFileReader fileReader;
	@Autowired
	private FrequencyDefiner definer;

	public LogDataInfo read() throws IOException, URISyntaxException {

		WebhookLog log = fileReader.read();

		LogDataInfo logInfo = definer.defineFrequency(log);

		sortLogInfos(logInfo);

		return findMostAccessed(logInfo);

	}

	private LogDataInfo findMostAccessed(LogDataInfo logInfo) {
		LogDataInfo mostAccessed = new LogDataInfo();
		mostAccessed
				.setUrlData(logInfo.getUrlData().subList(logInfo.getUrlData().size() - 3, logInfo.getUrlData().size()));
		mostAccessed.setStatusCode(logInfo.getStatusCode());
		return mostAccessed;
	}

	private void sortLogInfos(LogDataInfo logInfo) {
		logInfo.getUrlData().sort(Comparator.comparing(UrlRequested::getQuantity));
		logInfo.getStatusCode().sort(Comparator.comparing(StatusCode::getQuantity));
	}

}
