package de.hdm.itprojekt.shared.bo;

import java.util.Vector;

public class Unterhaltung extends BusinessObject {
	

	private static final long serialVersionUID = 1L;
	private Vector <Nutzer> NutzerListe;
	private Vector <Nachricht> nachrichtenListe;
	private Nutzer initiator;
	
	public Vector<Nutzer> getNutzerListe() {
		return NutzerListe;
	}
	public void setNutzerListe(Vector<Nutzer> NutzerListe) {
		this.NutzerListe = NutzerListe;
	}
	public Vector<Nachricht> getnachrichtenListe() {
		return nachrichtenListe;
	}
	public void setnachrichtenListe(Vector<Nachricht> nachrichtenListe) {
		this.nachrichtenListe = nachrichtenListe;
	}
	public Nutzer getInitiator() {
		return initiator;
	}
	public void setInitiator(Nutzer initiator) {
		this.initiator = initiator;
	}
}