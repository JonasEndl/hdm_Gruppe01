package de.hdm.itprojekt.shared.bo;

public class Hashtag extends BusinessObject {

	/**
	 *  Klasse serialisierbar durch die ID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Die Variablen deklarieren1
	 */

	private String schlagwort;


	/**
	 * Getter-Methode f�r das Schlagwort
	 * 
	 */

	public String getSchlagwort() {
		return schlagwort;
	}

	/**
	 * Setter-Methode f�r das Schlagwort
	 * 
	 */

	public void setSchlagwort(String schlagwort) {
		this.schlagwort = schlagwort;
	}

}
