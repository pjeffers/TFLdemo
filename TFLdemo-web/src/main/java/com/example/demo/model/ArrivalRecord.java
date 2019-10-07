package com.example.demo.model;



public class ArrivalRecord implements Comparable<ArrivalRecord>{
		 private String destinationName;
		 private int timeToStationInSeconds;
		 private int timeToStationInMinutes;
		 private String platformName;
		 
		 
		public int getTimeToStationInMinutes() {
			return timeToStationInMinutes;
		}
		public ArrivalRecord(String destinationName, String platformName, int timeToStationInSeconds) {
			super();
			this.destinationName = destinationName;
			this.platformName = platformName;
			this.timeToStationInSeconds = timeToStationInSeconds;
			this.timeToStationInMinutes = timeToStationInSeconds/60;
		}
		public String getPlatformName() {
			return platformName;
		}
		public String getDestinationName() {
			return destinationName;
		}

		public int getTimeToStationInSeconds() {
			return timeToStationInSeconds;
		}

		@Override
		public int compareTo(ArrivalRecord o) {
			
			return this.timeToStationInSeconds - o.timeToStationInSeconds;
		}
 }

