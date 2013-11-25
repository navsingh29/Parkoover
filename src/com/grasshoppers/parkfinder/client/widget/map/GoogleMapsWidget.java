package com.grasshoppers.parkfinder.client.widget.map;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.google.gwt.core.client.JsArray;
import com.google.gwt.dev.generator.ast.Node;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
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
import com.google.maps.gwt.client.MapTypeRegistry;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MarkerOptions;
import com.google.maps.gwt.client.StreetViewAddressControlOptions;
import com.google.maps.gwt.client.StreetViewPanorama;
import com.google.maps.gwt.client.StreetViewPanoramaData;
import com.google.maps.gwt.client.StreetViewPanoramaOptions;
import com.google.maps.gwt.client.TravelMode;

public class GoogleMapsWidget extends Composite{
	//TabPanel tp;
	private SimplePanel sp;
	private SimplePanel sp2;
	private GoogleMap theMap;
	private LatLng currentPark;
	private String zipcode;
	private String city;


	LatLng geocodeResult;
	StreetViewPanoramaOptions svpo;

	
	public GoogleMapsWidget(Double x, Double y, String zipcode, String city){
		VerticalPanel vp = new VerticalPanel();
		vp = new VerticalPanel();
		currentPark = LatLng.create(49.2500,-123.100);
		sp = new SimplePanel();
		sp.setWidth("600px");
		sp.setHeight("400px");
	    createTheWidget();
		vp.add(sp);
		
		
		
		
		initWidget(vp);
	}
    public void createTheWidget(){
    	//renderer = DirectionsRenderer.create();
		svpo = StreetViewPanoramaOptions.create();
    	MapOptions option = MapOptions.create();
		option.setCenter(LatLng.create(49.2500, -123.100));
		option.setZoom(15);
		option.setMapTypeId(MapTypeId.ROADMAP);
        option.setDraggable(true);
        option.setMapTypeControl(true);
        option.setScaleControl(true);
        option.setScrollwheel(true);
        theMap = GoogleMap.create(sp.getElement(), option);
    }
    public void createTheWidgetWithCentre(Double x, Double y){
    	//renderer = DirectionsRenderer.create();
		svpo = StreetViewPanoramaOptions.create();
    	MapOptions option = MapOptions.create();
		option.setCenter(LatLng.create(x, y));
		option.setZoom(15);
		option.setMapTypeId(MapTypeId.ROADMAP);
        option.setDraggable(true);
        option.setMapTypeControl(true);
        option.setScaleControl(true);
        option.setScrollwheel(true);
        theMap = GoogleMap.create(sp.getElement(), option);
    	
    	
    }
 
    
	public void plotPark(Double x, Double y){
		this.panTo(x,y);
        MarkerOptions mo = MarkerOptions.create();
        mo.setPosition(LatLng.create(x, y));
        Marker marker = Marker.create(mo);
        marker.setMap(theMap);
	}
	
	public void plotRoute(Double x1, Double y1, Double x2, Double y2, String travelMethodWorBorD){
		DirectionsService dService;
		DirectionsRequest dRequest;
		final DirectionsRenderer renderer;
		TravelMode travelMethod = TravelMode.DRIVING;
		
		
		dService = DirectionsService.create();
		dRequest = DirectionsRequest.create();
		renderer = DirectionsRenderer.create();
		
		
		
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
		DirectionsService dService;
		DirectionsRequest dRequest;
		final DirectionsRenderer renderer;
		dService = DirectionsService.create();
		dRequest = DirectionsRequest.create();
		renderer = DirectionsRenderer.create();
		
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
		clearEverything();
        theMap.setCenter(LatLng.create(x,y));
        this.currentPark = LatLng.create(x, y);
	}
	
	
	public void plotRouteFromHomeToPark(String zipcode,String city,Double x,Double y, String transMethod){
	    clearEverything();
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
		 clearEverything();
	     svpo.setPosition(LatLng.create(x, y));
		 svpo.setVisible(true);
		 StreetViewPanorama svpd = StreetViewPanorama.create(this.sp.getElement(), svpo);
	}
	
	
	public void exitStreetView(){
		 svpo.setVisible(false);
		 StreetViewPanorama svpd = StreetViewPanorama.create(this.sp.getElement(), svpo);	
		 sp.clear();
		 initWidget(sp);
	}
	
	public void clearEverything(){
		 svpo.setVisible(false);
		 StreetViewPanorama svpd = StreetViewPanorama.create(this.sp.getElement(), svpo);	
		 sp.clear();
		 createTheWidget();
	}
	
	public void goToNearestStreetView(Double x,Double y){
		
		Geocoder geocoder = Geocoder.create();
		GeocoderRequest geoRequest = GeocoderRequest.create();
		
		geoRequest.setLocation(LatLng.create(x,y));
		geocoder.geocode(geoRequest, new Geocoder.Callback() {
			
	
			@Override
			public void handle(JsArray<GeocoderResult> a, GeocoderStatus b) {
				if(b == GeocoderStatus.OK){
					GeocoderResult result = a.shift();
					
					clearEverything();
					goToStreetViewAtPoint(result.getGeometry().getLocation().lat(),result.getGeometry().getLocation().lng());
				}else{
					Window.alert("false location, please check your LatLong");
				}
			}
		});
		
		
		
		
	}
	
	

}