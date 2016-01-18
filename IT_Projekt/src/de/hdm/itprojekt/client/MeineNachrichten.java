package de.hdm.itprojekt.client;

import de.hdm.itprojekt.client.IT_Projekt;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class MeineNachrichten extends HorizontalPanel {

	private Button bearbeiten = new Button("bearbeiten");
	private Button Aktualisieren = new Button("aktualisieren");
	private Button Kommentieren = new Button("kommentieren");
	private Button Loeschen = new Button("loeschen");

	HorizontalPanel contentPanel = new HorizontalPanel();
	VerticalPanel contentPanel2 = new VerticalPanel();
	HorizontalPanel TopButtons = new HorizontalPanel();
	HorizontalPanel ButtonsNachrichten = new HorizontalPanel();
	
	VerticalPanel MeineNachrichtenContent = new VerticalPanel();
	TextBox Nachrichterfassen = new TextBox();
	
	Label Nachricht = new Label("Dies ist ein Beispiel Text. Hier würde dann die Nachricht die man erhält stehen");
	Label Kommentare = new Label("Hier stehen Kommentare");

	public MeineNachrichten() {

// Nachricht erfassen
		contentPanel.addStyleName("contentPanel");
		
		contentPanel2.add(Aktualisieren);
		MeineNachrichtenContent.add(Nachrichterfassen);
		ButtonsNachrichten.add(Kommentieren);
		ButtonsNachrichten.add(bearbeiten);
		ButtonsNachrichten.add(Loeschen);
		MeineNachrichtenContent.add(ButtonsNachrichten);
		MeineNachrichtenContent.add(Kommentare);
		contentPanel2.add(MeineNachrichtenContent);
		contentPanel.add(contentPanel2);


		
		
		RootPanel.get("Content").add(contentPanel);

	}

}