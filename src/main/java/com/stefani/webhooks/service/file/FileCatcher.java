package com.stefani.webhooks.service.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * Responsávelo pela leitura do arquivo na pasta
 * 
 * @author stefani
 *
 */
@Component
public class FileCatcher {

	public List<String> search(String fileName) throws IOException, URISyntaxException {
		return Files.lines(Paths.get(getClass().getResource("/" + fileName).toURI())).collect(Collectors.toList());
	}

}
