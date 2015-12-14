package de.hdm.itprojekt.shared.bo;
public class HashtagAbo extends Abonnement{
	
	/**
	 * Attribute eines Hashtagabos
	 */

	private static final long serialVersionUID = 1L;
	private int hasthtagAboID;
	private Abonnement hashtagAbo;

	public HashtagAbo () {
		
	}
	
	public int gethasthtagAboID () {
		return hasthtagAboID;
	}
	
	
	public void setAbHashtagID (int hasthtagAboID) {
		this.hasthtagAboID = hasthtagAboID;
	}
	
	public Abonnement getHashtagAbonnement () {
		return hashtagAbo;
	}


	public void setHashtagAbo(Abonnement hashtagAbo) {
		this.hashtagAbo = hashtagAbo;
	}

}