package de.hdm.itprojekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.impl.AsyncFragmentLoader.Logger;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.CommonSettings;
import de.hdm.itprojekt.shared.MessagingAdministration;
import de.hdm.itprojekt.shared.MessagingAdministrationAsync;
import de.hdm.itprojekt.shared.ReportGenerator;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;

/*Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
 */

public class ClientSideSettings extends CommonSettings {

  /**
   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
   * namens MessaginAdmin
   */

  private static MessagingAdministrationAsync messagingAdministration = null;

  /**
   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
   * namens ReportGenerator
   */
  private static ReportGeneratorAsync reportGenerator = null;

  /**
   * Name des Client-seitigen Loggers.
   */
  private static final String LOGGER_NAME = "MessagingProjekt Web Client";
  
  /**
   * Instanz des Client-seitigen Loggers.
   */
  private static final Logger log = Logger.getLogger(LOGGER_NAME);

  /**
   * <p>
   * Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
*/
     public static Logger getLogger() {
    return log;
  }

public static MessagingAdministrationAsync getMessagingAdministration() {
    if (messagingAdministration == null) {
      messagingAdministration = GWT.create(MessagingAdministration.class);
    }

 
    return messagingAdministration ;
  }

  public static ReportGeneratorAsync getReportGenerator(de.hdm.itprojekt.shared.AsyncCallback<Void> initReportGeneratorCallback) {
	  
    // Gab es bislang noch keine ReportGenerator-Instanz, dann...
    
	  if (reportGenerator == null) {
      
		  // Zunächst instantiieren wir ReportGenerator
      
		  reportGenerator = GWT.create(ReportGenerator.class);

      final AsyncCallback<Void> initReportGenerator = new AsyncCallback<Void>() {
        public void onFailure(Throwable caught) {
          ClientSideSettings.getLogger().severe(
              "Der ReportGenerator konnte nicht initialisiert werden!");
        }

        public void onSuccess(Void result) {
          ClientSideSettings.getLogger().info(
              "Der ReportGenerator wurde initialisiert.");
        }
      };

      reportGenerator.init(initReportGeneratorCallback);
    }
    return reportGenerator;
  }

}
