package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

/**
 * Mapper-Klasse, die <code>Hashtag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 * 
 * 
 * @author Thies
 */

public class HashtagMapper {

	/**
	 * Die Klasse HashtagMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static HashtagMapper hashtagMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected HashtagMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>HashtagMapper.hashtagMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>HashtagMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> HashtagMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>HashtagMapper</code>-Objekt.
	 * @see hashtagnMapper
	 */

	public static HashtagMapper hashtagMapper() {
		if (hashtagMapper == null) {
			hashtagMapper = new HashtagMapper();
		}
		return hashtagMapper;
	}

	/**
	 * Suchen eines Nuters mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Hashtag-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public Hashtag findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT id, schlagwort FROM hashtag " + "WHERE id=" + id + " ORDER BY schlagwort");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("id"));
				h.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));
				h.setSchlagwort(rs.getString("schlagwort"));

				return h;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Hashtags.
	 * 
	 * @return Ein Vektor mit Hashtag-Objekten, die sämtliche Nachrichten
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<Hashtag> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<Hashtag> result = new Vector<Hashtag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `hashtag` ORDER BY `id`");

			// Für jeden Eintrag im Suchergebnis wird nun ein Hashtag-Objekt
			// erstellt.
			while (rs.next()) {
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("id"));
				h.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));
				h.setschlagwort(rs.getString("schlagwort"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(na);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines <code>Hashtag</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Hashtag insert(Hashtag h) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Nachricht ");
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * na erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				h.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO Hashtag (id, Erstellungszeitpunkt, schlagwort) " + "VALUES (" + h.getId()
						+ ", " + h.Erstellungszeitpunkt() + ", " + h.schlagwort() + ", " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, der evtl. korrigierten Nachricht.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des Nachricht-Objekts
		 * auch ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar.
		 * Die explizite Rückgabe von n ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */

		return h;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Hashtag update(Hashtag h) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE Hashtag " + "SET schlagwort=\"" +
			// n.gettext() + "\" " + "WHERE id=" + n.getId());

			stmt.executeUpdate("UPDATE `hashtag` SET `schlagwort`='" + h.getSchlagwort() + "',`Erstellungszeitpunkt`='"
					+ "' WHERE `id`= " + h.getId() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(Hashtag h) zu wahren, geben wir h zurück
		return h;
	}

	/**
	 * Löschen der Daten eines <code>Hashtag</code>-Objekts aus der Datenbank.
	 * 
	 * @param h
	 *            das aus der DB zu löschende "Objekt"
	 */

	public void delete(Hashtag h) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM hashtag " + "WHERE id=" + h.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}