package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

/**
 * Mapper-Klasse, die <code>HashtagAbonnement</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * <p>
 * 
 * 
 * @author Thies
 */

public class HashtagAbonnementMapper {

	/**
	 * Die Klasse HashtagAbonnementMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static HashtagAbonnementMapper hashtagAbonnementMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected HashtagAbonnementMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>HashtagAbonnementMapper.hashtagAbonnementMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>HashtagAbonnementMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> HashtagAbonnementMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>HashtagAbonnementMapper</code>-Objekt.
	 * @see hashtagAbonnementMapper
	 */

	public static HashtagAbonnementMapper hashtagAbonnementMapper() {
		if (hashtagAbonnementMapper == null) {
			hashtagAbonnementMapper = new HashtagAbonnementMapper();
		}
		return hashtagAbonnementMapper;
	}

	/**
	 * Suchen eines HashtagAbonnement mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Prim�rschl�sselattribut (->DB)
	 * @return Hashtag-Objekt, das dem �bergebenen Schl�ssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public HashtagAbonnement findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT id, schlagwort FROM hashtagAbonnement " + "WHERE id=" + id + " ORDER BY id");

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				HashtagAbonnement ha = new HashtagAbonnement();
				ha.setId(rs.getInt("id"));
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
	 * Auslesen aller HashtagsAbonnements.
	 * 
	 * @return Ein Vektor mit HashtagAbonnement-Objekten, die s�mtliche HashtagAbonnements
	 *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zur�ckgeliefert.
	 */

	public Vector<HashtagAbonnement> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<HashtagAbonnement> result = new Vector<HashtagAbonnement>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `hashtagAbonnement` ORDER BY `id`");

			// F�r jeden Eintrag im Suchergebnis wird nun ein Hashtag-Objekt
			// erstellt.
			while (rs.next()) {
				HashtagAbonnement ha = new HashtagAbonnement();
				ha.setId(rs.getInt("id"));
				ha.setErstellungszeitpunkt(rs.getString("erstellungszeitpunkt"));


				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(ha);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

	/**
	 * Einf�gen eines <code>HashtagAbonnement</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtigt.
	 * 
	 * @param c
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public HashtagAbonnement insert(HashtagAbonnement ha) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM HashtagAbonnement ");
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * ha erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				ha.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tats�chliche Einf�geoperation
				stmt.executeUpdate("INSERT INTO HashtagAbonnement (id, Erstellungszeitpunkt, schlagwort) " + "VALUES (" + ha.getId()
						+ ", " + ha.Erstellungszeitpunkt() + ", " + ha.schlagwort() + ", " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * R�ckgabe, der evtl. korrigierten HashtagAbonnement.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des HashtagAbonnement-Objekts
		 * auch ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar.
		 * Die explizite R�ckgabe von ha ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * ver�ndert hat.
		 */

		return ha;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param ha
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 */

	public HashtagAbonnement update(HashtagAbonnement ha) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE HashtagAbonnement " + "SET schlagwort=\"" +
			// ha.gettext() + "\" " + "WHERE id=" + ha.getId());

			stmt.executeUpdate("UPDATE `hashtagAbonnement` SET `schlagwort`='" + ha.getSchlagwort() + "',`Erstellungszeitpunkt`='"
					+ "' WHERE `id`= " + ha.getId() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(HashtagAbonnement ha) zu wahren, geben wir ha zur�ck
		return ha;
	}

	/**
	 * L�schen der Daten eines <code>HashtagAbonnement</code>-Objekts aus der Datenbank.
	 * 
	 * @param ha
	 *            das aus der DB zu l�schende "Objekt"
	 */

	public void delete(HashtagAbonnement ha) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM hashtagAbonnement " + "WHERE id=" + ha.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}