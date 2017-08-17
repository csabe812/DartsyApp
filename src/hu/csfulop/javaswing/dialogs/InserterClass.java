package hu.csfulop.javaswing.dialogs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserterClass {
 
    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
	
	public InserterClass(String name) {
		insert(name);
	}
	
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:d:\\Programming\\eclipse_projects\\Dartsy\\src\\resources\\dartsy_db.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insert(String name) {
        String sql = "INSERT INTO users(name) VALUES(?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
}