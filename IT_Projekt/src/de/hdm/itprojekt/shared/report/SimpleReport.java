package de.hdm.itprojekt.shared.report;

import java.util.Vector;

/**
 * einfacher Report. Weist Informationen der Superklasse Report und eine Tabelle mit
 * Positionsdaten auf. Greift auf Hilfsklassen <code>Row</code> und <code>Column</code> zurück.
 * 
 * 
 */

public abstract class SimpleReport extends Report {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<Row> table = new Vector<Row>();
	
	public void addRow(Row r){ 
		
		this.table.addElement(r);
	}
	
	public void removeRow(Row r){ 
		
		this.table.removeElement(r);
	}
	
	public Vector<Row> getRows(){
		
		return this.table;
	}
	

	
}
