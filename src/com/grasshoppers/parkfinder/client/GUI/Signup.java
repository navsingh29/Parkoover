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
import com.grasshoppers.parkfinder.client.GUIController;
import com.google.gwt.user.client.ui.Image;

public class Signup extends Composite {
	private TextBox textBoxUsername;
	private TextBox textBoxPassword;
	private TextBox textBoxEmail;
	private TextBox textBoxAddress;
	private TextBox txtbxCountry;
	private TextBox txtbxCity;
	private TextBox txtbxPostalCode;
	private TextBox textBoxFirstName;
	private TextBox textBoxLastName;
	private Image image;
	

	public Signup(final GUIController controller) {
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
		
		image = new Image("images/Parkoover10.gif");
		flexTable.setWidget(0, 0, image);
		
		textBoxUsername = new TextBoxWithGhostText();
		textBoxUsername.setStyleName("gwt-HorizontalSplitPanel");
		textBoxUsername.setAlignment(TextAlignment.CENTER);
		((TextBoxWithGhostText) textBoxUsername).setGhostText("Username");
		flexTable.setWidget(1, 0, textBoxUsername);
		flexTable.getCellFormatter().setStyleName(1, 0, "gwt-Label-Login");
		textBoxUsername.setWidth("75%");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBoxPassword = new TextBoxWithGhostText();
		textBoxPassword.setStyleName("gwt-HorizontalSplitPanel");
		textBoxPassword.setAlignment(TextAlignment.CENTER);
		((TextBoxWithGhostText) textBoxPassword).setGhostText("Password");
		flexTable.setWidget(2, 0, textBoxPassword);
		textBoxPassword.setWidth("75%");
		flexTable.getCellFormatter().setWidth(2, 0, "");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBoxFirstName = new TextBoxWithGhostText();
		textBoxFirstName.setAlignment(TextAlignment.CENTER);
		textBoxFirstName.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		textBoxFirstName.setStyleName("gwt-Label-Login");
		((TextBoxWithGhostText) textBoxFirstName).setGhostText("first name");
		flexTable.setWidget(3, 0, textBoxFirstName);
		textBoxFirstName.setWidth("75%");
		
		textBoxLastName = new TextBoxWithGhostText();
		textBoxLastName.setAlignment(TextAlignment.CENTER);
		textBoxLastName.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		textBoxLastName.setStyleName("gwt-Label-Login");
		((TextBoxWithGhostText) textBoxLastName).setGhostText("last name");
		flexTable.setWidget(4, 0, textBoxLastName);
		textBoxLastName.setWidth("75%");
		
		textBoxEmail = new TextBoxWithGhostText();
		textBoxEmail.setAlignment(TextAlignment.CENTER);
		textBoxEmail.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		textBoxEmail.setStyleName("gwt-Label-Fields");
		((TextBoxWithGhostText) textBoxEmail).setGhostText("Email");
	//	flexTable.setWidget(3, 0, textBoxEmail);
		textBoxEmail.setWidth("75%");
		
		textBoxAddress = new TextBoxWithGhostText();
		textBoxAddress.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		textBoxAddress.setStyleName("gwt-Label-Fields");
		textBoxAddress.setAlignment(TextAlignment.CENTER);
		((TextBoxWithGhostText) textBoxAddress).setGhostText("Address");
		flexTable.setWidget(5, 0, textBoxAddress);
		textBoxAddress.setWidth("75%");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				controller.buttonDoSignUp(textBoxUsername.getText(), textBoxPassword.getText(), 
						textBoxFirstName.getText(), textBoxLastName.getText(), textBoxAddress.getText(), txtbxCountry.getText(), 
						txtbxCity.getText(), txtbxPostalCode.getText());
					}
			});
		
		txtbxCountry = new TextBoxWithGhostText();
		txtbxCountry.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtbxCountry.setStyleName("gwt-Label-Fields");
		txtbxCountry.setAlignment(TextAlignment.CENTER);
		((TextBoxWithGhostText) txtbxCountry).setGhostText("Country");
		flexTable.setWidget(6, 0, txtbxCountry);
		txtbxCountry.setWidth("75%");
		
		txtbxCity = new TextBoxWithGhostText();
		txtbxCity.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtbxCity.setStyleName("gwt-Label-Fields");
		txtbxCity.setAlignment(TextAlignment.CENTER);
		((TextBoxWithGhostText) txtbxCity).setGhostText("City");
		flexTable.setWidget(7, 0, txtbxCity);
		txtbxCity.setWidth("75%");
		
		txtbxPostalCode = new TextBoxWithGhostText();
		txtbxPostalCode.setTextAlignment(TextBoxBase.ALIGN_CENTER);
		txtbxPostalCode.setStyleName("gwt-Label-Fields");
		txtbxPostalCode.setAlignment(TextAlignment.CENTER);
		((TextBoxWithGhostText) txtbxPostalCode).setGhostText("Province");
		flexTable.setWidget(8, 0, txtbxPostalCode);
		txtbxPostalCode.setWidth("75%");
		btnNewButton.setStyleName("gwt-RichTextToolbar");
		btnNewButton.setText("sign up");
		flexTable.setWidget(9, 0, btnNewButton);
		btnNewButton.setWidth("25%");
		flexTable.getCellFormatter().setVerticalAlignment(8, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button button = new Button("New button");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.goToLogIn();
			}
		});
		button.setText("have an account?");
		button.setStyleName("gwt-RichTextToolbar");
		flexTable.setWidget(10, 0, button);
		button.setWidth("25%");
		flexTable.getCellFormatter().setHorizontalAlignment(9, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

}