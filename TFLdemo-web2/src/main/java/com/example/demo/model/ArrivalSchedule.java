package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ArrivalSchedule {
    private String FullStationName;
    List<ArrivalRecord> arrivalRecords;
    
	public ArrivalSchedule(String fullStationName, List<ArrivalRecord> arrivalRecords) {
		super();
		FullStationName = fullStationName;
		this.arrivalRecords = arrivalRecords;
	}

	public String getFullStationName() {
		return FullStationName;
	}

	public void setFullStationName(String fullStationName) {
		FullStationName = fullStationName;
	}

	public List<ArrivalRecord> getArrivalRecords() {
		return arrivalRecords;
	}

	public void setArrivalRecords(List<ArrivalRecord> arrivalRecords) {
		this.arrivalRecords = arrivalRecords;
	}


    
}
