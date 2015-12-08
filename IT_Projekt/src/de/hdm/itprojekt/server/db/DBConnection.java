package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {

	private static Connection con = null;
	 
	/** URL muss noch eingef�gt werden
	   */
	private static String googleUrl = "url";
    private static String localUrl = "url";

    public static Connection connection() {
        if (con == null) {
            String url = null;
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                    url = googleUrl;
                } else {
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                }

                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }
        return con;
    }
}