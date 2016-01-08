package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


public class LogInUi implements EntryPoint {


  private VerticalPanel mainPanel = new VerticalPanel();
  private Label label1 = new Label("Email-Adresse");
  private TextBox textBox = new TextBox();
  private Label label2 = new Label("Passwort");
  private PasswordTextBox passwortBox = new PasswordTextBox();
  private HorizontalPanel buttonsPanel = new HorizontalPanel();
  private Button login = new Button("Login");
  private Button regestrieren = new Button("Mit Google Account Registrieren");
  
 
  String cwBasicButtonClickMessage() {
	return null;
}

  
  /**
   * Entry point method.
   */
  public void onModuleLoad() {
   

    mainPanel.add(label1);
    mainPanel.add(textBox);
    mainPanel.add(label2);
    mainPanel.add(passwortBox);
    buttonsPanel.add(login);
    buttonsPanel.add(regestrieren);

   
//Add Style Name
    
    mainPanel.addStyleName("mainPanelLogin");

    
   
   mainPanel.add(buttonsPanel);
    
   

    // Associate the Main panel with the HTML host page.
    RootPanel.get("stockList").add(mainPanel);

    
    
    
   
    
   
      }
    
  }
    
    
  
  
      
