package de.hdm.itprojekt.shared;

public interface ReportGeneratorAsync {

	  void createAllAbonnementOfNutzerReport(Nutzer n,
	      AsyncCallback<AllAbonnementOfNutzerReport> callback);

	  void createAllNachrichtOfNutzerReport(Nutzer n,
	      AsyncCallback<AllNachrichtOfNutzerReport> callback);

	  void init(AsyncCallback<Void> callback);

	  void setNutzer(Nutzer n, AsyncCallback<Void> callback);

	}

