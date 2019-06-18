package nl.sikken.bertrik.submitter.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TrackerConfig {
	
	@JsonProperty()
	private String dummy = "dummy";
	
	public TrackerConfig() {
		// jackson constructor
	}

}
