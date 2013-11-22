package com.grasshoppers.parkfinder.client.widget.map;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.maps.gwt.client.DirectionsRenderer;
import com.google.maps.gwt.client.DirectionsRendererOptions;
import com.google.maps.gwt.client.DirectionsRequest;
import com.google.maps.gwt.client.DirectionsResult;
import com.google.maps.gwt.client.DirectionsService;
import com.google.maps.gwt.client.DirectionsStatus;
import com.google.maps.gwt.client.Geocoder;
import com.google.maps.gwt.client.GeocoderRequest;
import com.google.maps.gwt.client.GeocoderResult;
import com.google.maps.gwt.client.GeocoderStatus;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MarkerOptions;
import com.google.maps.gwt.client.StreetViewPanorama;
import com.google.maps.gwt.client.StreetViewPanoramaOptions;
import com.google.maps.gwt.client.TravelMode;
public class GoogleMapsWidget extends Composite{
	
	private SimplePanel sp;
	GoogleMap theMap;
	LatLng lastPark;
	DirectionsService dService;
	DirectionsRequest dRequest;
	DirectionsRenderer renderer;
	LatLng geocodeResult;
    StreetViewPanoramaOptions svpo;

	
	public GoogleMapsWidget(){
		 dService = DirectionsService.create();
		 dRequest = DirectionsRequest.create();
		VerticalPanel vp = new VerticalPanel();
		
		lastPark = LatLng.create(49.2500,-123.100);
		sp = new SimplePanel();
		sp.setWidth("300px");
		sp.setHeight("300px");
		
		renderer = DirectionsRenderer.create();
		svpo = StreetViewPanoramaOptions.create();
		
		MapOptions option = MapOptions.create();
		option.setCenter(LatLng.create(49.2500, -123.1000));
		option.setZoom(14);
		option.setMapTypeId(MapTypeId.ROADMAP);
        option.setDraggable(true);
        option.setMapTypeControl(true);
        option.setScaleControl(true);
        option.setScrollwheel(true);
         theMap = GoogleMap.create(sp.getElement(), option) ;
		
		
		vp.add(sp);
		
		initWidget(vp);
	}
	
	public GoogleMapsWidget(Double centerX, Double centerY){
		VerticalPanel vp = new VerticalPanel();

		lastPark = LatLng.create(centerX, centerY);
		sp = new SimplePanel();
		sp.setWidth("300px");
		sp.setHeight("300px");
		
		
		MapOptions option = MapOptions.create();
		option.setCenter(LatLng.create(centerX, centerY));
		option.setZoom(15);
		option.setMapTypeId(MapTypeId.ROADMAP);
        option.setDraggable(true);
        option.setMapTypeControl(true);
        option.setScaleControl(true);
        option.setScrollwheel(true);
         theMap = GoogleMap.create(sp.getElement(), option) ;
		
		
		vp.add(sp);
		
		initWidget(vp);
	}
/**	
	public void plotParks(Set<Park> parks){
	    Set<LatLng> setOfLatLng = new HashSet<LatLng>();
		for(Park p: parks){
			setOfLatLng.add(LatLng.create(p.getMap_x_loc(),p.getMap_y_loc()));
		}
		
	}
**/
	
	public void plotPark(Double x, Double y){
		lastPark=(LatLng.create(x,y));
        theMap.setCenter(LatLng.create(x,y));
        MarkerOptions mo = MarkerOptions.create();
        mo.setPosition(LatLng.create(x, y));
        Marker marker = Marker.create(mo);
        marker.setMap(theMap);
       
		
	}
	
	public void plotRoute(Double x1, Double y1, Double x2, Double y2, String travelMethodWorBorD){
		TravelMode travelMethod = TravelMode.DRIVING;
		
		lastPark = LatLng.create(x1, x2);
		
		if(travelMethodWorBorD == "W"){
		     travelMethod = TravelMode.WALKING;
				}
		if(travelMethodWorBorD == "B"){
			 travelMethod = TravelMode.BICYCLING;
		}
		if(travelMethodWorBorD == "D"){
			 travelMethod = TravelMode.DRIVING;
		}
		
		dRequest.setDestination(LatLng.create(x2, y2));
		dRequest.setOrigin(LatLng.create(x1, y1));
		dRequest.setTravelMode(travelMethod);
		
		dService.route(dRequest, new DirectionsService.Callback() {
			
			@Override
			public void handle(DirectionsResult a, DirectionsStatus b) {
				//DirectionsRendererOptions directionsRendererOption = DirectionsRendererOptions.create();
				//directionsRendererOption.setDirections(a);
				renderer.setDirections(a);
			    //renderer=  DirectionsRenderer.create(directionsRendererOption);
				renderer.setMap(theMap);
			}
		});
		
		
		
		
	}
	public void plotRouteWithLatL(LatLng orign, LatLng des, String travelMethodWorBorD){
		TravelMode travelMethod = TravelMode.DRIVING;
		lastPark = orign;
		
		if(travelMethodWorBorD == "W"){
		     travelMethod = TravelMode.WALKING;
				}
		if(travelMethodWorBorD == "B"){
			 travelMethod = TravelMode.BICYCLING;
		}
		if(travelMethodWorBorD == "D"){
			 travelMethod = TravelMode.DRIVING;
		}
		
		dRequest.setDestination(des);
		dRequest.setOrigin(orign);
		dRequest.setTravelMode(travelMethod);
		
		dService.route(dRequest, new DirectionsService.Callback() {
			
			@Override
			public void handle(DirectionsResult a, DirectionsStatus b) {
				//DirectionsRendererOptions directionsRendererOption = DirectionsRendererOptions.create();
				//directionsRendererOption.setDirections(a);
				renderer.setDirections(a);
			    //renderer=  DirectionsRenderer.create(directionsRendererOption);
				renderer.setMap(theMap);
			}
		});
		
		
		
		
	}
	
	public void cleanTheMap(){
		MapOptions option = MapOptions.create();
		option.setCenter(lastPark);
		option.setZoom(15);
		option.setMapTypeId(MapTypeId.ROADMAP);
        option.setDraggable(true);
        option.setMapTypeControl(true);
        option.setScaleControl(true);
        option.setScrollwheel(true);
         theMap = GoogleMap.create(sp.getElement(), option) ;
	}
	
	public void plotMultipleParksLocation(List<LatLng> listOfLatLng){
		if(!listOfLatLng.isEmpty()){
		theMap.setCenter(listOfLatLng.get(0));	
		for (LatLng ll: listOfLatLng){
        MarkerOptions mo = MarkerOptions.create();
        mo.setPosition(ll);
        Marker marker = Marker.create(mo);
        marker.setMap(theMap);
		}
		}
	}
	
	public void panTo(Double x,Double y){
        renderer = DirectionsRenderer.create();
        theMap.panTo(LatLng.create(x,y));
	}
	
	
	public void plotRouteFromHomeToPark(String zipcode,String city,Double x,Double y, String transMethod){
	    
		final String address = zipcode + "," + city;
		final LatLng dest = LatLng.create(x, y);
		final String transM = transMethod;
		Geocoder geocoder = Geocoder.create();
		GeocoderRequest geoRequest = GeocoderRequest.create();
		geoRequest.setAddress(address);
		
		geocoder.geocode(geoRequest, new Geocoder.Callback() {
			
			@Override
			public void handle(JsArray<GeocoderResult> a, GeocoderStatus b) {
			if (b == GeocoderStatus.OK){
				GeocoderResult result = a.shift();
				plotRouteWithLatL(result.getGeometry().getLocation(), dest, transM);
				
			}else{
				Window.alert("Fail to find user's address, please check your profile");
			}	}
		});
		{}
	}
	
	public void goToStreetViewAtPoint(Double x, Double y){
		
		  
		 svpo.setPosition(LatLng.create(x, y));
		 svpo.setVisible(true);
		 StreetViewPanorama svpd = StreetViewPanorama.create(getElement(), svpo);
	}
	

}