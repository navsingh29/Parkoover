package com.grasshoppers.parkfinder.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List; 

import com.grasshoppers.parkfinder.client.Facility;
import com.grasshoppers.parkfinder.client.Park;

public class ParkSorter {

	
	/**
	 * Sorts a list of parks Alphabetically based on Name
	 */
	//Not sure if this is the proper way to use Comparator, someone check please.
	public List<Park> sortAlphabetically(List<Park> parklist){
		List<Park> newparklist = parklist;
		Collections.sort(newparklist, new Comparator<Park>(){
			@Override
			public int compare(Park park1, Park park2) {
				// TODO Auto-generated method stub
				return park1.getName().compareToIgnoreCase(park2.getName());
			}
			
		});
		
		
		return newparklist;
		
	}
	
/**
 *  Filters a list of Parks based on the given Facility's type
 * @param parklist List of parks to be filtered
 * @param fac Facility type
 * @return Filtered list containing only Parks that have the selected Facility type
 */
	public List<Park> filterByFacilityType(List<Park> parklist, String fac){
		
		List<Park> parklist2 = new ArrayList<Park>();
		for (int i = 0; i < parklist.size(); i++){
			Park park = parklist.get(i);
			List<Facility> faclist = park.getFacilityList();
			if (!park.getFacilityList().isEmpty()){
				for (int j = 0; j < faclist.size(); j++){
					if(faclist.get(j).getType().equalsIgnoreCase(fac)){
						parklist2.add(park);
						break;
					}
				}
			}
		}
		return parklist2;
		
	}
	
	/**
	 *  Filters a list of Parks based on the given Facility's feature
	 * @param parklist List of parks to be filtered
	 * @param fac Facility Feature
	 * @return Filtered list containing only Parks that have the selected Facility feature
	 */
		public List<Park> filterByFacilityFeature(List<Park> parklist, String fac){
			
			List<Park> parklist2 = new ArrayList<Park>();
			for (int i = 0; i < parklist.size(); i++){
				Park park = parklist.get(i);
				List<Facility> faclist = park.getFacilityList();
				if (!park.getFacilityList().isEmpty()){
					for (int j = 0; j < faclist.size(); j++){
						if(faclist.get(j).getFeature().equalsIgnoreCase(fac)){
							parklist2.add(park);
							break;
						}
					}
				}
			}
			return parklist2;
			
		}
}
