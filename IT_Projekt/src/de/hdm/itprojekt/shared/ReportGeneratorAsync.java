package de.hdm.itprojekt.shared;


// Das asynchrone Gegenstück des Interfaces ReportGenerator 

public interface ReportGeneratorAsync {

// Report aller Abos eines Nutzers-->AsyncCallback
	
	  void createAllAbonnementOfNutzerReport(Nutzer n,
	      AsyncCallback<AllAbonnementOfNutzerReport> callback);

// Report aller Nachrichten eines Nutzers-->AsyncCallback
	  
	  void createAllNachrichtOfNutzerReport(Nutzer n,
	      AsyncCallback<AllNachrichtOfNutzerReport> callback);

// Initialisierungsmethode-->AsyncCallback
	  
	  void init(AsyncCallback<Void> callback);

//Nutzer setzen-->AsyncCallback
	  
	  void setNutzer(Nutzer n, AsyncCallback<Void> callback);

	}

