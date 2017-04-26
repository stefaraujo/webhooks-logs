package com.stefani.webhooks.service.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.stefani.webhooks.model.WebhookLog;

/**
 * Responsável por interpretar dados do arquivo de log
 * @author stefani
 *
 */
@Component
public class FileInterpreter {

	public WebhookLog understand() throws IOException, URISyntaxException {
		String fileName = "log.txt";

		List<String> list = Files.lines(Paths.get(getClass().getResource("/" + fileName).toURI()))
				.collect(Collectors.toList());
		WebhookLog logInfo = new WebhookLog();
		List<String> urlList = new ArrayList<>();
		List<String> statusList = new ArrayList<>();

		for (String string : list) {
			Pattern urlP = Pattern.compile(
					"(ftp:\\/\\/|www\\.|https?:\\/\\/){1}[a-zA-Z0-9u00a1-\\uffff0-]{2,}\\.[a-zA-Z0-9u00a1-\\uffff0-]{2,}(\\S*)");
			Matcher urlM = urlP.matcher(string);
			if (urlM.find()) {
				urlList.add(urlM.group(0));
			}

			Pattern respP = Pattern.compile("response_status(.*)");
			Matcher respM = respP.matcher(string);
			if (respM.find()) {
				String status = respM.group(0).split("\"")[1];
				statusList.add(status);
			}

			logInfo.setRequestTo(urlList);
			logInfo.setResponseStatus(statusList);
		}
		return logInfo;
	}

}
