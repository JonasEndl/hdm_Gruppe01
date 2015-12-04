package de.hdm.itprojekt.shared.report;

/**
 * Klasse überführt auf den Client die ihm vom Server zur Verfügung gestellten 
 * Report Objekte in ein menschenlesbares Format.
 */
public abstract class ReportWriter {

/**
 * Hier wird in das Zielformat übersetzt.
 * @param r der zu übersetzende Report
 */
	  public abstract void process(AllAbonnementOfNutzerReport r);

	 
	  public abstract void process(AllNachrichtOfNutzerReport r);

	}
