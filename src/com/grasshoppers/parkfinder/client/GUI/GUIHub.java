/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.grasshoppers.parkfinder.client.GUI;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.grasshoppers.parkfinder.client.ServiceController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GUIHub implements EntryPoint {
	
	private static RootPanel rootPanel;
	private static HorizontalPanel horizontalPanel;
	private ServiceController clientImpl;
	
	public void onModuleLoad() {
		rootPanel = RootPanel.get();
		
		horizontalPanel = new HorizontalPanel();
		horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootPanel.add(horizontalPanel, 0, 0);
		horizontalPanel.setSize("450px", "300px");
		
		Login log = new Login();
		horizontalPanel.add(log);
		
		clientImpl = new ServiceController(GWT.getModuleBaseURL() + "parksearch");
		
	}
	
	
//=============================================================================================================
//	GOTOs
//=============================================================================================================
	
	public static void goToSignUp() {
		horizontalPanel.clear();
		Signup signup = new Signup();
		horizontalPanel.add(signup);
	}


	public static void goToLogIn() {
		horizontalPanel.clear();
		Login login = new Login();
		horizontalPanel.add(login);
	}
	

	public static void goToPrefList() {
		horizontalPanel.clear();
		PreferenceList pList = new PreferenceList();
		horizontalPanel.add(pList);
	}
	
//=============================================================================================================
//	OnClick Triggers
//=============================================================================================================
	
	public static void buttonDoSignIn(String username, String password, Boolean rememberMe ) {
		if (username.length() == 0 || password.length() == 0) {
			Window.alert("Username or password is empty."); 
		} else {
			
			Window.alert("Query check on: "+username+", "+password+", with Remember Me: "+rememberMe);
			System.out.println("Query check on: "+username+", "+password+", with Remember Me: "+rememberMe);
			horizontalPanel.clear();
			Search search = new Search();
			horizontalPanel.add(search);
		}
	}
	
	public static void buttonDoSignUp(String username, String password, String email, String addr, 
			String country, String city, String postal) {
		if (username.length() == 0 || password.length() == 0) {
			Window.alert("Username or password must be filled."); 
		} else {
			Window.alert("Query check on: "+username+", "+password+", from: "+city+", "+country);
			System.out.println("Query check on: "+username+", "+password+", from: "+city+", "+country);
			goToLogIn();
		}
	}


	public static void doSearch(List<String> list) {
		horizontalPanel.clear();
		Results results = new Results(list);
		horizontalPanel.add(results);
	}





}
