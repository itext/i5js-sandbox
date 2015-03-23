/*
 * This example was written by Bruno Lowagie
 */
package zugferd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A simple example to test the database
 */
public class DatabaseTest {
    public static void main(String[] args) throws SQLException {
        DatabaseTest app = new DatabaseTest();
        Connection connection = app.createConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM Product");
        while (rs.next()) {
            System.out.println(rs.getString("Name"));
        }
        stm.close();
        connection.close();
    }
    
    public Connection createConnection() throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("HSQLDB database driver not found");
        }
        return DriverManager.getConnection(
            "jdbc:hsqldb:resources/zugferd/db/invoices", "SA", "");
    }
}
