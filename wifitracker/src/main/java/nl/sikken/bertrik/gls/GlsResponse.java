package nl.sikken.bertrik.gls;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlsResponse {
	@JsonProperty("location")
	private GlsLocation location;
	
	@JsonProperty("accuracy")
	private double accuracy;

	public GlsLocation getLocation() {
		return location;
	}

	public double getAccuracy() {
		return accuracy;
	}
	
}
