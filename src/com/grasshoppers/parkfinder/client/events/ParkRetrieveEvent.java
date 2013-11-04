package com.grasshoppers.parkfinder.client.events;

import com.grasshoppers.parkfinder.client.modeldata.Park;

public class ParkRetrieveEvent extends DataEvent{

	private Park park;

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}
	
	
	
}
