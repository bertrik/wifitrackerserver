package nl.sikken.bertrik.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class LogRecord {
	@JsonProperty("deviceid")
	private String deviceId;
	
	@JsonProperty("datetime")
	private String dateTime;
	
	@JsonProperty("scan")
	private List<LogScan> scan;

	public String getDeviceId() {
		return deviceId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public List<LogScan> getScan() {
		return scan;
	}

	@Override
	public String toString() {
		return String.format("{deviceid=%s,datetime=%s,scan=(%d elements))", deviceId, dateTime, scan.size());
	}
	
	private LogRecord() {
	}
	
}
