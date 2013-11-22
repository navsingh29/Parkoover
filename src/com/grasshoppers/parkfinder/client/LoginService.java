package com.grasshoppers.parkfinder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.grasshoppers.parkfinder.client.modeldata.User;

@RemoteServiceRelativePath("loginservice")
public interface LoginService extends RemoteService {

	/**
     * Utility class for simplifying access to the instance of async service.
     */
    public static class Util
    {
        private static LoginServiceAsync instance;
 
        public static LoginServiceAsync getInstance()
        {
            if (instance == null)
            {
                instance = GWT.create(LoginService.class);
            }
            return instance;
        }
    }
    
    public User getUserFromSession();
    public Boolean logout();
	
}
