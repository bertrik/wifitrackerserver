package nl.sikken.bertrik.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogScan {
	
	@JsonProperty("mac")
	private String mac;

	@JsonProperty("rssi")
	private int rssi;
	
	public String getMac() {
		return mac;
	}

	public int getRssi() {
		return rssi;
	}

	@Override
	public String toString() {
		return String.format("(mac=%s,rssi=%d)", mac, rssi);
	}
	
}
