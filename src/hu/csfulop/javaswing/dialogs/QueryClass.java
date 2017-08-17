package hu.csfulop.javaswing.dialogs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class QueryClass {
 
    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
	
    private static Connection connect() {
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
    
    public static boolean checkUserExists(String name) {
    	String sql = "SELECT name FROM users where name = '" + name + "'";
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            if(rs.next()) {
            	return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public static void selectAll(JComboBox<String> jcb){
        String sql = "SELECT name FROM users";
        jcb.removeAllItems();
        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
        	int counter = 0;
            while (rs.next()) {
            	jcb.insertItemAt(rs.getString("name"), counter);
            	counter ++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insert(String name) {
    	if(!checkUserExists(name)) {
    		String sql = "INSERT INTO users(name) VALUES(?)";
    		 
            try (Connection conn = connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    	} else {
    		JFrame jf = new JFrame();
    		JOptionPane.showMessageDialog(jf, "User already exists!", "Cannot add user", JOptionPane.WARNING_MESSAGE);
    	}
    }
 
}