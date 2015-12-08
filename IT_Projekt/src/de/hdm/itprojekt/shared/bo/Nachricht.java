package de.hdm.itprojekt.shared.bo;
import java.util.ArrayList;
import java.util.List;




public class Nachricht extends BusinessObject{
	
	
	private static final long serialVersionUID =1L;
	private String text;
	private Nutzer sender;
	private ArrayList<Nutzer> empfaenger;
	private int hashtagID;
	private int unterhaltungsID;
	private int nutzerID;
	
	
	public Nachricht() {
	}
	

	public String getText() {
		return this.text;
	}

	
	
	public void setText(String text) {
		this.text = text;
	}

	public Nutzer getSender() {
		return this.sender;
	}


	public void setSender(Nutzer sender) {
		this.sender = sender;
	}

	
	public List<Nutzer> getEmpfaenger() {
		List<Nutzer> result = new ArrayList<Nutzer>();
		for (Nutzer e : this.empfaenger) {
			result.add(e);
		}
		return result;
	}


	public void setEmpfaenger(Nutzer e) {
		if (e != null) {
			empfaenger.add(e);	}
		else {
		
		}
	}

	
	public int getHashtagID() {
		return hashtagID;
	}
	

	public void setHashtagID(int hashtagID) {
		this.hashtagID = hashtagID;
	}

	
	public int getUnterhaltungsID() {
		return unterhaltungsID;
	}

	
	public void setUnterhaltungsID(int unterhaltungsID) {
		this.unterhaltungsID = unterhaltungsID;
	}


	public int getNutzerID() {
		return nutzerID;
	}
	

	public void setNutzerID(int nutzerID) {
		this.nutzerID = nutzerID;
	}

}
