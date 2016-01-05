package de.hdm.itprojekt.client;


import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;



public class Profil extends VerticalPanel {

	private Button test = new Button("Test");

	public Profil(){
		
		RootPanel.get("Content").add(test);
		
	}

}


