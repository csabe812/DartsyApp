package hu.csfulop.javaswing.dialogs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import hu.csfulop.javaswing.MainWindow;
import hu.csfulop.javaswing.config.DataClass;

public class QueryClass {

	/**
	 * Connect to the test.db database
	 *
	 * @return the Connection object
	 */

	private static Connection connect() {
		// SQLite connection string
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DataClass.jdbcUrl);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static boolean checkUserExists(String name) {
		String sql = "SELECT name FROM users where name = '" + name + "'";
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static void selectAll(JComboBox<String> jcb) {
		jcb.removeAllItems();
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataClass.selectName);

			// loop through the result set
			int counter = 0;
			while (rs.next()) {
				jcb.insertItemAt(rs.getString("name"), counter);
				counter++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String selectRemainingScore(int userId) {
		String selectScore = "select 501-sum(score) as score from throws, users where throws.userid = users.id and throws.matchid = (SELECT matches.id  FROM matches ORDER BY Id DESC LIMIT 1) and throws.userid = " + userId + " group by userid";
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectScore);

			// loop through the result set
			while (rs.next()) {
				return String.valueOf(rs.getInt("score"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public static void insert(String name) {
		if (!checkUserExists(name)) {
			try {
				Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(DataClass.insertName);
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			JFrame jf = new JFrame();
			JOptionPane.showMessageDialog(jf, DataClass.userExists, DataClass.cannotAdd, JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void insertNewMatch(int playerOneId, int playerTwoId) {
		String insertMatch = "INSERT INTO matches(playerone, playertwo) VALUES (" + playerOneId + ", " + playerTwoId
				+ ")";
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(insertMatch);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertNewThrow(int userid, int score) {
		String insertThrow = "INSERT INTO throws (matchid, userid, score) select (SELECT matches.id  FROM matches ORDER BY Id DESC LIMIT 1), " + userid + ", " + score;
		System.out.println(insertThrow);
		try {
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(insertThrow);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void selectThrows(MainWindow mw) {
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataClass.selectThrows);
			DefaultTableModel dtm = buildTableModel(rs);
			mw.getJt().setModel(dtm);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}
}