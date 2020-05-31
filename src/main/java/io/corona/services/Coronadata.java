package io.corona.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.corona.models.Location;


@Service
public class Coronadata {
	
	private List<Location> stat=new ArrayList<>();
	
	private static String BASE_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	@PostConstruct
	@Scheduled(cron = "0 * * * * *")
	public void fetchData() throws IOException, InterruptedException {

		List<Location> nstat=new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder()
		.uri(URI.create(BASE_URL))
		.build();
		
		HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
		
		//System.out.println("hey");
		StringReader in = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			Location location=new Location();
			location.setState(record.get("Province/State"));
			location.setCountry(record.get("Country/Region"));

			    
			
		int latest=Integer.parseInt(record.get(record.size()-1));
		int previous=Integer.parseInt(record.get(record.size()-2));
		location.setLastTotal(latest);
		location.setDiff(latest-previous);
			
			nstat.add(location);

		}
		this.stat=nstat;
			
	}

	public List<Location> getStat() {
		return stat;
	}

	
	

}
