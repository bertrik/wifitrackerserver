package nl.sikken.bertrik.submitter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import nl.sikken.bertrik.gls.GlsAccessPoint;
import nl.sikken.bertrik.gls.GlsLocation;
import nl.sikken.bertrik.gls.GlsRequest;
import nl.sikken.bertrik.gls.GlsResponse;
import nl.sikken.bertrik.json.LogRecord;
import nl.sikken.bertrik.json.LogScan;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class Submitter {

	private static final Logger LOG = LoggerFactory.getLogger(Submitter.class);

	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		
		LOG.info("Starting!");

		Submitter submitter = new Submitter();
		String apiKey = "<your google API key here>";
		URI url = new URI("https://www.googleapis.com/geolocation/v1/geolocate?key=" + apiKey);
//		final URI url = new URI("https://location.services.mozilla.com/v1/geolocate?key=test");
		File csv = new File("20160423.csv");
		submitter.run(new File("data/20160423/20160423.log"), url, csv);
	}

	private void run(File file, URI url, File csvFile) throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();

		// read file
		LogReader reader = new LogReader();
		List<LogRecord> records = reader.read(file);

		// convert and submit
		OutputStream os = new FileOutputStream(csvFile);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
		for (LogRecord record : records) {
			// create request
			GlsRequest request = new GlsRequest();
			for (LogScan scan : record.getScan()) {
				GlsAccessPoint ap = new GlsAccessPoint(scan.getMac(), scan.getRssi());
				request.addAccessPoint(ap);
			}
			String json = mapper.writeValueAsString(request);
//			byte[] postData = json.getBytes("UTF-8");

			// submit
			String response = doPost(url, json);
			System.out.println("response = " + response);
			try {
				GlsResponse glsResponse = mapper.readValue(response, GlsResponse.class);
				GlsLocation glsLocation = glsResponse.getLocation();

				// output to file
				String line = String.format(Locale.US, "%s,%s,%f,%f,%f",
						record.getDeviceId(), record.getDateTime(), glsLocation.getLat(), glsLocation.getLng(), glsResponse.getAccuracy());
				writer.write(line);
				writer.newLine();
			} catch (IOException e) {
				System.out.println("Error deserializing: " + e.getMessage());
			}

			Thread.sleep(100);
		}
		writer.close();
		os.close();
	}

	private String doPost(URI url, String data) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		StringEntity input = new StringEntity(data);
		input.setContentType("application/json");
		post.setEntity(input);

		HttpResponse response = client.execute(post);
		InputStream is = response.getEntity().getContent();
		StringBuilder sb = new StringBuilder();
		while (true) {
			int c = is.read();
			sb.append((char) c);
			if (c < 0) {
				break;
			}
		}
		client.close();
		return sb.toString();
	}

}
