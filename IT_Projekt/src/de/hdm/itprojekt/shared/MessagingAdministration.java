package de.hdm.itprojekt.shared;

import java.util.Vector;

// Synchrone Schnittstelle für eine RPC-fähige Klasse für die MessaginAdmin.

public interface MessagingAdministration extends RemoteService {

	/* Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse
	   * MessagingAdministration notwendig. Bitte diese Methode direkt nach der
	     Instantiierung aufrufen.
	*/
public void init() throws IllegalArgumentException;

// Anlegen von Nutzer + Abos + Hashtag + Unterhaltung + Nachricht


public Nutzer createNutzer(Nutzer n) throws IllegalArgumentException;
public HashtagAbo createHashtagAbo ( HashtagAbo ha) throws IllegalArgumentException;
public NutzerAbo createNutzerAbo ( NutzerAbo na) throws IllegalArgumentException;
public Hashtag createHashtag(Hashtag h) throws IllegalArgumentException;
public Unterhaltung createUnterhaltung (Unterhaltung u) throws IllegalArgumentException;
public Nachricht createNachricht( Nachricht na) throws IllegalArgumentException;

/* Speichern der Nutzer + Abos + Hashtag + Unterhaltung + Nachricht

 */

public void saveNutzer(Nutzer n) throws IllegalArgumentException;
public void saveHashtag( Hashtag h) throws IllegalArgumentException;
public void saveHashtagAbo ( HashtagAbo ha) throws IllegalArgumentException;
public void saveNutzerAbo ( NutzerAbo na) throws IllegalArgumentException;
public void saveUnterhaltung(Unterhaltung u) throws IllegalArgumentException;
public void saveNachricht(Nachricht na) throws IllegalArgumentException;

/* Löschen der Nutzer + Abos + Hashtag + Unterhaltung + Nachricht
 */
public void deleteNutzer(Nutzer n) throws IllegalArgumentException;
public void deleteHashtag( Hashtag h) throws IllegalArgumentException;
public void deleteHashtagAbo ( HashtagAbo ha) throws IllegalArgumentException;
public void deleteNutzerAbo ( NutzerAbo na) throws IllegalArgumentException;
public void deleteUnterhaltung(Unterhaltung u) throws IllegalArgumentException;
public void deleteNachricht(Nachricht  na) throws IllegalArgumentException;

/* Finden der Nutzer + Abos + Hashtag + Unterhaltung + Nachricht anhand der ID

 */
public Nutzer getNutzerById(int id) throws IllegalArgumentException;
public Hashtag getHashtagById(int id) throws IllegalArgumentException;
public HashtagAbo getHashtagAboById ( int id) throws IllegalArgumentException;
public NutzerAbo getNutzerAboById (int id) throws IllegalArgumentException;
public Unterhaltung getUnterhaltungById(int id) throws IllegalArgumentException;
public Nachricht getNachrichtById(int id) throws IllegalArgumentException;

/* Finden Aller Hashtags
 */
public Vector<Hashtag> findAllHashtags() throws IllegalArgumentException;

//Nachricht anhand eines Nutzers finden
public Vector<Nachricht> getNachrichtbyNutzer(Nutzer n)
    throws IllegalArgumentException;

/*NutzerAbos finden
 */

public Vector<NutzerAbo> getAllNutzerAbo()
    throws IllegalArgumentException;

/*HashtagAbos finden
 */

public Vector<HashtagAbo> getAllHashtagAbo()
    throws IllegalArgumentException;

/*Hashtags anhand eines Nutzers finden
 */

public Vector<Hashtag> getHashtagbyNutzer(Nutzer n)
  
	throws IllegalArgumentException;

//Alle Nutzer
public Vector<Nutzer>getAllNutzer() 
		throws IllegalArgumentException;

/*Alle Nutzer, die man noch abonnieren kann,
	also noch nicht abonniert wurden.
*/


public Vector<Nutzer>getAllAbonnierbareNutzer()
		throws IllegalArgumentException;

}

