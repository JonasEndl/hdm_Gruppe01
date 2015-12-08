package de.hdm.itprojekt.shared;

import java.util.Vector;


public interface MessagingAdministrationAsync {
	
void createNutzer(Nutzer n, AsyncCallback<Nutzer> callback);

void save(Nutzer n, AsyncCallback<Void> callback);

void delete(Nutzer n, AsyncCallback<Void> callback);

void createAbonnement(Abonnement a, AsyncCallback<Abonnement> callback);

void save(Abonnement a, AsyncCallback<Void> callback);

void delete(Abonnement a, AsyncCallback<Void> callback);

void createUnterhaltung(Unterhaltung u, AsyncCallback<Unterhaltung> callback);

void save(Unterhaltung u, AsyncCallback<Void> callback);

void delete(Unterhaltung u, AsyncCallback<Void> callback);

void createHashtag(Hashtag h, AsyncCallback<Hashtag> callback);

void save(Hashtag h, AsyncCallback<Void> callback);

void delete(Hashtag h, AsyncCallback<Void> callback);

void createNachricht(Nachricht na, AsyncCallback<Nachricht> callback);

void save(Nachricht na, AsyncCallback<Void> callback);

void delete(Nachricht na, AsyncCallback<Void> callback);


void init(AsyncCallback<Void> callback);


void getNutzerById(int id, AsyncCallback<Nutzer> callback);

void getHashtagById(int id, AsyncCallback<Hashtag> callback);

void getAbonnementById(int id, AsyncCallback<Abonnement> callback);

void getUnterhaltungById(int id, AsyncCallback<Unterhaltung> callback);

void getNachrichtById(int id, AsyncCallback<Nachricht> callback);


void findAllHashtags(AsyncCallback<Vector<Hashtag>> callback);

void getNachrichtenByNutzer(AsyncCallback<Vector<Nachricht>> callback);

void getAbonnementByNutzer(AsyncCallback<Vector<Abonnement>> callback);

void getAbonnementByHashtag(AsyncCallback<Vector<Abonnement>> callback);	

void getHashtagByNutzer(AsyncCallback<Vector<Hashtag>> callback);

}

