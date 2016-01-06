package de.hdm.itprojekt.server;

import com.google.appengine.api.users.User; 
import com.google.appengine.api.users.UserService; 
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.shared.LoginInfo;
import de.hdm.itprojekt.shared.bo.Nutzer; 


//LoginServiceImpl Klasse

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{

private String requestUri;
private static final long serialVersionUID = 1L;

public LoginServiceImpl()throws IllegalArgumentException { 
} 

public LoginInfo login (String requestUri) {

UserService userService= UserServiceFactory.getUserService(); 
Nutzer nutzer= userService.getCurrentUser(); 
LoginInfo loginInfo= new LoginInfo();

if (nutzer != null) { 
loginInfo.setLoggedIn(true); 
loginInfo.setEmailAddresse(nutzer.getMailadresse()); 
loginInfo.setVorname(nutzer.getVorname());
loginInfo.setNachname(nutzer.getNachname()); 
loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));

} else { 
loginInfo.setLoggedIn(false); 
loginInfo.setLoginUrl(userService.createLoginURL(requestUri));

}
 return loginInfo;
}
}