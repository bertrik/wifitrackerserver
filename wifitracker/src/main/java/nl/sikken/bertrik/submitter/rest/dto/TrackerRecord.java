package nl.sikken.bertrik.submitter.rest.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class TrackerRecord {

	@JsonProperty("deviceid")
	private String deviceId;

	@JsonProperty("datetime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	private Date dateTime;

	@JsonProperty("wifi")
	private final List<TrackerWifiRecord> wifi = new ArrayList<TrackerWifiRecord>();

	@SuppressWarnings("unused")
	private TrackerRecord() {
		// Jackson constructor
	}
	
	public TrackerRecord(String deviceId, Date dateTime) {
		this.deviceId = deviceId;
		this.dateTime = new Date(dateTime.getTime());
	}

	public void add(TrackerWifiRecord wifiRecord) {
		wifi.add(wifiRecord);
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public Date getDateTime() {
		return new Date(dateTime.getTime());
	}

	public List<TrackerWifiRecord> getWifi() {
		return wifi;
	}

	
	
}

