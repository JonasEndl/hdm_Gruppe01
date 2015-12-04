package de.hdm.itprojekt.shared.report;

/**
 * Klasse �berf�hrt auf den Client die ihm vom Server zur Verf�gung gestellten 
 * Report Objekte in ein menschenlesbares Format.
 */
public abstract class ReportWriter {

/**
 * Hier wird in das Zielformat �bersetzt.
 * @param r der zu �bersetzende Report
 */
	  public abstract void process(AllAbonnementOfNutzerReport r);

	 
	  public abstract void process(AllNachrichtOfNutzerReport r);

	}
