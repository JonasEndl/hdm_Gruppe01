package de.hdm.itprojekt.shared.bo;

/** 
 * BusinessObject ist die Superklasse von Abonnement
 * @author JonasEndl
 */
public class Abonnement extends BusinessObject {
	
	/**
	 * Attribute eines Abonnements
	 */
	private static final long serialVersionUID = 1L;
	private Nutzer abonnent = null;
	

	/**
	 * Konstruktor
	 */
	
	public Abonnement () {
	}
	
	
	/**
	 * Auslesen des Abonnenten
	 * @return abonnent
	 */
	public Nutzer getAbonnent() {
		return abonnent;
	}
	
	/**
	 * Setzen eines Abonnenten
	 * @param abonnent
	 */
	public void setAbonnent(Nutzer abonnent) {
		this.abonnent = abonnent;
	}
	
}