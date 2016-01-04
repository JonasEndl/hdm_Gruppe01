package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback; 


public interface LoginServiceAsync { 

 	void login(String requestUri, AsyncCallback<LoginInfo> async);
  
 
 } 
