package de.hdm.itprojekt.shared.bo;


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
