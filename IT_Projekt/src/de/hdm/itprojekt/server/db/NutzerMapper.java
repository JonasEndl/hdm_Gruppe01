package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

/**
 * Mapper-Klasse, die <code>Nutzer</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
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
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static NutzerMapper nutzerMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected NutzerMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerMapper.nutzerMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
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
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Prim�rschl�sselattribut (->DB)
	 * @return Nutzer-Objekt, das dem �bergebenen Schl�ssel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 */

	public Nutzer findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT id, vorName, nachnameName, eMail FROM nutzer " + "WHERE id=" + id + " ORDER BY nachName");

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
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
	 * @return Ein Vektor mit Nutzer-Objekten, die s�mtliche Nutzer
	 *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zur�ckgeliefert.
	 */

	public Vector<Nutzer> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<Nutzer> result = new Vector<Nutzer>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `nutzer` ORDER BY `name`");

			// F�r jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt
			// erstellt.
			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setVorName(rs.getString("vorName"));
				n.setNachName(rs.getString("nachName"));
				n.setEMail(rs.getString("EMail"));

				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(n);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

	/**
	 * Einf�gen eines <code>Nutzer</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Nutzer insert(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Nutzer ");
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * c erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				n.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tats�chliche Einf�geoperation
				stmt.executeUpdate("INSERT INTO Nutzer (id, vorname, nachname, email) " + "VALUES (" + n.getId() + ", "
						+ n.getvorname() + ", " + n.getnachname() + ", " + n.getemail() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * R�ckgabe, des evtl. korrigierten Nutzers.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des Nutzer-Objekts auch
		 * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
		 * explizite R�ckgabe von n ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * ver�ndert hat.
		 */

		return n;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
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
		// Um Analogie zu insert(Nutzer n) zu wahren, geben wir n zur�ck
		return n;
	}

	/**
	 * L�schen der Daten eines <code>Nutzer</code>-Objekts aus der Datenbank.
	 * 
	 * @param n
	 *            das aus der DB zu l�schende "Objekt"
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
	 * Auslesen der zugeh�rigen <code>Nachrichten</code>-Objekte zu einem
	 * gegebenen Nutzer.
	 * 
	 * @param n
	 *            der Nutzer, dessen Nachrichten wir auslesen m�chten
	 * @return ein Vektor mit s�mtlichen Nachrichten-Objekten des Nutzers
	 */
	public Vector<Nachricht> getNachrichtenOf(Nutzer n) {
		/*
		 * Wir bedienen uns hier einfach des NachrichtenMapper. Diesem geben wir
		 * einfach den in dem Nutzer-Objekt enthaltenen Prim�rschl�ssel.Der
		 * CustomerMapper l�st uns dann diese ID in eine Reihe von
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