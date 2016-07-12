package nl.sikken.bertrik.gls;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class GlsRequest {

	@JsonProperty("considerIp")
	private String considerIp = "false";
	
	@JsonProperty("wifiAccessPoints")
	private List<GlsAccessPoint> wifiAccessPoints = new ArrayList<GlsAccessPoint>();
	
	public void addAccessPoint(GlsAccessPoint ap) {
		wifiAccessPoints.add(ap);
	}
	
}
