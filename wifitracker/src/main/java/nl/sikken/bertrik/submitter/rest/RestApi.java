package nl.sikken.bertrik.submitter.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.sikken.bertrik.submitter.rest.dto.TrackerConfig;
import nl.sikken.bertrik.submitter.rest.dto.TrackerRecord;

@Path("/tracker/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface RestApi {

	@POST
	@Path("/upload")
	public void upload(TrackerRecord record);
	
	@GET
	@Path("/config/{id}")
	TrackerConfig config(@PathParam("id") String deviceId);

	@GET
	@Path("/ping")
	String ping();

}
