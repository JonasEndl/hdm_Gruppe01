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
// Anpassung an NutzerAbo + HashtagAbo notwendig

public Nutzer createNutzer(Nutzer n) throws IllegalArgumentException;
public Abonnement createAbonnement ( Abonnement a) throws IllegalArgumentException;
public Hashtag createHashtag(Hashtag h) throws IllegalArgumentException;
public Unterhaltung createUnterhaltung (Unterhaltung u) throws IllegalArgumentException;
public Nachricht createNachricht( Nachricht na) throws IllegalArgumentException;

/* Speichern der Nutzer + Abos + Hashtag + Unterhaltung + Nachricht
 * Anpassung an NutzerAbo + HashtagAbo notwendig!!
 */

public void saveNutzer(Nutzer n) throws IllegalArgumentException;
public void saveHashtag( Hashtag h) throws IllegalArgumentException;
public void saveAbonnement( Abonnement a) throws IllegalArgumentException;
public void saveUnterhaltung(Unterhaltung u) throws IllegalArgumentException;
public void saveNachricht(Nachricht na) throws IllegalArgumentException;

/* Löschen der Nutzer + Abos + Hashtag + Unterhaltung + Nachricht
 * Anpassung an NutzerAbo + HashtagAbo notwendig!!
 */
public void deleteNutzer(Nutzer n) throws IllegalArgumentException;
public void deleteHashtag( Hashtag h) throws IllegalArgumentException;
public void deleteAbonnement( Abonnement a) throws IllegalArgumentException;
public void deleteUnterhaltung(Unterhaltung u) throws IllegalArgumentException;
public void deleteNachricht(Nachricht  na) throws IllegalArgumentException;

/* Finden der Nutzer + Abos + Hashtag + Unterhaltung + Nachricht anhand der ID
 * Anpassung an NutzerAbo + HashtagAbo notwendig!!
 */
public Nutzer getNutzerById(int id) throws IllegalArgumentException;
public Hashtag getHashtagById(int id) throws IllegalArgumentException;
public Abonnement getAbonnementById(int id) throws IllegalArgumentException;
public Unterhaltung getUnterhaltungById(int id) throws IllegalArgumentException;
public Nachricht getNachrichtById(int id) throws IllegalArgumentException;

/* Finden Aller Hashtags
 */
public Vector<Hashtag> findAllHashtags() throws IllegalArgumentException;

//Nachricht anhand eines Nutzers finden
public Vector<Nachricht> getNachrichtbyNutzer(Nutzer n)
    throws IllegalArgumentException;

/*Abos anhand eines Nutzers finden
 * Anpassung NutzerAbo+ HashtagAbo
 */

public Vector<Abonnement> getAbonnementbyNutzer(Nutzer n)
    throws IllegalArgumentException;

/*Abos anhand eines Hashtags finden
 * Anpassung NutzerAbo+ HashtagAbo
 */

public Vector<Abonnement> getAbonnementbyNutzer(Hashtag h)
    throws IllegalArgumentException;

/*Hashtags anhand eines Nutzers finden
 */

public Vector<Hashtag> getHashtagbyNutzer(Nutzer n)
    throws IllegalArgumentException;

}

