package de.hdm.itprojekt.client;

import de.hdm.itprojekt.client.IT_Projekt;
import de.hdm.thies.bankProjekt.client.gui.AccountForm.CreateDepositCallback;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class Nachrichten extends HorizontalPanel {

	private Button MeineNachrichten = new Button("Meine Nachrichten");
	private Button Aktualisieren = new Button("aktualisieren");
	private Button Empfeanger = new Button("Empfeanger");
	private Button Senden = new Button("senden");
	private Button Sender = new Button("Sender");
	private Button WeitereEmpfeanger = new Button("weitere Empfeanger");
	private Button Kommentieren = new Button("kommentieren");
	private Button Abonnieren = new Button("abonnieren");
	HorizontalPanel contentPanel = new HorizontalPanel();
	VerticalPanel contentPanel2 = new VerticalPanel();
	HorizontalPanel TopButtons = new HorizontalPanel();
	VerticalPanel NachrichterfassenContent = new VerticalPanel();
	VerticalPanel ErhalteneNachrichtenContent = new VerticalPanel();
	TextBox Nachrichterfassen = new TextBox();
	HorizontalPanel NachrichtSchreibenButtons = new HorizontalPanel();
	HorizontalPanel SenderEmpfeangerButtonContent = new HorizontalPanel();
	HorizontalPanel KommentierenAbonnierenButtonContent = new HorizontalPanel();
	Label Nachricht = new Label("Dies ist ein Beispiel Text. Hier würde dann die Nachricht die man erhält stehen");

	public Nachrichten() {

// Nachricht erfassen
		contentPanel.addStyleName("contentPanel");
		contentPanel.add(contentPanel2);
		TopButtons.add(MeineNachrichten);
		TopButtons.add(Aktualisieren);
		contentPanel2.add(TopButtons);
		NachrichtSchreibenButtons.add(Empfeanger);
		NachrichtSchreibenButtons.add(Senden);
		NachrichterfassenContent.add(Nachrichterfassen);
		NachrichterfassenContent.add(NachrichtSchreibenButtons);
		contentPanel2.add(NachrichterfassenContent);
		
// Nachricht erhalten
		SenderEmpfeangerButtonContent.add(Sender);
		SenderEmpfeangerButtonContent.add(WeitereEmpfeanger);
		ErhalteneNachrichtenContent.add(SenderEmpfeangerButtonContent);
		ErhalteneNachrichtenContent.add(Nachricht);
		KommentierenAbonnierenButtonContent.add(Kommentieren);
		KommentierenAbonnierenButtonContent.add(Abonnieren);
		ErhalteneNachrichtenContent.add(KommentierenAbonnierenButtonContent);
		
		contentPanel2.add(ErhalteneNachrichtenContent);

		
		MeineNachrichten.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Content").clear();
				RootPanel.get("Content").add(new MeineNachrichten());
			}
		});
		
		RootPanel.get("Content").add(contentPanel);

	}


}