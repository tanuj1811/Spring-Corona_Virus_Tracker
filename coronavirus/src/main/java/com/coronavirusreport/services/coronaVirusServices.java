package com.coronavirusreport.services;

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

import com.coronavirusreport.models.locationStats;

@Service
public class coronaVirusServices {
	
	private List<locationStats> allstats = new ArrayList<>();
	
	private static String VIRUS_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	

	@PostConstruct
	@Scheduled(cron = "1 1 1 * * *")
	public void fetchDataFromURL() throws IOException, InterruptedException {
		List<locationStats> newstats = new ArrayList<>();
		List<locationStats> india = new ArrayList<>();
 		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_URL))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvReader = new StringReader(response.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		for (CSVRecord record : records) {
			locationStats ls = new locationStats();
			int new_Cases = Integer.parseInt(record.get(record.size()-1)) - Integer.parseInt(record.get(record.size()-2));
		    ls.setCOUNTRY(record.get("Country/Region"));
		    ls.setSTATE(record.get("Province/State"));
		    ls.setTotal_Cases(Integer.parseInt(record.get(record.size() -1)));
		    ls.setNew_Cases(new_Cases);
		    if(record.get("Country/Region") == "India") {
		    	india.add(ls);
		    }
		    newstats.add(ls);

		}
		this.allstats = newstats;
	}
	
	
	public List<locationStats> getAllstats() {
		return allstats;
	}
	
	
	
}
