package de.hdm.itprojekt.client;

import de.hdm.itprojekt.client.IT_Projekt;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

public class Profil extends VerticalPanel {
	Label Profilueberschrift = new Label("Profil");
	Label EMail = new Label("E-Mail: ");
	Label VorName = new Label("Vorname: ");
	Label NachName = new Label("Nachname: ");
	private Button speichernEMail = new Button("speichern");
	private Button speichernVorName = new Button("speichern");
	private Button speichernNachName = new Button("speichern");
	VerticalPanel contentPanel = new VerticalPanel();
	HorizontalPanel EMailPanel = new HorizontalPanel();
	HorizontalPanel VorNamePanel = new HorizontalPanel();
	HorizontalPanel NachNamePanel = new HorizontalPanel();
	TextBox EMailTextBox = new TextBox();
	TextBox VorNameTextBox = new TextBox();
	TextBox NachNameTextBox = new TextBox();
	
	public Profil() {

		// CSS verknüpfung des contentPanels
		contentPanel.addStyleName("contentPanel");
		
		//Überschrift des Menüpunktes
		contentPanel.add(Profilueberschrift);
		
		
		// Email bearbeiten
		EMailPanel.add(EMail);
		EMailPanel.add(EMailTextBox);
		EMailTextBox.setText("Beispiel@hdm-stuttgart.de");
		contentPanel.add(EMailPanel);
		contentPanel.add(speichernEMail);
		
		// Vorname bearbeiten
		VorNamePanel.add(VorName);
		VorNamePanel.add(VorNameTextBox);
		VorNameTextBox.setText("Max");
		contentPanel.add(VorNamePanel);
		contentPanel.add(speichernVorName);
		
		
		//Nachname bearbeiten
		NachNamePanel.add(NachName);
		NachNamePanel.add(NachNameTextBox);
		NachNameTextBox.setText("Mustermann");
		contentPanel.add(NachNamePanel);
		contentPanel.add(speichernNachName);
		
		
		// ContentPanel dem Content HTML Element hinzufügen
		RootPanel.get("Content").add(contentPanel);

	}

}