package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Anchor;

import de.hdm.itprojekt.shared.LoginInfo;
import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.LoginServiceAsync;

public class IT_Projekt implements EntryPoint {

	private Button profil = new Button("Profil");
	private Button nachrichten = new Button("Nachrichten");
	private Button abonnements = new Button("Abonnements");
	private Button nutzer = new Button("Nutzer");
	private Button hashtag = new Button("Hashtag");
	private Button logout = new Button("Logout");
	private Button meineNachrichten = new Button("Meine Nachrichten");
	private Button aktualisieren = new Button("Aktualisieren");
	// HorizontalPanel mainPanel = new HorizontalPanel();
	// HorizontalPanel contentPanel = new HorizontalPanel();
	VerticalPanel navigator = new VerticalPanel();

	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {

		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					loadIT_Projekt();
				} else {
					loadLogin();
				}
			}
		});

		loadIT_Projekt();
	}

	private void loadIT_Projekt() {

		navigator.add(profil);
		navigator.add(nachrichten);
		navigator.add(abonnements);
		navigator.add(nutzer);
		navigator.add(hashtag);
		navigator.add(logout);
		navigator.addStyleName("navigator");
		// contentPanel.add(meineNachrichten);
		// contentPanel.add(aktualisieren);

		// add style names to Buttons
		
		nachrichten.addStyleName("NavButton");
		abonnements.addStyleName("NavButton");
		nutzer.addStyleName("NavButton");
		hashtag.addStyleName("NavButton");
		logout.addStyleName("NavButton");
		aktualisieren.addStyleName("ContentButton");
		meineNachrichten.addStyleName("ContentButton");

		// Content Panel
		// contentPanel.addStyleName("contentPanel");

		// Assemble Main panel.
		// mainPanel.add(navigator);
		// mainPanel.add(contentPanel);

		// mainPanel.addStyleName("mainPanel");
	
		profil.addStyleName("NavButton");
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
		RootPanel.get("navigator").add(navigator);

	}

	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("it_projekt").add(loginPanel);
	}
}