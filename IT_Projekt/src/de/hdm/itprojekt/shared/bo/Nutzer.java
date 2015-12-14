package de.hdm.itprojekt.shared.bo;

	public class Nutzer extends BusinessObject {

		/**
		 * @author Jonas Endl
		 */
		private static final long serialVersionUID = 1L;
		private String Nachname;
		private String Vorname;
		private String Mailadresse;

			//Getter Nachname
			public String getNachname() {
				return Nachname;
			}
			//Setter Nachname
			public void setNachname(String nachname) {
				Nachname = nachname;
			}
			//Getter Vorname
			public String getVorname() {
				return Vorname;
			}
			// Setter Vorname
			public void setVorname(String vorname) {
				Vorname = vorname;
			}
			// Getter Mailadresse
			public String getMailadresse() {
				return Mailadresse;
			}
			
			// Setter Mailadresse
			public void setMailadresse(String mailadresse) {
				Mailadresse = mailadresse;
			}



		}



