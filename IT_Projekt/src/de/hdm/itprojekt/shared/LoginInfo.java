package de.hdm.itprojekt.shared;


import java.io.Serializable;

import de.hdm.itprojekt.shared.bo.Nutzer; 
 
 
public class LoginInfo implements Serializable { 

 
	private static final long serialVersionUID = -5207880593956618550L; 
	private boolean loggedIn = false; 
	private String loginUrl; 
	private String logoutUrl; 
 	private String emailAddresse; 
	private String googleId; 
	private Nutzer nutzer;

 
	public boolean isLoggedIn() { 
		return loggedIn; 
	} 

 
	public void setLoggedIn(boolean loggedIn) { 
		this.loggedIn = loggedIn; 
	} 

 	public String getLoginUrl() { 
		return loginUrl; 
	} 

 
	public void setLoginUrl(String loginUrl) { 
		this.loginUrl = loginUrl; 
	} 
 
 
 	public String getLogoutUrl() { 
 		return logoutUrl; 
	} 
 
 
 	public void setLogoutUrl(String logoutUrl) { 
 		this.logoutUrl = logoutUrl; 
 	} 
 
 
 	public String getEmailAddresse() { 
		return emailAddresse; 
 	} 

 
	public void setEmailAddresse(String emailAddresse) { 
 		this.emailAddresse = emailAddresse; 
	} 

 
 	public Nutzer getNutzer() { 
 		return nutzer;
 	} 
 
 
 	public void setNutzer(Nutzer nutzer) { 
 		this.nutzer = nutzer; 
 	} 
 
 
 	public String getGoogleId() { 
 		return googleId; 
 	} 
 
 
 	public void setGoogleId(String string) { 
 		this.googleId = string; 
 	} 
 } 
