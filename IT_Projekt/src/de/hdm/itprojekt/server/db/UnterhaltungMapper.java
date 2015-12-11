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

public class UnterhaltungMapper {

	/**
	 * Die Klasse UnterhaltungMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static UnterhaltungMapper unterhaltungMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected UnterhaltungMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>UnterhaltungMapper.unterhaltungMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>UnterhaltungMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> UnterhaltungMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>UnterhaltungMapper</code>-Objekt.
	 * @see hashtagnMapper
	 */

	public static UnterhaltungMapper unterhaltungMapper() {
		if (unterhaltungMapper == null) {
			unterhaltungMapper = new UnterhaltungMapper();
		}
		return unterhaltungMapper;
	}

	/**
	 * Suchen eines Nuters mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Unterhaltung-Objekt, das dem übergebenen Schlüssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public Unterhaltung findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT id, FROM unterhaltung " + "WHERE id=" + id + " ORDER BY id");

			/*
			 * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
			 * werden. Prüfe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("id"));
				u.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Unterhaltungen.
	 * 
	 * @return Ein Vektor mit Unterhaltung-Objekten, die sämtliche Unterhaltung
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */

	public Vector<Unterhaltung> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<Unterhaltung> result = new Vector<Unterhaltung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `unterhaltung` ORDER BY `id`");

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// Unterhaltung-Objekt
			// erstellt.
			while (rs.next()) {
				Unterhaltung u = new Unterhaltung();
				u.setId(rs.getInt("id"));
				u.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(u);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Einfügen eines <code>Unterhaltung</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Unterhaltung insert(Unterhaltung u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zunächst schauen wir nach, welches der momentan höchste
			 * Primärschlüsselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Unterhaltung ");
			// Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * na erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				u.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				stmt.executeUpdate("INSERT INTO Unterhaltung (id, Erstellungszeitpunkt) " + "VALUES (" + u.getId()
						+ ", " + u.Erstellungszeitpunkt() + ", " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * Rückgabe, der evtl. korrigierten Unterhaltung.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte übergeben werden, wäre die Anpassung des Unterhaltung-Objekts
		 * auch ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar.
		 * Die explizite Rückgabe von h ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verändert hat.
		 */

		return u;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param u
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Unterhaltung update(Unterhaltung u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE `unterhaltung` SET `Erstellungszeitpunkt`='" + u.getErstellungszeitpunkt()
					+ "' WHERE `id`= " + u.getId() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(Hashtag h) zu wahren, geben wir u zurück
		return u;
	}

	/**
	 * Löschen der Daten eines <code>Unterhaltung</code>-Objekts aus der Datenbank.
	 * 
	 * @param u
	 *            das aus der DB zu löschende "Objekt"
	 */

	public void delete(Unterhaltung u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM unterhaltung " + "WHERE id=" + u.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}