package de.hdm.itprojekt.shared.report;

import java.util.Vector;

/**
 * Hier haben wir einen einfachen Report. Dieser weist Informationen der Superklasse <code>Report</code> und eine Tabelle mit
 * "Positionsdaten" auf. Es wird von der Tabelle auf zwei Hilfsklassen <code>Row</code> und <code>Column</code> zurückgegriffen.
 * 
 * Die Liste einer Bestellposition eines Bestellscheins, welche beispielsweise Artikelnummer und Menge in
 * der Tabelle aufweist sind vergleichbar mit den "Positionsdaten".
 * @see Row
 * @see Column
 * @author Teuta
 * 
 */

public abstract class SimpleReport extends Report {
	
	private static final long serialVersionUID = 1L;
	
/**
 * Die Tabelle mit den Positionsdaten wird in diesem <code>Vector</code> zeilenweise abgelegt.
 */
	
	private Vector<Row> table = new Vector<Row>();
	
/** Hier wird eine Zeile hinzugefügt.
 * 
 * @param r die hinzufügende Zeile.
 */
	public void addRow(Row r){ 
		
		this.table.addElement(r);
	}
	
/** Hier wird eine Zeile entfernt.
 * 
 * @param r die zu entfernende Zeile.
 */
	public void removeRow(Row r){ 
		
		this.table.removeElement(r);
	}

/** Hier werden alle Positionsdaten ausgelesen.
 * 
 * @return die Tabelle der Positionsdaten.
 */
	
	public Vector<Row> getRows(){
		
		return this.table;
	}
	

	
}
