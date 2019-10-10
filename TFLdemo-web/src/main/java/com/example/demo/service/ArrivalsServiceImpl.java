package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ArrivalRecord;
import com.example.demo.model.ArrivalSchedule;

@Service
public class ArrivalsServiceImpl implements ArrivalsService{
	/* This service returns the arrival schedule for Great Portland Street tube station.
 	 * Although a station search string is passed, the URL for arrivals at Great Portland Street tube 
     * station is currently hard-coded, this is OK as 
     * it is unlikely to change. The URL contains the code for the station: 940GZZLUGPS, obtained using the  
	 * stop point search URL:"https://api.tfl.gov.uk/StopPoint/Search?query=Great Portland Street&modes=tube"
	 * with Postman. This URL returns the station details matching a search string. in the example url above
	 * it would return only one name. Future improvements, which would allow the retrieval of arrival details 
	 * for any station, include implementing the method getStationDetails to return the name and ID
	 * of the station we want arrivals for. the station ID could then be added to the arrivals URL dynamically
	 * In addition to this the (full) name of the station could be passed back to the view page for display.
	 * The advantage of the current hard-coding is that it only requires one HTTP call rather than 2 (one to 
	 * retrieve the station details and one retrieve the arrivals).
	 */
	@Override
	public ArrivalSchedule getTubeArrivals(String  searchString) {
		
		// Great Portland Street Arrivals URL (it has ID 940GZZLUGPS) 
		String url="https://api.tfl.gov.uk/StopPoint/940GZZLUGPS/Arrivals?mode=tube";
		
		 
		 // retrieve the JSON response for the URL
		 RestTemplate restTemplate = new RestTemplate();
		 String resp = restTemplate.getForObject(url, String.class);

		 // get the JSON pasrser
		 JsonParser springParser = JsonParserFactory.getJsonParser();
		 
		 // parse the response into a List of objects (Maps)
		List<Object> list = springParser.parseList(resp);
		
		// create an empty list for the arrival records
		List<ArrivalRecord> arrivalsList =new ArrayList<>();

		// iterate through the list of objects casting each one to a map and then retrieve the arrival
		// record details from each map. Create a new arrival record for each set of details and add
		// it to the arrivals list.
		
		//map for sorting by platform
		Map <String,List<ArrivalRecord>> platformMap = new HashMap<>();
		
		for(Object o : list) {
			
			if(o instanceof Map) {
				
				//Map<String,Object> map = (Map<String,Object>) o;				
				Map map =( Map) o;
				
				// get the arrival record
				String destination 		= (String) 	map.get("destinationName");
				String platformName     = (String) 	map.get("platformName");
				Integer timeToStation 	= (Integer)	map.get("timeToStation");
				
				// code for organising by platform
				if (platformMap.containsKey(platformName)){
					ArrayList<ArrivalRecord> platformList = (ArrayList) platformMap.get(platformName);
					platformList.add(new ArrivalRecord(destination, platformName, timeToStation));
					platformMap.put(platformName, platformList);
				}else{
					ArrayList<ArrivalRecord> platformList = new ArrayList<>();
					platformList.add(new ArrivalRecord(destination, platformName, timeToStation));
					platformMap.put(platformName, platformList);
				}
				
				//code for adding records which are not organised by platform
				//ArrivalRecord arrivalRecord =new ArrivalRecord(destination, platformName, timeToStation);
				//arrivalsList.add(arrivalRecord);
			}

		}
		
		//code for sorting records organised by platform
		for (Map.Entry <String,List<ArrivalRecord>> entry : platformMap.entrySet()) {
			
			List <ArrivalRecord> tempList = entry.getValue();
			Collections.sort(tempList);
			arrivalsList.addAll(tempList);
		}
		
	  //code for sorting records by time until arrival only (not organised by platform) 
	  //Collections.sort(arrivalsList);
      
	  // create the arrival schedule, we can pass an empty string for the station name as it is,for our purposes, 
	  // currently hard-coded in the home page. This could change if arrivals service is made more generic
	  ArrivalSchedule arrivalSchedule = new ArrivalSchedule("",arrivalsList);
	  
	  return arrivalSchedule;
	  
	}
	

}
