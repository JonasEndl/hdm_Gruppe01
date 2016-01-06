package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class IT_Projekt implements EntryPoint {

	private HorizontalPanel mainPanel = new HorizontalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private VerticalPanel navigator = new VerticalPanel();
	private Button profil = new Button("Profil");
	private Button nachrichten = new Button("Nachrichten");
	private Button abonnements = new Button("Abonnements");
	private Button nutzer = new Button("Nutzer");
	private Button hashtag = new Button("Hashtag");
	private Button logout = new Button("Logout");

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {

		navigator.add(profil);
		navigator.add(nachrichten);
		navigator.add(abonnements);
		navigator.add(nutzer);
		navigator.add(hashtag);
		navigator.add(logout);
		navigator.addStyleName("navigator");


		// add style names to Buttons
		profil.addStyleName("NavButton");
		nachrichten.addStyleName("NavButton");
		abonnements.addStyleName("NavButton");
		nutzer.addStyleName("NavButton");
		hashtag.addStyleName("NavButton");
		logout.addStyleName("NavButton");
		
		// Content Panel
		contentPanel.addStyleName("contentPanel");
		
		// Assemble Main panel.
		mainPanel.add(navigator);
		mainPanel.add(contentPanel);
		
		mainPanel.addStyleName("mainPanel");
		
		 profil.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        RootPanel.get("Content").clear();
		        RootPanel.get("Content").add(new Profil());
		      }
		    });
		
		 nachrichten.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        RootPanel.get("Content").clear();
		        RootPanel.get("Content").add(new Nachrichten());
		      }
		    });

		// Associate the Main panel with the HTML host page.
			RootPanel.get("navigator").add(mainPanel);

	}
}
