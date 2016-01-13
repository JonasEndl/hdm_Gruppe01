package de.hdm.itprojekt.client;

import de.hdm.itprojekt.client.IT_Projekt;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;

public class Nachrichten extends HorizontalPanel {

	private Button test2 = new Button("Test2");
	HorizontalPanel contentPanel = new HorizontalPanel();

	public Nachrichten() {


		contentPanel.addStyleName("contentPanel");
		contentPanel.add(test2);
		RootPanel.get("Content").add(contentPanel);

	}

}