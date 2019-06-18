package nl.sikken.bertrik.submitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.sikken.bertrik.json.LogRecord;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class LogReader {

	public LogReader() {
		
	}
	
	public List<LogRecord> read(File file) throws IOException {
		final BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
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
		} finally {
			reader.close();
		}
	}

	private LogRecord decodeLine(String line) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final LogRecord record = mapper.readValue(line, LogRecord.class);
		return record;
	}
	
}
