package com.grasshoppers.parkfinder.client.widget.weather;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.grasshoppers.parkfinder.client.GUIController;

public class WeatherViewer extends Composite {

	private int curr;
	private VerticalPanel verticalPanel;
	private List<Weather> weathers;
	
	public WeatherViewer() {
		curr = 0;
		weathers = new WeatherParser().get();
		verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		constructViewer();
	}

	private void constructViewer() {
		verticalPanel.setWidth("400px");
		
		Label lblNewLabel = new Label("Weekly Vancouver Weather");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblNewLabel.setStyleName("gwt-DisclosurePanel");
		verticalPanel.add(lblNewLabel);
		lblNewLabel.setWidth("405px");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanel_1);
		horizontalPanel_1.setWidth("398px");
		
		TextBox txtbxFirst = new TextBox();
		horizontalPanel_1.add(txtbxFirst);
		txtbxFirst.setText(weathers.get(curr).getDay());
		txtbxFirst.setStyleName("gwt-RichTextToolbar");
		txtbxFirst.setWidth("195px");
		
		TextBox txtbxSecond = new TextBox();
		horizontalPanel_1.add(txtbxSecond);
		txtbxSecond.setText(weathers.get(curr).getGen());
		txtbxSecond.setStyleName("gwt-RichTextToolbar");
		txtbxSecond.setWidth("195px");
		
		HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
		horizontalPanel_2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanel_2);
		horizontalPanel_2.setWidth("398px");
		
		TextBox txtbxFirst_1 = new TextBox();
		txtbxFirst_1.setText("Pressure: "+weathers.get(curr).getPressure());
		txtbxFirst_1.setStyleName("gwt-RichTextToolbar");
		horizontalPanel_2.add(txtbxFirst_1);
		txtbxFirst_1.setWidth("195px");
		
		TextBox txtbxSecond_1 = new TextBox();
		txtbxSecond_1.setText("Humidity: "+weathers.get(curr).getHumidity());
		txtbxSecond_1.setStyleName("gwt-RichTextToolbar");
		horizontalPanel_2.add(txtbxSecond_1);
		txtbxSecond_1.setWidth("195px");
		
		TextBox txtbxThird = new TextBox();
		txtbxThird.setText("Wind: "+weathers.get(curr).getWind());
		txtbxThird.setStyleName("gwt-RichTextToolbar");
		verticalPanel.add(txtbxThird);
		txtbxThird.setWidth("398px");
		
		TextBox txtbxFourth = new TextBox();
		txtbxFourth.setText("Day Temp: "+weathers.get(curr).getTempDay());
		txtbxFourth.setStyleName("gwt-RichTextToolbar");
		verticalPanel.add(txtbxFourth);
		txtbxFourth.setWidth("398px");
		
		TextBox textBox = new TextBox();
		textBox.setText("Other Temp: "+weathers.get(curr).getTempOther());
		textBox.setStyleName("gwt-RichTextToolbar");
		verticalPanel.add(textBox);
		textBox.setWidth("398px");
		
		TextBox textBox_1 = new TextBox();
		textBox_1.setText("Precipication: "+weathers.get(curr).getPrec());
		textBox_1.setStyleName("gwt-RichTextToolbar");
		verticalPanel.add(textBox_1);
		textBox_1.setWidth("398px");
		
		TextBox textBox_2 = new TextBox();
		textBox_2.setText("Cloud: "+weathers.get(curr).getCloud());
		textBox_2.setStyleName("gwt-RichTextToolbar");
		verticalPanel.add(textBox_2);
		textBox_2.setWidth("398px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setWidth("398px");
		
		Button prevDayButton = new Button("prev day");
		
		prevDayButton.setStyleName("gwt-ToggleButton-up");
		if (curr > 0)
			prevDayButton.addClickHandler(new prevDayClickHandler());

		horizontalPanel.add(prevDayButton);
		prevDayButton.setWidth("100px");
		

		
		Button btnReferesh = new Button("refresh");
		btnReferesh.addClickHandler(new refreshClickHandler());
		
		btnReferesh.setStyleName("gwt-ToggleButton-up");
		horizontalPanel.add(btnReferesh);
		btnReferesh.setWidth("100px");
		
		Button nextDayButton = new Button("next day");
		nextDayButton.setStyleName("gwt-ToggleButton-up");
		if (curr < weathers.size() - 1)
			nextDayButton.addClickHandler(new nextDayClickHandler());

		horizontalPanel.add(nextDayButton);
		nextDayButton.setWidth("100px");
	}
	
	private class prevDayClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			if (curr > 0) {
				curr--;
				System.out.println(curr);
				verticalPanel.clear();
				constructViewer();
			}
		}		
	}
	
	private class nextDayClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			if (curr < weathers.size() - 1) {
				curr++;
				System.out.println(curr);
				verticalPanel.clear();
				constructViewer();
			}
		}
	}
	
	private class refreshClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			WeatherParser tempWP = new WeatherParser();
			weathers = tempWP.get();
			curr = 0;
			verticalPanel.clear();
			constructViewer();
		}
	}
}
