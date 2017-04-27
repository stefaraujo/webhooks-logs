package com.stefani.webhooks.service.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stefani.webhooks.model.Line;
import com.stefani.webhooks.model.WebhookLog;

/**
 * Responsavel pela interpretação do log de webhook
 * 
 * @author stefani
 *
 */
@Component
public class WebhookFileReader {
	@Autowired
	private FileCatcher fileCatcher;
	private static final String URL_PATTERN = "(ftp:\\/\\/|www\\.|https?:\\/\\/){1}[a-zA-Z0-9u00a1-\\uffff0-]{2,}\\.[a-zA-Z0-9u00a1-\\uffff0-]{2,}(\\S*)";
	private static final String CODE_PATTERN = "response_status(.*)";

	public WebhookLog read() throws IOException, URISyntaxException {
		String fileName = "log.txt";
		List<String> list = fileCatcher.search(fileName);

		List<Line> lines = new ArrayList<>();

		for (String string : list) {
			Line line = new Line();
			Pattern urlP = Pattern.compile(URL_PATTERN);
			Matcher urlM = urlP.matcher(string);
			if (urlM.find()) {
				line.setRequestTo(urlM.group(0).split("\"")[0]);
			}

			Pattern respP = Pattern.compile(CODE_PATTERN);
			Matcher respM = respP.matcher(string);
			if (respM.find()) {
				line.setResponseStatus(respM.group(0).split("\"")[1]);
			}

			lines.add(line);
		}
		WebhookLog logInfo = new WebhookLog();
		logInfo.setLine(lines);
		return logInfo;
	}

}
