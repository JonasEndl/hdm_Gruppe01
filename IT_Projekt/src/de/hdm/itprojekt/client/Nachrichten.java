package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Nachrichten extends VerticalPanel {

	private Button test2 = new Button("Test2");

	public Nachrichten(){
		
		RootPanel.get("Content").add(test2);
		
	}

}
