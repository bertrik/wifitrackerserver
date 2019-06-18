package nl.sikken.bertrik.submitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.sikken.bertrik.json.LogRecord;

public final class LogReader {

	public LogReader() {
		
	}
	
	public List<LogRecord> read(File file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
			final List<LogRecord> list = new ArrayList<LogRecord>();
			
			while (true) {
				final String line = reader.readLine();
				if (line == null) {
					break;
				}
				final LogRecord record = decodeLine(line);
				if (record == null) {
					break;
				}
				list.add(record);
			}
			return list;
		}
	}

	private LogRecord decodeLine(String line) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final LogRecord record = mapper.readValue(line, LogRecord.class);
		return record;
	}
	
}
