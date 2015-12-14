package de.hdm.itprojekt.shared.bo;


public class NutzerAbo extends Abonnement {
	
	/**
	 * @author Jonas Endl
	 */
	private static final long serialVersionUID = 1L;
	private Nutzer nutzerAbo;

	public Nutzer getNutzerAbo() {
		return nutzerAbo;
	}

	public void setNutzerAbo(Nutzer nutzerAbo) {
		this.nutzerAbo = nutzerAbo;
	}

}

