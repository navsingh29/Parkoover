package com.grasshoppers.parkfinder.shared;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class Facebook {

	
	public static void main(String[] args) {
		
		FacebookClient fbClient = new DefaultFacebookClient("CAACEdEose0cBAPfh3RPMjcT2tdwdmxMhZBj71UwDtz1H6ZATw7j6RxKzt8ZAqMSwVxZCFZAaCHZBwdZBEJgSDhMun7ZC227CEykanS70dCAgsfFoZBQfbf6IwKNs4hHePtYtohZBbNJmVELPOiCPI3AgOLpaJL3HWDFd40321cg4OPL4VB28Jpi9LPDXjnoxVnUOoZD");
		fbClient.fetchObject("", User.class);
		
	}
	
}
