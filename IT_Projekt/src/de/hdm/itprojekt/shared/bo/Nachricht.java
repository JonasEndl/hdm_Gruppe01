package de.hdm.itprojekt.shared.bo;
import java.util.ArrayList;
import java.util.List;

/**
 * BusinessObject, ist die Superklasse von Nachricht
 * 
 * @author tinohauler
 *
 */

public class Nachricht extends BusinessObject{
	
	/**
	 * Anlegen der Attribute
	 */
	private static final long serialVersionUID =1L;
	private String text;
	private Nutzer sender;
	private ArrayList<Nutzer> empfaenger;
	private int hashtagID;
	private int unterhaltungsID;
	private int nutzerID;
	
	/**
	 * Konstruktor
	 */
	public Nachricht() {
	}
	
	
	/**Auslesen des Textes
	 * @return text
	 */
	public String getText() {
		return this.text;
	}

	
	/**
	 * Setzen des Textes
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Auslesen des Senders
	 * @return sender
	 */
	public Nutzer getSender() {
		return this.sender;
	}

	/**
	 * Setzen des Senders
	 * @param sender
	 */
	public void setSender(Nutzer sender) {
		this.sender = sender;
	}

	/**
	 * Auslesen des Empfaengers aus der ArrayList<Nutzer>
	 * @return emfaenger
	 */
	public List<Nutzer> getEmpfaenger() {
		List<Nutzer> result = new ArrayList<Nutzer>();
		for (Nutzer e : this.empfaenger) {
			result.add(e);
		}
		return result;
	}

	/**
	 * Setzen des Emfaengers in einer Nachricht
	 * @param empfaenger
	 */
	public void setEmpfaenger(Nutzer e) {
		if (e != null) {
			empfaenger.add(e);	}
		else {
			//keinen Empfaenger setzen 
		}
	}

	/**
	 * Auslesen der HashtagID
	 * @return hashtagID
	 */
	public int getHashtagID() {
		return hashtagID;
	}
	
	/**
	 * Setzen der HashtagID
	 * @param hashtagID
	 */
	public void setHashtagID(int hashtagID) {
		// TODO Auto-generated method stub
		this.hashtagID = hashtagID;
	}

	/**
	 * Auslesen der UnterhaltungsID
	 * @return unterhaltungsID
	 */
	public int getUnterhaltungsID() {
		return unterhaltungsID;
	}

	/**
	 * Setzen der UnterhaltungsID
	 * @param unterhaltungsID
	 */
	public void setUnterhaltungsID(int unterhaltungsID) {
		// TODO Auto-generated method stub
		this.unterhaltungsID = unterhaltungsID;
	}

	/**
	 * Auslesen der NutzerID
	 * @return nutzerID
	 */
	public int getNutzerID() {
		return nutzerID;
	}
	
	/**
	 * Setzen der NutzerID
	 * @param nutzerID
	 */
	public void setNutzerID(int nutzerID) {
		// TODO Auto-generated method stub
		this.nutzerID = nutzerID;
	}

}