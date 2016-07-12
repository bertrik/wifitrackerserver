package nl.sikken.bertrik.gls;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class GlsAccessPoint {
	@JsonProperty("macAddress")
	private final String macAddress;
	
	@JsonProperty("signalStrength")
	private final int signalStrength;
	
	public GlsAccessPoint(String mac, int rssi) {
		this.macAddress = mac;
		this.signalStrength = rssi;
	}
	
}
