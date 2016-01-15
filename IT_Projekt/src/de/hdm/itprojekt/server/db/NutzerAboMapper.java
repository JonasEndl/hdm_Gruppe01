package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.NutzerAbo;
import de.hdm.itprojekt.shared.bo.Nutzer;
import de.hdm.itprojekt.shared.bo.Abonnement;




public class NutzerAboMapper {

	/**
	 * Die Klasse NutzerAbonnementMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 */

	private static NutzerAboMapper nutzerAboMapper = null;

	/**
	 * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit new neue
	 * Instanzen dieser Klasse zu erzeugen.
	 * 
	 */

	protected NutzerAboMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>NutzerAbonnementMapper.nutzerAbonnementMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
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
	 * genau ein Objekt zur�ckgegeben.
	 * 
	 * @param id
	 *            Prim�rschl�sselattribut (->DB)
	 * @return Hashtag-Objekt, das dem �bergebenen Schl�ssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public NutzerAbo findById(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(
					"SELECT NutzerAboID FROM NutzerAbo " + "WHERE NutzerAboID=" + id + " ORDER BY NutzerAboID");

			/*
			 * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				NutzerAbo nab = new NutzerAbo();
				nab.setID(rs.getInt("NutzerAboID"));
				nab.setErstellungszeitpunkt(rs.getString("Erstellungszeitpunkt"));
				

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
	 * @return Ein Vektor mit NutzerAbonnement-Objekten, die s�mtliche NutzerAbonnements
	 *         repr�sentieren. Bei evtl. Exceptions wird ein partiell gef�llter
	 *         oder ggf. auch leerer Vetor zur�ckgeliefert.
	 */

	public Vector<NutzerAbo> findAll() {
		Connection con = DBConnection.connection();
		// Ergebnisvektor vorbereiten
		Vector<NutzerAbo> result = new Vector<NutzerAbo>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `NutzerAbo` ORDER BY `NutzerAboID`");

			// F�r jeden Eintrag im Suchergebnis wird nun ein NutzerAbo-Objekt
			// erstellt.
			while (rs.next()) {
				NutzerAbo nab = new NutzerAbo();
				nab.setID(rs.getInt("NutzerAboID"));
				nab.setErstellungszeitpunkt(rs.getString("Erstellungszeitpunkt"));


				// Hinzuf�gen des neuen Objekts zum Ergebnisvektor
				result.addElement(nab);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zur�ckgeben
		return result;
	}

	/**
	 * Einf�gen eines <code>NutzerAbo</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und ggf.
	 * berichtigt.
	 * 
	 * @param nab
	 *            das zu speichernde Objekt
	 * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */

	public NutzerAbo insert(NutzerAbo nab) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(NutzerAboID) AS maxid " + "FROM NutzerAbo ");
			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * nab erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				nab.setID(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				// Jetzt erst erfolgt die tats�chliche Einf�geoperation
				stmt.executeUpdate("INSERT INTO NutzerAbo (NutzerAboID, Erstellungszeitpunkt) " + "VALUES (" + nab.getID()
						+ ", " + nab.getErstellungszeitpunkt() + ", " + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/*
		 * R�ckgabe, der evtl. korrigierten NutzerAbonnement.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des NutzerAbonnement-Objekts
		 * auch ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar.
		 * Die explizite R�ckgabe von nab ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * ver�ndert hat.
		 */

		return nab;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param nab
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter �bergebene Objekt
	 */

	public NutzerAbo update(NutzerAbo nab) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// stmt.executeUpdate("UPDATE HashtagAbonnement " + "SET schlagwort=\"" +
			// ha.gettext() + "\" " + "WHERE id=" + ha.getId());

			stmt.executeUpdate("UPDATE `NutzerAbo` SET `Erstellungszeitpunkt`='"
					+ "' WHERE `NutzerAboID`= " + nab.getID() + ";");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Um Analogie zu insert(NutzerAbonnement nab) zu wahren, geben wir nab zur�ck
		return nab;
	}

	/**
	 * L�schen der Daten eines <code>NutzerAbo</code>-Objekts aus der Datenbank.
	 * 
	 * @param nab
	 *           das aus der DB zu l�schende "Objekt"
	 */

	public void delete(NutzerAbo nab) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM NutzerAbo" + "WHERE NutzerAboID=" + nab.getID());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}