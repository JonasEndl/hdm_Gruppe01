package de.hdm.itprojekt.client;

import de.hdm.itprojekt.client.IT_Projekt;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Profil extends HorizontalPanel {

	private Button test = new Button("Test");
	HorizontalPanel contentPanel = new HorizontalPanel();

	public Profil() {

		contentPanel.addStyleName("contentPanel");
		contentPanel.add(test);
		RootPanel.get("Content").add(contentPanel);

	}

}
