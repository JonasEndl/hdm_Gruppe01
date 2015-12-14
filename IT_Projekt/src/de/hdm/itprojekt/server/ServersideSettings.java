import java.util.logging.Logger;

//Klasse mit Eigenschaften und Diensten, die für alle Serverseitige Klassen wichtig sind


public class ServersideSettings extends CommonSettings {

  private static final String LOGGER_NAME = "MessagingProject Server";
  private static final Logger log = Logger.getLogger(LOGGER_NAME);


  public static Logger getLogger() {
    return log;
  }

}
