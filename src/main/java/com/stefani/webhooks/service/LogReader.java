package com.stefani.webhooks.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefani.webhooks.model.LogDataInfo;
import com.stefani.webhooks.model.StatusResponded;
import com.stefani.webhooks.model.UrlRequested;
import com.stefani.webhooks.model.WebhookLog;
import com.stefani.webhooks.service.file.FileInterpreter;

@Service
public class LogReader {

	@Autowired
	private FileInterpreter interpreter;

	public LogDataInfo read() throws IOException, URISyntaxException {

		WebhookLog infoList = interpreter.understand();

		List<UrlRequested> urlsList = new ArrayList<>();
		
			UrlRequested url = new UrlRequested();
			Set<String> topUrl = new HashSet<>(infoList.getRequestTo());

			for (String key : topUrl) {
				url.setQuantity(Collections.frequency(infoList.getRequestTo(), key));
				url.setUrl(key);
				urlsList.add(url);
			}
			

		List<StatusResponded> statusList = new ArrayList<>();
			StatusResponded status = new StatusResponded();
			Set<String> uniqueStatus = new HashSet<>(infoList.getResponseStatus());
			for (String key : uniqueStatus) {
				status.setQuantity(Collections.frequency(infoList.getResponseStatus(), key));
				status.setStatus(key);
				statusList.add(status);
			}

		LogDataInfo logDataInfo = new LogDataInfo();
		logDataInfo.setUrlData(urlsList);
		logDataInfo.setStatusData(statusList);

		return logDataInfo;
	}

}
