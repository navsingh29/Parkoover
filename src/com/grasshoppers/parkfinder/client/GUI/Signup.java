package com.grasshoppers.parkfinder.client.GUI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.TextBoxBase;

public class Signup extends Composite {
	private TextBox textBoxUsername;
	private TextBox textBoxPassword;
	private TextBox textBoxEmail;
	private TextBox textBoxAddress;
	private TextBox txtbxCountry;
	private TextBox txtbxCity;
	private TextBox txtbxPostalCode;
	

	public Signup() {
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		verticalPanel.setCellHorizontalAlignment(flexTable, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(flexTable, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("Parkoover");
		flexTable.setWidget(0, 0, lblNewLabel);
		flexTable.getCellFormatter().setStyleName(0, 0, "gwt-Label-Login");
		
		textBoxUsername = new TextBox();
		textBoxUsername.setStyleName("gwt-Label-Login");
		textBoxUsername.setAlignment(TextAlignment.CENTER);
		textBoxUsername.setText("username");
		flexTable.setWidget(1, 0, textBoxUsername);
		flexTable.getCellFormatter().setStyleName(1, 0, "gwt-Label-Login");
		textBoxUsername.setWidth("75%");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBoxPassword = new TextBox();
		textBoxPassword.setStyleName("gwt-Label-Login");
		textBoxPassword.setAlignment(TextAlignment.CENTER);
		textBoxPassword.setText("password");
		flexTable.setWidget(2, 0, textBoxPassword);
		textBoxPassword.setWidth("75%");
		flexTable.getCellFormatter().setWidth(2, 0, "");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBoxEmail = new TextBox();
		textBoxEmail.setAlignment(TextAlignment.CENTER);
		textBoxEmail.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		textBoxEmail.setStyleName("gwt-Label-Login");
		textBoxEmail.setText("email");
		flexTable.setWidget(3, 0, textBoxEmail);
		textBoxEmail.setWidth("75%");
		
		textBoxAddress = new TextBox();
		textBoxAddress.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		textBoxAddress.setText("address");
		textBoxAddress.setStyleName("gwt-Label-Login");
		textBoxAddress.setAlignment(TextAlignment.CENTER);
		flexTable.setWidget(4, 0, textBoxAddress);
		textBoxAddress.setWidth("75%");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GUIHub.buttonDoSignUp(textBoxUsername.getText(), textBoxPassword.getText(), 
						textBoxEmail.getText(), textBoxAddress.getText(), txtbxCountry.getText(), 
						txtbxCity.getText(), txtbxPostalCode.getText());
					}
			});
		
		txtbxCountry = new TextBox();
		txtbxCountry.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtbxCountry.setText("country");
		txtbxCountry.setStyleName("gwt-Label-Login");
		txtbxCountry.setAlignment(TextAlignment.CENTER);
		flexTable.setWidget(5, 0, txtbxCountry);
		txtbxCountry.setWidth("75%");
		
		txtbxCity = new TextBox();
		txtbxCity.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtbxCity.setText("city");
		txtbxCity.setStyleName("gwt-Label-Login");
		txtbxCity.setAlignment(TextAlignment.CENTER);
		flexTable.setWidget(6, 0, txtbxCity);
		txtbxCity.setWidth("75%");
		
		txtbxPostalCode = new TextBox();
		txtbxPostalCode.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtbxPostalCode.setText("postal code");
		txtbxPostalCode.setStyleName("gwt-Label-Login");
		txtbxPostalCode.setAlignment(TextAlignment.CENTER);
		flexTable.setWidget(7, 0, txtbxPostalCode);
		txtbxPostalCode.setWidth("75%");
		btnNewButton.setStyleName("gwt-Label-Login");
		btnNewButton.setText("sign up");
		flexTable.setWidget(8, 0, btnNewButton);
		btnNewButton.setWidth("25%");
		flexTable.getCellFormatter().setVerticalAlignment(8, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button button = new Button("New button");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GUIHub.goToLogIn();
			}
		});
		button.setText("already have an account? login");
		button.setStyleName("gwt-Label-Login");
		flexTable.setWidget(9, 0, button);
		button.setWidth("25%");
		flexTable.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

}