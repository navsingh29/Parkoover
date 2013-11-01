package com.grasshoppers.parkfinder.server;

import java.util.List;

import com.grasshoppers.parkfinder.client.ParkSearchService;
import com.grasshoppers.parkfinder.model.Park;
import com.grasshoppers.parkfinder.model.ParkModel;
import com.grasshoppers.parkfinder.model.User;
import com.grasshoppers.parkfinder.model.UserModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ParkSearchServiceImpl extends RemoteServiceServlet implements
		ParkSearchService {

/*	@Override
	public List<Park> findParkServer(String name, String neighbourhood,
			String facility) throws IllegalArgumentException {
		  List<Park> parks = ParkModel.findParks(name, neighbourhood, facility);
		return parks;
	}
*/
	
	public String getPark() {
		User user = UserModel.getUser("superman", "kryptonite");
		List<Park> parks = ParkModel.findParks(null, "ridge","ball");
		return parks.get(0).getName();
	}

	

}
