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
			List<LogRecord> list = new ArrayList<LogRecord>();
			
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				LogRecord record = decodeLine(line);
				if (record == null) {
					break;
				}
				list.add(record);
			}
			return list;
		}
	}

	private LogRecord decodeLine(String line) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		LogRecord record = mapper.readValue(line, LogRecord.class);
		return record;
	}
	
}
