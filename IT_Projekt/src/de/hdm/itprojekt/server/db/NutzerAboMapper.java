package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;


public class NutzerAboMapper {

	/**
	 * Die Klasse NutzerAbonnementMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static NutzerAboMapper nutzerAboMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected NutzerAboMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerAbonnementMapper.nutzerAbonnementMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>NutzerAbonnementMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerAbonnementMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NutzerAbonnementMapper</code>-Objekt.
	 * @see nutzerAbonnementMapper
	 */

	public static NutzerAboMapper nutzerAboMapper() {
		if (nutzerAboMapper == null) {
			nutzerAboMapper = new NutzerAboMapper();
		}
		return nutzerAboMapper;
	}

	/**
	 * Suchen eines HashtagAbo mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Hashtag-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public NutzerAbo findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT id, schlagwort FROM nutzerAbo " + "WHERE id=" + id + " ORDER BY id");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				NutzerAbo nab = new NutzerAbo();
				nab.setId(rs.getInt("id"));
				nab.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));
				

				return nab;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller NutzerAbonnements.
	 * 
	 * @return Ein Vektor mit NutzerAbonnement-Objekten, die sämtliche NutzerAbonnements
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<NutzerAbo> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<NutzerAbo> result = new Vector<NutzerAbo>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzerAbo` ORDER BY `id`");

			// Für jeden Eintrag im Suchergebnis wird nun ein NutzerAbo-Objekt
			// erstellt.
			while (rs.next()) {
				NutzerAbon nab = new NutzerAbo();
				nab.setId(rs.getInt("id"));
				nab.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));


				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(nab);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines <code>NutzerAbo</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param nab
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public NutzerAbo insert(NutzerAbo nab) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM NutzerAbo ");
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * nab erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				nab.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO HashtagAbo (id, Erstellungszeitpunkt, schlagwort) " + "VALUES (" + nab.getId()
						+ ", " + nab.Erstellungszeitpunkt() + ", " + nab.schlagwort() + ", " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, der evtl. korrigierten NutzerAbonnement.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des NutzerAbonnement-Objekts
		 * auch ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar.
		 * Die explizite Rückgabe von nab ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */

		return nab;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param nab
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public NutzerAbo update(NutzerAbo nab) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE HashtagAbonnement " + "SET schlagwort=\"" +
			// ha.gettext() + "\" " + "WHERE id=" + ha.getId());

			stmt.executeUpdate("UPDATE `nutzerAbo` SET `Erstellungszeitpunkt`='"
					+ "' WHERE `id`= " + nab.getId() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(NutzerAbonnement nab) zu wahren, geben wir nab zurück
		return nab;
	}

	/**
	 * Löschen der Daten eines <code>NutzerAbo</code>-Objekts aus der Datenbank.
	 * 
	 * @param nab
	 *           das aus der DB zu löschende "Objekt"
	 */

	public void delete(NutzerAbo nab) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM nutzerAbon" + "WHERE id=" + nab.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}