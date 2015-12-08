package de.hdm.itprojekt.shared.bo;

/**
 * Klasse Abonnement ist die Superklasse von HashtagAbonnement
 * @author Sarah Weimer
 *
 */
public class HashtagAbo extends Abonnement{
	
	/**
	 * Attribute eines Hashtag Abonnements
	 */
	private static final long serialVersionUID = 1L;
	private int aboHashtagID;
	private Abonnement hashtagAbo;

	/**
	 * Konstruktor
	 */
	public HashtagAbo() {
	
	}
	
	/**
	 * Auslesen der abonnierten Hashtag ID
	 * @return abonnierterHashtagID
	 */
	public int getAboHashtagID () {
		return aboHashtagID;
	}
	
	/**
	 * Setzen der ID
	 * @param aboHashtagID
	 */
	public void setAboHashtagID (int aboHashtagID) {
		this.aboHashtagID = aboHashtagID;
	}
	
	/**
	 * Auslesen der HashtagAbos
	 * @return
	 */
	public Abonnement getHashtagAbo () {
		return hashtagAbo;
	}

	/**
	 * Setzen der HashtagAbos
	 * @param hashtagAbo
	 */
	public void setHashtagAbo(Abonnement hashtagAbo) {
		this.hashtagAbo = hashtagAbo;
	}

}
