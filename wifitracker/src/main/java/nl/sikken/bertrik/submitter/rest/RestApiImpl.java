package nl.sikken.bertrik.submitter.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.sikken.bertrik.submitter.rest.dto.TrackerConfig;
import nl.sikken.bertrik.submitter.rest.dto.TrackerRecord;

public final class RestApiImpl implements RestApi {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestApiImpl.class);
	
	public RestApiImpl() {
		LOG.info("Starting RestApiImpl");
	}

	@Override
	public TrackerConfig config(String deviceId) {
		LOG.info("Incoming config request for {}", deviceId);
		return new TrackerConfig();
	}

	@Override
	public void upload(TrackerRecord record) {
		LOG.info("Incoming upload request");
	}

	@Override
	public String ping() {
		LOG.info("Incoming ping request");
		return "pong!";
	}

}
