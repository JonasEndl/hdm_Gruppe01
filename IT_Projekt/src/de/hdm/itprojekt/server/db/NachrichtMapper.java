package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;



public class NachrichtenMapper {

	/**
	 * Die Klasse NachrichtenMapper wird nur einmal instantiiert.
	 */

	private static NachrichtenMapper nachrichtMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected NachrichtenMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NachrichtenMapper.NachrichtMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>NachrichtenMapper</code> existiert.
	 * <p>
	 */

	public static NachrichtenMapper nachrichtMapper() {
		if (nachrichtMapper == null) {
			nachrichtMapper = new NachrichtenMapper();
		}
		return nachrichtMapper;
	}

	/**
	 * Suchen eines Nutzers mit vorgegebener ID. 
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Nachricht-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public Nachricht findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT id, vorName, nachnameName, eMail FROM nutzer " + "WHERE id=" + id + " ORDER BY nachName");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Nutzer na = new Nutzer();
				na.setId(rs.getInt("id"));
				na.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));
				na.setText(rs.getString("text"));

				return na;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Nachrichten.
	 * 
	 * @return Ein Vektor mit Nachrichten-Objekten, die sämtliche Nachrichten
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<Nachricht> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<Nachricht> result = new Vector<Nachricht>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `nachricht` ORDER BY `id`");

			// Für jeden Eintrag im Suchergebnis wird nun ein Nachricht-Objekt
			// erstellt.
			while (rs.next()) {
				Nachricht na = new Nachricht();
				na.setId(rs.getInt("id"));
				na.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));
				na.setText(rs.getString("text"));

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
	 * Einfügen eines <code>Nachricht</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Nachricht insert(Nachricht na) {
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
				na.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO Nachricht (id, Erstellungszeitpunkt, text) " + "VALUES (" + na.getId()
						+ ", " + na.Erstellungszeitpunkt() + ", " + na.text() + ", " + ")");
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

		return na;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Nachricht update(Nachricht na) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE Nachricht " + "SET text=\"" +
			// n.gettext() + "\" " + "WHERE id=" + n.getId());

			stmt.executeUpdate("UPDATE `nachricht` SET `text`='" + na.getvorname() + "',`Erstellungszeitpunkt`='"
					+ "' WHERE `id`= " + na.getId() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(Nachricht na) zu wahren, geben wir na zurück
		return na;
	}

	/**
	 * Löschen der Daten eines <code>Nachricht</code>-Objekts aus der Datenbank.
	 * 
	 * @param na
	 *            das aus der DB zu löschende "Objekt"
	 */

	public void delete(Nachricht na) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM nachricht " + "WHERE id=" + na.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}