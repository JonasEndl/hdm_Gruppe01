package de.hdm.itprojekt.server;

import com.google.appengine.api.users.User; 
import com.google.appengine.api.users.UserService; 
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.shared.LoginInfo;
import de.hdm.itprojekt.shared.LoginService;

/**
 * LoginServiceImpl Klasse.
 * @author Teuta
 *
 */


public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{


private static final long serialVersionUID = 1L;




public LoginInfo login (String requestUri) {

UserService userService= UserServiceFactory.getUserService(); 
User user= userService.getCurrentUser(); 
LoginInfo loginInfo= new LoginInfo();

if (user != null) { 
loginInfo.setLoggedIn(true); 
loginInfo.setMailAdresse(user.getEmail()); 
loginInfo.setNickname(user.getNickname());
loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));

} else { 
loginInfo.setLoggedIn(false); 
loginInfo.setLoginUrl(userService.createLoginURL(requestUri));

}
 return loginInfo;
}
}