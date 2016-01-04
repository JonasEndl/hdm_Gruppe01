package de.hdm.itprojekt.shared.report;

/**
 * <p>
 * Diese Klasse wird ben�tigt, um auf dem Client die ihm vom Server zur
 * Verf�gung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu �berf�hren.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat �berf�hrten Information wird den Subklassen �berlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die f�r die
 * Prozessierung der Quellinformation zust�ndig sind.
 * </p>
 * 
 * @author Teuta
 */
public abstract class ReportWriter {

/**
 * Hier wird das <code>AllAbonnementOfNutzerReport</cod> in das Zielformat �bersetzt.
 * 
 * @param r der zu �bersetzende Report
 */
	  public abstract void process(AllAbonnementOfNutzerReport r);
/**
 * Hier wird das <code>AllNachrichtOfNutzerReport</code> in das Zielformat �bersetzt.
 * 
 * @param r der zu �bersetzende Report
 */
	 
	  public abstract void process(AllNachrichtOfNutzerReport r);

	}
