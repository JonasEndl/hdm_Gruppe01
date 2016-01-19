package de.hdm.itprojekt.shared.bo;

	public class Nutzer extends BusinessObject {

		/**
		 * @author Jonas Endl
		 */
		private static final long serialVersionUID = 1L;
		private String nachname;
		private String vorname;
		private String mailAdresse;

		/**
		 * Konstruktor hinzugefügt.
		 * 
		 * @param vorname
		 * @param nachname
		 * @param mailAdresse
		 */
		public Nutzer (String vorname, String nachname, String mailAdresse){
			this.vorname = vorname;
			this.nachname = nachname;
			this.mailAdresse = mailAdresse;
		}
		
		
			//Getter Nachname
			public String getNachname() {
				return this.nachname;
			}
			//Setter Nachname
			public void setNachname(String nachname) {
				this.nachname = nachname;
			}
			//Getter Vorname
			public String getVorname() {
				return this.vorname;
			}
			// Setter Vorname
			public void setVorname(String vorname) {
				this.vorname = vorname;
			}
			// Getter Mailadresse
			public String getMailadresse() {
				return this.mailAdresse;
			}
			
			// Setter Mailadresse
			public void setMailadresse(String mailadresse) {
				this.mailAdresse = mailadresse;
			}



		}



