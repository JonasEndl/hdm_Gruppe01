package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

/**
 * Mapper-Klasse, die <code>HashtagAbo</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 * 
 * 
 * 
 * @author Thies
 */

public class HashtagAboMapper {

	/**
	 * Die Klasse HashtagAbonnementMapper wird nur einmal instantiiert. Man
	 * spricht hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static HashtagAboMapper hashtagAboMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected HashtagAboMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>HashtagAboMapper.hashtagAboMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>HashtagAboMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> HashtagAboMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>HashtagAboMapper</code>-Objekt.
	 * @see hashtagAboMapper
	 */

	public static HashtagAboMapper hashtagAboMapper() {
		if (hashtagAboMapper == null) {
			hashtagAboMapper = new HashtagAboMapper();
		}
		return hashtagAboMapper;
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

	public HashtagAbo findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, schlagwort FROM hashtagAbon" + "WHERE id=" + id + " ORDER BY id");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				HashtagAbo ha = new HashtagAbo();
				ha.setID(rs.getInt("id"));
				ha.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));

				return ha;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller HashtagsAbo.
	 * 
	 * @return Ein Vektor mit HashtagAbo-Objekten, die sämtliche HashtagAbon *
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<HashtagAbo> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<HashtagAbo> result = new Vector<HashtagAbo>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `hashtagAbo` ORDER BY `id`");

			// Für jeden Eintrag im Suchergebnis wird nun ein Hashtag-Objekt
			// erstellt.
			while (rs.next()) {
				HashtagAbo ha = new HashtagAbo();
				ha.setID(rs.getInt("id"));
				ha.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(ha);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines <code>HashtagAbo</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primärschlüssel des übergebenen Objekts geprüft und
	 * ggf. berichtigt.
	 * 
	 * @param ha
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public HashtagAbo insert(HashtagAbo ha) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM HashtagAbo ");
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * ha erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				ha.setID(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO HashtagAbo (id, Erstellungszeitpunkt) " + "VALUES ("
						+ ha.getID() + ", " + ha.getErstellungszeitpunkt() + ",  " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, der evtl. korrigierten HashtagAbonnement.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des
		 * HashtagAbonnement-Objekts auch ohne diese explizite Rückgabe
		 * außerhalb dieser Methode sichtbar. Die explizite Rückgabe von ha ist
		 * eher ein Stilmittel, um zu signalisieren, dass sich das Objekt evtl.
		 * im Laufe der Methode verändert hat.
		 */

		return ha;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param ha
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public HashtagAbo update(HashtagAbo ha) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE HashtagAbonnement " + "SET
			// schlagwort=\"" +
			// ha.gettext() + "\" " + "WHERE id=" + ha.getId());

			stmt.executeUpdate("UPDATE `hashtagAbon` SET `Erstellungszeitpunkt`='" + "' WHERE `id`= " + ha.getID() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(HashtagAbo ha) zu wahren, geben wir ha
		// zurück
		return ha;
	}

	
	/**
	 * Löschen der Daten eines <code>HashtagAbo</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param ha
	 *           das aus der DB zu löschende "Objekt"
	 */

	public void delete(HashtagAbo ha) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM hashtagAbo " + "WHERE id=" + ha.getID());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}