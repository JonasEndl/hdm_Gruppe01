package de.hdm.itprojekt.server;

import java.util.logging.Logger;

import de.hdm.itprojekt.shared.CommonSettings;

//Klasse mit Eigenschaften und Diensten, die f�r alle Serverseitige Klassen wichtig sind


public class ServersideSettings extends CommonSettings {

  private static final String LOGGER_NAME = "MessagingProject Server";
  private static final Logger log = Logger.getLogger(LOGGER_NAME);


  public static Logger getLogger() {
    return log;
  }

}
