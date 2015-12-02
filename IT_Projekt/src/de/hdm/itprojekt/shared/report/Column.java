package de.hdm.itprojekt.shared.report;

import java.io.Serializable;

/**
 * Spalte eine <code>Row</code>- Objects inklusive Serializable Interface. So kann eine Kopie vom Server
 * an den Client übertragen werden.
 * @author Teuta
 */

public class Column implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
/**
 * 
 * Wert Spaltenobjekt entspricht Zeileneintrag der Tabelle
 */

	
	private String value = "";
	
/**
 * No-Argument Konstruktor
 */
	
	public Column() {
	}
	
/**
 * Konstruktor. Erzwingt die Angabe eines Werts(Spalteneintrag).
 * @param s der Wert, der durch das Column- Objekt dargestellt werden soll.
 */
	
	public Column(String s) {
	this.value = s;
	}
	
/**
 * Auslesen des Spaltenwerts.
 * @return der Eintrag als String
 */
	public String getValue() {
		
		return value;
	}
	

/**
 * Überschreiben des aktuellen Spaltenwerts.
 * @param value neuer Spaltenwert
 */
	public void setValue(String value) {
		this.value = value;
	}

/**
 * Umwandeln des <code>Column</code>- Objekts in einen String.
 *
 */
	public String toString() {
		return this.value;
	}


}
