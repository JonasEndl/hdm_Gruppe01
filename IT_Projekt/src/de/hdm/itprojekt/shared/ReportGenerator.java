package de.hdm.itprojekt.shared;

public interface ReportGenerator extends RemoteService {

	  public void init() throws IllegalArgumentException;

	  
	  public void setNutzer(Nutzer n) throws IllegalArgumentException;

	  public abstract AllAbonnementOfNutzerReport createAllAbonnementOfNutzerReport(
	      Nutzer n) throws IllegalArgumentException;

	  public abstract AllNachrichtOfNutzerReport createAllNachrichtOfNutzerReport(
	      Nutzer n) throws IllegalArgumentException;



	}
