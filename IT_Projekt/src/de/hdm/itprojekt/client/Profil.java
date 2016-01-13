package de.hdm.itprojekt.client;

import de.hdm.itprojekt.client.IT_Projekt;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

public class Profil extends VerticalPanel {
	Label Profilueberschrift = new Label("Profil");
	private Button test = new Button("Test");
	VerticalPanel contentPanel = new VerticalPanel();

	public Profil() {
		contentPanel.add(Profilueberschrift);
		contentPanel.addStyleName("contentPanel");
		contentPanel.add(test);
		RootPanel.get("Content").add(contentPanel);

	}

}