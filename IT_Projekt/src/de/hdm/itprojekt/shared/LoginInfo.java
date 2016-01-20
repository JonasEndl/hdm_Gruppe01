package de.hdm.itprojekt.shared;


import java.io.Serializable; 
 
 
public class LoginInfo implements Serializable { 

 
	private static final long serialVersionUID = 1L; 
	private boolean loggedIn = false; 
	private String loginUrl; 
	private String logoutUrl; 
 	private String mailAdresse; 
	private String nickname;

 
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
 
 
 	public String getMailAdresse() { 
		return mailAdresse; 
 	} 

 
	public void setMailAdresse(String mailAdresse) { 
 		this.mailAdresse = mailAdresse; 
	}
 


	public void setNickname(String nickname) {
		this.nickname = nickname;
		
	} 
	
	public String getNickname(){
		return nickname;
	}
 } 
