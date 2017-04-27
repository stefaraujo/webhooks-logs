package com.stefani.webhooks.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.stefani.webhooks.model.Line;
import com.stefani.webhooks.model.LogDataInfo;
import com.stefani.webhooks.model.StatusCode;
import com.stefani.webhooks.model.UrlRequested;
import com.stefani.webhooks.model.WebhookLog;

/**
 * Responsável pelo calculo da definicão de frequencia de Urls e webhooks codes no log
 * @author stefani
 *
 */
@Component
public class FrequencyDefiner {

	public LogDataInfo defineFrequency(WebhookLog logLines) {
		List<String> urls = new ArrayList<>();
		List<String> codes = new ArrayList<>();

		for (Line line : logLines.getLine()) {
			urls.add(line.getRequestTo());
			codes.add(line.getResponseStatus());
		}

		List<UrlRequested> urlsRequested = countUrls(urls);

		List<StatusCode> statusCode = countWebhooks(codes);

		LogDataInfo logInfo = new LogDataInfo();
		logInfo.setUrlData(urlsRequested);
		logInfo.setStatusCode(statusCode);

		return logInfo;
	}

	private List<StatusCode> countWebhooks(List<String> codes) {
		List<StatusCode> statusCode = new ArrayList<>();
		Set<String> codesFrequency = new HashSet<>(codes);
		for (String string : codesFrequency) {
			Integer quantity = Collections.frequency(codes, string);
			statusCode.add(new StatusCode(string, quantity));
		}
		return statusCode;
	}

	private List<UrlRequested> countUrls(List<String> urls) {
		List<UrlRequested> urlsRequested = new ArrayList<>();
		Set<String> urlsFrequency = new HashSet<>(urls);
		for (String string : urlsFrequency) {
			Integer quantity = Collections.frequency(urls, string);
			urlsRequested.add(new UrlRequested(string, quantity));
		}
		return urlsRequested;
	}
}
