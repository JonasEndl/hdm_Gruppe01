package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.*;

import java.sql.DriverManager;
import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.api.rdbms.AppEngineDriver;

public class DBConnection {

	private static Connection con = null;
	 
	/**
	 * Verwalten einer Verbindung zur Datenbank.<p>
	 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.<p>
	 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
	 * fest vorgegebene Datenbank zugegriffen werden.<p>
	 * 
	 * @author Thies
	 */
	
	private static String googleUrl = "jdbc:google:rdbms://130.211.66.99it-projektgruppe01:itprojekt15/itprojekt16?user=root&password=root";


	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ... 
		try {
			if ( con == null || con.isClosed() ) {
				try {
					// Ersteinmal muss der passende DB-Treiber geladen werden
					DriverManager.registerDriver(new AppEngineDriver());

					/*
					 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
					 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
					 * 
					 * Diese Verbindung wird dann in der statischen Variable con 
					 * abgespeichert und fortan verwendet.
					 */
					con = DriverManager.getConnection(googleUrl);
				} 
				catch (SQLException e1) {
					con = null;
					e1.printStackTrace();
				}
			}
		}
		catch (SQLException e1) {
			con = null;
			e1.printStackTrace();
		}
		
		// Zurückgegeben der Verbindung
		return con;
	}
	
	/**
	 * Diese statische Methode kann aufgrufen werden durch 
	 * <code>DBConnection.closeConnection()</code>. Sie löst
	 * eine bestehende Verbindung zur Datenbank auf.
	 * 
	 * @throws	RuntimeException - beim "kappen" der DB-
	 * 			Verbindung kann ein Fehler entstehen,
	 * 			welcher mittelbar an die aufrufende Methode
	 * 			weitergeleitet wird
	 */
	public static void closeConnection() throws RuntimeException {
		try {
			if (con != null && (!con.isClosed())) {
				try {
					con.close();
					con = null;
				}
				catch (SQLException e1) {
					throw new RuntimeException("Fehler beim Trennen der DB-Verbindung aufgetreten: " + e1.getMessage());
				}
			}
		}
		catch (SQLException e1) {
			throw new RuntimeException("Fehler beim Trennen der DB-Verbindung aufgetreten: " + e1.getMessage());
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
    public static Connection connection() {
        if (con == null) {
            String url = null;
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                    url = googleUrl;
                } else {
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                }

                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }
        return con;
    }
}
*/