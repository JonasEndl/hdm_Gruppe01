package de.hdm.itprojekt.shared.report;

/**
 * <p>
 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu überführen.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat überführten Information wird den Subklassen überlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die für die
 * Prozessierung der Quellinformation zuständig sind.
 * </p>
 * 
 * @author Teuta
 */
public abstract class ReportWriter {

/**
 * Hier wird das <code>AllAbonnementOfNutzerReport</cod> in das Zielformat übersetzt.
 * 
 * @param r der zu übersetzende Report
 */
	  public abstract void process(AllAbonnementOfNutzerReport r);
/**
 * Hier wird das <code>AllNachrichtOfNutzerReport</code> in das Zielformat übersetzt.
 * 
 * @param r der zu übersetzende Report
 */
	 
	  public abstract void process(AllNachrichtOfNutzerReport r);

	}
