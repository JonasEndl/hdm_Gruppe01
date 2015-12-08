package de.hdm.itprojekt.shared.bo;
public class HashtagAbo extends Abonnement{
	

	private static final long serialVersionUID = 1L;
	private int aboHashtagID;
	private Abonnement hashtagAbo;

	public HashtagAbo () {
		
	}
	
	public int getAborHashtagID () {
		return aboHashtagID;
	}
	
	
	public void setAbHashtagID (int aboHashtagID) {
		this.aboHashtagID = aboHashtagID;
	}
	
	public Abonnement getHashtagAbonnement () {
		return hashtagAbo;
	}


	public void setHashtagAbo(Abonnement hashtagAbo) {
		this.hashtagAbo = hashtagAbo;
	}

}