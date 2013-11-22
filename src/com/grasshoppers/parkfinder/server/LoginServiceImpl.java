package com.grasshoppers.parkfinder.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.grasshoppers.parkfinder.client.LoginService;
import com.grasshoppers.parkfinder.client.modeldata.User;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	@Override
	public User getUserFromSession() {
			
		 	User user = null;
	        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
	        HttpSession session = httpServletRequest.getSession();
	       
	        String accessToken = httpServletRequest.getParameter("access_token");
	        String expirationDate = httpServletRequest.getParameter("expires_in");
	        System.out.println(accessToken + " ==Expires:== " + expirationDate);
	        
	        Object userObj = session.getAttribute("user");
	        if (userObj != null && userObj instanceof User) {
	            user = (User) userObj;
	            if(accessToken!=null){
	            	user.setFacebookLogin(true);
	            	user.setAccessToken(accessToken);
	            	session.setAttribute("user", user);
	            }
	            
	        }
	        return user;
	}
	
	public Boolean logout() {
        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("user");
        return true;
    }

}
