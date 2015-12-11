package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

/**
 * Mapper-Klasse, die <code>Hashtag</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
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
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static HashtagMapper hashtagMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected HashtagMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>HashtagMapper.hashtagMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
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
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Prim�rschl�sselattribut (->DB)
	 * @return Hashtag-Objekt, das dem �bergebenen Schl�ssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public Hashtag findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT id, schlagwort FROM hashtag " + "WHERE id=" + id + " ORDER BY schlagwort");

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
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
	 * @return Ein Vektor mit Hashtag-Objekten, die s�mtliche Nachrichten
	 *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zur�ckgeliefert.
	 */

	public Vector<Hashtag> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<Hashtag> result = new Vector<Hashtag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `hashtag` ORDER BY `id`");

			// F�r jeden Eintrag im Suchergebnis wird nun ein Hashtag-Objekt
			// erstellt.
			while (rs.next()) {
				Hashtag h = new Hashtag();
				h.setId(rs.getInt("id"));
				h.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));
				h.setschlagwort(rs.getString("schlagwort"));

				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(na);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

	/**
	 * Einf�gen eines <code>Hashtag</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public Hashtag insert(Hashtag h) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Nachricht ");
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * na erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				h.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tats�chliche Einf�geoperation
				stmt.executeUpdate("INSERT INTO Hashtag (id, Erstellungszeitpunkt, schlagwort) " + "VALUES (" + h.getId()
						+ ", " + h.Erstellungszeitpunkt() + ", " + h.schlagwort() + ", " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * R�ckgabe, der evtl. korrigierten Nachricht.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des Nachricht-Objekts
		 * auch ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar.
		 * Die explizite R�ckgabe von n ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * ver�ndert hat.
		 */

		return h;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param n
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
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
		// Um Analogie zu insert(Hashtag h) zu wahren, geben wir h zur�ck
		return h;
	}

	/**
	 * L�schen der Daten eines <code>Hashtag</code>-Objekts aus der Datenbank.
	 * 
	 * @param h
	 *            das aus der DB zu l�schende "Objekt"
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