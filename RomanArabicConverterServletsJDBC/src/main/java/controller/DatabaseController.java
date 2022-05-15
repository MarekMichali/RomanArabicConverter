package controller;

import java.sql.*;
import java.util.ArrayList;

/**
 * DatabaseController class
 *
 * @author Marek
 * @version 1.0
 */
public class DatabaseController {

    private Connection con;
    private ArrayList<String> history = new ArrayList<String>();

    /**
     * Constructor of the database controller
     *
     * @throws SQLException
     */
    public DatabaseController() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        con = DriverManager.getConnection("jdbc:derby://localhost:1527/romanarabic;create=true");
        DatabaseMetaData databaseMetadata = con.getMetaData();
        ResultSet beforeConversion = databaseMetadata.getTables(null, null, "BEFORECONVERSION", null);
        ResultSet afterConversion = databaseMetadata.getTables(null, null, "AFTERCONVERSION", null);

        if (!beforeConversion.next()) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE BEFORECONVERSION ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                    + " Number VARCHAR(50) )");
        }
        if (!afterConversion.next()) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE AFTERCONVERSION ("
                    + "ID INTEGER REFERENCES BEFORECONVERSION(ID), "
                    + " Number VARCHAR(50) )");
        }

    }

    /**
     * Inserts data into the database
     *
     * @param originalNumber number before conversion
     * @param convertedNumber number after conversion
     * @throws SQLException
     */
    public void insertData(String originalNumber, String convertedNumber) throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO BEFORECONVERSION(Number) VALUES ('" + originalNumber + "')");
        statement.executeUpdate("INSERT INTO AFTERCONVERSION VALUES ((SELECT MAX(ID) FROM BEFORECONVERSION WHERE Number='" + originalNumber + "'),'" + convertedNumber + "')");

    }

    /**
     * Returns the database content
     *
     * @return database content
     * @throws SQLException
     */
    public ArrayList<String> displayData() throws SQLException {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT BEFORECONVERSION.Number, AFTERCONVERSION.Number FROM BEFORECONVERSION, AFTERCONVERSION WHERE BEFORECONVERSION.ID=AFTERCONVERSION.ID");
        history.clear();
        while (rs.next()) {
            history.add(rs.getString(1));
            history.add(rs.getString(2));
        }
        rs.close();
        return history;
    }
}
