package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

/**
 * Mapper-Klasse, die <code>Nutzer</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 *
 * 
 * @author Thies
 */

public class NutzerMapper {

	/**
	 * Die Klasse Nutzermapper wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static NutzerMapper nutzerMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected NutzerMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerMapper.nutzerMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>NutzerMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> NutzerMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>NutzerMapper</code>-Objekt.
	 * @see nutzerMapper
	 */

	public static NutzerMapper nutzerMapper() {
		if (nutzerMapper == null) {
			nutzerMapper = new NutzerMapper();
		}
		return nutzerMapper;
	}

	/**
	 * Suchen eines Nuters mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Nutzer-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 */

	public Nutzer findById(int id) {
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
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setFirstName(rs.getString("vorName"));
				n.setLastName(rs.getString("nachName"));

				return n;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Nutzer.
	 * 
	 * @return Ein Vektor mit Nutzer-Objekten, die sämtliche Nutzer
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<Nutzer> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<Nutzer> result = new Vector<Nutzer>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` ORDER BY `name`");

			// Für jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt
			// erstellt.
			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setVorName(rs.getString("vorName"));
				n.setNachName(rs.getString("nachName"));
				n.setEMail(rs.getString("EMail"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(n);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Nutzer insert(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Nutzer ");
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * c erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				n.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO Nutzer (id, vorname, nachname, email) " + "VALUES (" + n.getId() + ", "
						+ n.getvorname() + ", " + n.getnachname() + ", " + n.getemail() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, des evtl. korrigierten Nutzers.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des Nutzer-Objekts auch
		 * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
		 * explizite Rückgabe von n ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */

		return n;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Nutzer update(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE nutzer " + "SET vorname=\"" +
			// n.getvorname() + "\" " + "WHERE id=" + n.getId());

			stmt.executeUpdate("UPDATE `nutzer` SET `name`='" + n.getvorname() + "',`nachname`='" + n.getnachname()
					+ "',`email`='" + n.getemail() + "' WHERE `id`= " + n.getId() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(Nutzer n) zu wahren, geben wir n zurück
		return n;
	}

	/**
	 * Löschen der Daten eines <code>Nutzer</code>-Objekts aus der Datenbank.
	 * 
	 * @param n
	 *            das aus der DB zu löschende "Objekt"
	 */

	public void delete(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM nutzer " + "WHERE id=" + n.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Auslesen der zugehörigen <code>Nachrichten</code>-Objekte zu einem
	 * gegebenen Nutzer.
	 * 
	 * @param n
	 *            der Nutzer, dessen Nachrichten wir auslesen möchten
	 * @return ein Vektor mit sömtlichen Nachrichten-Objekten des Nutzers
	 */
	public Vector<Nachricht> getNachrichtenOf(Nutzer n) {
		/*
		 * Wir bedienen uns hier einfach des NachrichtenMapper. Diesem geben wir
		 * einfach den in dem Nutzer-Objekt enthaltenen Primärschlüssel.Der
		 * CustomerMapper löst uns dann diese ID in eine Reihe von
		 * Nachrichten-Objekten auf.
		 */
		return NachrichtMapper.nachrichtMapper().findById(n);
	}

	public Vector<Unterhaltung> getUnterhaltungenOf(Nutzer n) {
		return UnterhaltungMapper.unterhaltungMapper().findById(n);
	}

	public Vector<Hashtag> getHashtagsOf(Nutzer n) {
		return HashtagMapper.hashtagMapper().findById(n);
	}

}