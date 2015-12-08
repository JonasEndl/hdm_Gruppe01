package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

// Kommentare müssen noch hinzugefügt werden
public class NutzerMapper {
	private static NutzerMapper nutzerMapper = null;

	protected NutzerMapper() {
}
public static NutzerMapper nutzerMapper(){
	if (nutzerMapper == null){
		nutzerMapper = new NutzerMapper();
	}
	return nutzerMapper;
}
public Nutzer findByKey(int id) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM nutzer "
          + "WHERE id=" + id + " ORDER BY owner");
      if (rs.next()) {
        Nutzer a = new Nutzer();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));
        return a;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }
public Vector<Nutzer> findAll() {
    Connection con = DBConnection.connection();

    Vector<Nutzer> result = new Vector<Nutzer>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM nutzer "
          + " ORDER BY id");

      while (rs.next()) {
        Nutzer a = new Nutzer();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));

        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
    return result;
  }
public Nutzer insert(Nutzer n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM nutzer ");

      if (rs.next()) {

        n.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        stmt.executeUpdate("INSERT INTO accounts (id, owner) " + "VALUES ("
            + n.getId() + "," + n.getOwnerID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return n;
  }
public Nutzer update(Nutzer n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE nutzer " + "SET owner=\"" + n.getOwnerID()
          + "\" " + "WHERE id=" + n.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return n;
  }

public void delete(Nutzer n) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM nutzer " + "WHERE id=" + n.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }
public Vector<Nachricht> getNachrichtOfNutzer(int NutzerID) {
    Connection con = DBConnection.connection();
    Vector<Nachricht> result = new Vector<Nachricht>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM nachricht "
          + "WHERE nutzer=" + nutzerID + " ORDER BY id");

 
      while (rs.next()) {
        Nutzer a = new Nutzer();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("nutzer"));

      
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }


    return result;
  }
}