package de.hdm.itprojekt.shared;

import java.util.Vector;

// Das asynchrone Gegenstück zum Interface MessagingAdmin

public interface MessagingAdministrationAsync {
	
//Erstellen des Nutzers-->AsyncCallback

	void createNutzer(Nutzer n, AsyncCallback<Nutzer> callback);

//Speichern des Nutzers-->AsyncCallback

	void saveNutzer(Nutzer n, AsyncCallback<Void> callback);
	
//Löschen des Nutzers-->AsyncCallback

	void deleteNutzer(Nutzer n, AsyncCallback<Void> callback);

/*Erstellen des Abos-->AsyncCallback
 * Anpassung an NutzerAbo+ HAshtag Abo!!
 */
	void createAbonnement(Abonnement a, AsyncCallback<Abonnement> callback);

//Speichern des Abos-->AsyncCallback
	
	void saveAbonnement(Abonnement a, AsyncCallback<Void> callback);
	
//Löschen des Abos-->AsyncCallback

	void deleteAbonnement(Abonnement a, AsyncCallback<Void> callback);

//Erstellen der Unterhaltung-->AsyncCallback
	
	void createUnterhaltung(Unterhaltung u, AsyncCallback<Unterhaltung> callback);
	
//Speichern des Unterhaltung-->AsyncCallback

	void saveUnterhaltung(Unterhaltung u, AsyncCallback<Void> callback);

//Löschen des Unterhaltung-->AsyncCallback
	
	void deleteUnterhaltung(Unterhaltung u, AsyncCallback<Void> callback);

//Erstellen des Hashtags-->AsyncCallback
	
	void createHashtag(Hashtag h, AsyncCallback<Hashtag> callback);

//speichern des Hashtags-->AsyncCallback
	
	void saveHashtag(Hashtag h, AsyncCallback<Void> callback);

//Löschen des Hashtags-->AsyncCallback
	
	void deleteHashtag(Hashtag h, AsyncCallback<Void> callback);

//Erstellen der Nachricht-->AsyncCallback
	
	void createNachricht(Nachricht na, AsyncCallback<Nachricht> callback);

//Speichern der Nachricht-->AsyncCallback
	
	void saveNachricht(Nachricht na, AsyncCallback<Void> callback);

//Löschen der Nachricht-->AsyncCallback
	
	void deleteNachricht(Nachricht na, AsyncCallback<Void> callback);

//Initialisierungsmethode-->AsyncCallback
	
	void init(AsyncCallback<Void> callback);

//Nutzer durch ID finden-->AsyncCallback
	
	void getNutzerById(int id, AsyncCallback<Nutzer> callback);

//Hashtag durch ID finden-->AsyncCallback
	
	void getHashtagById(int id, AsyncCallback<Hashtag> callback);

/*Abos durch ID finden-->AsyncCallback
 * Abos anpassen!!
 */
	void getAbonnementById(int id, AsyncCallback<Abonnement> callback);

//Unterhaltung durch ID finden-->AsyncCallback
	
	void getUnterhaltungById(int id, AsyncCallback<Unterhaltung> callback);

//Nachricht durch ID finden-->AsyncCallback
	
	void getNachrichtById(int id, AsyncCallback<Nachricht> callback);

//Alle Hashtags finden-->AsyncCallback
	
	void findAllHashtags(AsyncCallback<Vector<Hashtag>> callback);

//Nachrichten durch Nutzer finden-->AsyncCallback
	
	void getNachrichtenByNutzer(AsyncCallback<Vector<Nachricht>> callback);

/* NutzerAbos durch Nutzer finden -->AsyncCallback
 * Anpassung der Abos!!!
 */
	
	void getAbonnementByNutzer(AsyncCallback<Vector<Abonnement>> callback);

	/* HashtagAbos durch Nutzer finden -->AsyncCallback
	 * Anpassung der Abos!!!
	 */
	
	void getAbonnementByHashtag(AsyncCallback<Vector<Abonnement>> callback);	

	/* Hashtag durch Nutzer finden -->AsyncCallback
	 */
	
	void getHashtagByNutzer(AsyncCallback<Vector<Hashtag>> callback);
}

