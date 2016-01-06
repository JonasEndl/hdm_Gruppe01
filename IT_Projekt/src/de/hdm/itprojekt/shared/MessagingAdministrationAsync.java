package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.*;

// Das asynchrone Gegenstück zum Interface MessagingAdmin

public interface MessagingAdministrationAsync {
	
//Erstellen des Nutzers-->AsyncCallback

	void createNutzer(Nutzer n, AsyncCallback<Nutzer> callback);

//Speichern des Nutzers-->AsyncCallback

	void saveNutzer(Nutzer n, AsyncCallback<Void> callback);
	
//Löschen des Nutzers-->AsyncCallback

	void deleteNutzer(Nutzer n, AsyncCallback<Void> callback);

/*Erstellen des Abos-->AsyncCallback
 */
	void createNutzerAbo(NutzerAbo na, AsyncCallback<NutzerAbo> callback);
	void createHashtagAbo(HashtagAbo ha, AsyncCallback<HashtagAbo> callback);
	
//Speichern des Abos-->AsyncCallback
	
	void saveNutzerAbo(NutzerAbo na, AsyncCallback<NutzerAbo> callback);
	void saveHashtagAbo(HashtagAbo ha, AsyncCallback<HashtagAbo> callback);
	
//Löschen des Abos-->AsyncCallback

	void deleteNutzerAbo(NutzerAbo na, AsyncCallback<NutzerAbo> callback);
	void deleteHashtagAbo(HashtagAbo ha, AsyncCallback<HashtagAbo> callback);
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
 
 */
	void getNutzerAboById(int id, AsyncCallback<NutzerAbo> callback);
	void getHashtagAboById(int id, AsyncCallback<HashtagAbo> callback);
	
//Unterhaltung durch ID finden-->AsyncCallback
	
	void getUnterhaltungById(int id, AsyncCallback<Unterhaltung> callback);

//Nachricht durch ID finden-->AsyncCallback
	
	void getNachrichtById(int id, AsyncCallback<Nachricht> callback);

//Alle Hashtags finden-->AsyncCallback
	
	void findAllHashtags(AsyncCallback<Vector<Hashtag>> callback);

//Nachrichten durch Nutzer finden-->AsyncCallback
	
	void getNachrichtenByNutzer(AsyncCallback<Vector<Nachricht>> callback);

/* NutzerAbos durch Nutzer finden -->AsyncCallback
 
 */
	
	void getAllNutzerAbo(AsyncCallback<Vector<NutzerAbo>> callback);

	/* HashtagAbos durch Nutzer finden -->AsyncCallback
	
	 */
	
	void getAllHashtagAbo(AsyncCallback<Vector<HashtagAbo>> callback);	

	/* Hashtag durch Nutzer finden -->AsyncCallback
	 */
	
	void getHashtagByNutzer(Nutzer n, AsyncCallback<Vector<Hashtag>> callback);
	
	//Alle Nutzer
	
	void getAllNutzer(AsyncCallback<Vector<Nutzer>> callback);
	
	/*Alle Nutzer, die man noch abonnieren kann,
		also noch nicht abonniert wurden.
	*/

	void getAllAbonnierbareNutzer(AsyncCallback<Vector<Nutzer>> callback);

}

