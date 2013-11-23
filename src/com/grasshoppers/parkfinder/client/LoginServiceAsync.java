package com.grasshoppers.parkfinder.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.grasshoppers.parkfinder.client.modeldata.User;

public interface LoginServiceAsync  {

	void getUserFromSession(String token, AsyncCallback<User> callback);
	void logout(AsyncCallback<Boolean> callback);
	
}
