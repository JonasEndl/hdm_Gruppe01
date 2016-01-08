package de.hdm.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


public class Registration implements EntryPoint {


  private Label labelreg = new Label("Registrieren");
  private VerticalPanel mainPanel = new VerticalPanel();
  private Label labelVorname = new Label("Vorname");
  private TextBox textBoxVorname = new TextBox();
  private Label labelNachname = new Label("Nachname");
  private TextBox textBoxNachname = new TextBox();
  private Label labelEmail = new Label("E-Mail Adresse");
  private TextBox textBoxEmail = new TextBox();
  private Label labelPasswort = new Label("Passwort");
  private PasswordTextBox passwortBox = new PasswordTextBox();
  private Button registrieren = new Button("Registrieren");
 
  String cwBasicButtonClickMessage() {
	return null;
}
  
  /**
   * Entry point method.
   */
  public void onModuleLoad() {
   

    mainPanel.add(labelreg);
    mainPanel.add(labelVorname);
    mainPanel.add(textBoxVorname);
    mainPanel.add(labelNachname);
    mainPanel.add(textBoxNachname);
    mainPanel.add(labelEmail); 
    mainPanel.add(textBoxEmail);
    mainPanel.add(labelPasswort);
    mainPanel.add(passwortBox);
    mainPanel.add(registrieren);
    

    
    //Add Style Name
    
    labelreg.addStyleName("labelregister");
    
    mainPanel.addStyleName("mainPanelRegist");
   

    // Associate the Main panel with the HTML host page.
    RootPanel.get("stockList").add(mainPanel);

    
    
    
   
    
   
      }
    
  }
    
    
  
  
      
