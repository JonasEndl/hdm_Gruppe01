package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.report.AllAbonnementOfNutzerReport;
import de.hdm.itprojekt.shared.report.AllNachrichtOfNutzerReport;


/* Synchrone Schnittstelle f�r eine RPC-f�hige Klasse
   zur Erstellung von Reports
   
   Ein ReportGenerator bietet die M�glichkeit, eine Menge von Reports
   zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des
   Systems  darstellen.
*/

public interface ReportGenerator extends RemoteService {

// Initialisierungsmethode ohne Argumente
	
	  public void init() throws IllegalArgumentException;

// Nutzer setzen

	  public void setNutzer(Nutzer n) throws IllegalArgumentException;

//Report f�r alle Abos eines Nutzers
	  
	  public abstract AllAbonnementOfNutzerReport createAllAbonnementOfNutzerReport(
	      Nutzer n) throws IllegalArgumentException;

// Report f�r alle Nachrichten eines Nutzers
	  
	  public abstract AllNachrichtOfNutzerReport createAllNachrichtOfNutzerReport(
	      Nutzer n) throws IllegalArgumentException;

	}
