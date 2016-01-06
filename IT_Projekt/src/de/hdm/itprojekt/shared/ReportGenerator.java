package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.report.AllAbonnementOfNutzerReport;
import de.hdm.itprojekt.shared.report.AllNachrichtOfNutzerReport;


/* Synchrone Schnittstelle für eine RPC-fähige Klasse
   zur Erstellung von Reports
   
   Ein ReportGenerator bietet die Möglichkeit, eine Menge von Reports
   zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
   Systems  darstellen.
*/

public interface ReportGenerator extends RemoteService {

// Initialisierungsmethode ohne Argumente
	
	  public void init() throws IllegalArgumentException;

// Nutzer setzen

	  public void setNutzer(Nutzer n) throws IllegalArgumentException;

//Report für alle Abos eines Nutzers
	  
	  public abstract AllAbonnementOfNutzerReport createAllAbonnementOfNutzerReport(
	      Nutzer n) throws IllegalArgumentException;

// Report für alle Nachrichten eines Nutzers
	  
	  public abstract AllNachrichtOfNutzerReport createAllNachrichtOfNutzerReport(
	      Nutzer n) throws IllegalArgumentException;

	}
