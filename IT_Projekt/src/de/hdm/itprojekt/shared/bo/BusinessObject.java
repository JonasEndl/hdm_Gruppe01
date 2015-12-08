package de.hdm.itprojekt.shared.bo;

import java.io.Serializable;
import java.util.Date;

/** 
 * Superklasse von Abonnement
 * @author JonasEndl
 */

public abstract class BusinessObject implements Serializable {
	
	/**
	 * Attribute anlegen
	 */
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Date erstellungszeitpunkt;
	
	/**
	 * Konstruktor
	 */
	public BusinessObject(){
		
	}
	
	/**
	 * Auslesen der ID
	 * @return id
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * Setzen der ID
	 * @param id
	 */
	public void setID(int id){
		this.id = id;
	}
	
	/**
	 * auslesen Erstellungszeit
	 * @return erstellungszeit
	 */
	public Date getErstellungszeitpunkt(){
		return this.erstellungszeitpunkt;
	}
	
	public void setErstellungszeitpunkt(Date erstellungszeitpunkt){
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}
	
	/**
	 * Erzeugen einer textuellen Darstellung 
	 */
	public String toString() {
		/**
		 * Wir geben den Klassennamen und ID des Objekts zurück
		 */
		return this.getClass().getName() + " #" + this.id;
	  }
	
	/**
	 * 
	 */
	public boolean equals (Object o){
	
		boolean result = false;
		if (o != null && o instanceof BusinessObject){
			BusinessObject b = (BusinessObject) o;
			try {
				if (b.getID() == this.id) {
					result = true;
				}
			}
			catch (IllegalArgumentException e) {
				/**
				 * Bei Fehler false zurückgeben.
				 */
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
}