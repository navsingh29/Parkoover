package com.grasshoppers.parkfinder.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grasshoppers.parkfinder.client.modeldata.User;

@SuppressWarnings("serial")
public class ParseFacebookLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		PrintWriter out = resp.getWriter();
		String accessToken = req.getParameter("access_token");
        String expirationDate = req.getParameter("expires_in");
        System.out.println(accessToken + " ==Expires:== " + expirationDate);
        
        Map map = req.getParameterMap();
        System.out.println(req.getLocalAddr());
        System.out.println(req.getContextPath()); 
        System.out.println(req.getPathInfo());
        System.out.println(req.getRequestURL());
        Object userObj = req.getSession(true).getAttribute("user");
        if (userObj != null && userObj instanceof User) {
            User user = (User) userObj;
            if(accessToken!=null){
            	user.setFacebookLogin(true);
            	user.setAccessToken(accessToken);
            	req.getSession().setAttribute("user", user);
            }
        }
        out.println("<meta http-equiv=\"refresh\" content=\"0;URL='http://127.0.0.1:8888/ParkFinder.html?gwt.codesvr=127.0.0.1:9997/'\" />");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	
	
}
