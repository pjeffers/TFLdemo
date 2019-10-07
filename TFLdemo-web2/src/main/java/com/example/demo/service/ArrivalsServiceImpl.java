package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ArrivalRecord;
import com.example.demo.model.ArrivalSchedule;

@Service
public class ArrivalsServiceImpl implements ArrivalsService{
	/* (non-Javadoc)
	 * @see com.example.demo.service.ArrivalsService#getTubeArrivals(java.lang.String)
	 */
	@Override
	public ArrivalSchedule getTubeArrivals(String  searchString) {
		
		
         // URL for arrivals at great portland street
		String url="https://api.tfl.gov.uk/StopPoint/940GZZLUGPS/Arrivals?mode=tube";
		 
		 //retrieve the json response for the URL
		 RestTemplate restTemplate = new RestTemplate();
		 String resp = restTemplate.getForObject(url, String.class);

		 //get the json pasrser
		 JsonParser springParser = JsonParserFactory.getJsonParser();
		 
		 //parse the response into a list of objects (Maps)
		List<Object> list = springParser.parseList(resp);
		
		//create an empty list of arrival records
		List<ArrivalRecord> arrivalsList =new ArrayList<>();

		// iterate through the list of objects casting each one to a map and then retrieve the arrival
		// record details from each map. Create a new arrival record for each set of details and add
		// it to the arrivals list.
		for(Object o : list) {
			if(o instanceof Map) {
				
				Map<String,Object> map = (Map<String,Object>) o;				
				//Map map =( Map) o;
				
				// get the arrival record
				String destination 		= (String) map.get("destinationName");
				String platformName     = (String) map.get("platformName");
				Integer timeToStation 	= (Integer) map.get("timeToStation");
				
				ArrivalRecord arrivalRecord =new ArrivalRecord(destination, platformName, timeToStation);
				arrivalsList.add(arrivalRecord);

			}

		}	
		Collections.sort(arrivalsList);

	  ArrivalSchedule arrivalSchedule = new ArrivalSchedule("",arrivalsList);
	  
	  return arrivalSchedule;
	  
	}
	
	public String getStationCode(String searchString ){
		
		
		return searchString;
		
	}

}
