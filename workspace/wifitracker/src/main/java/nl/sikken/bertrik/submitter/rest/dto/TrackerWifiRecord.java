package nl.sikken.bertrik.submitter.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TrackerWifiRecord {
	@JsonProperty("mac")
	private String mac;

	@JsonProperty("rssi")
	private int rssi;

	@SuppressWarnings("unused")
	private TrackerWifiRecord() {
		// empty constructor for Jackson
	}

	public TrackerWifiRecord(String mac, int rssi) {
		this.mac = mac;
		this.rssi = rssi;
	}

}
