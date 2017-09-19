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
		Connection conn = null;
		try {
			conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void selectAll(JComboBox<String> jcb) {
		Connection conn = null;
		jcb.removeAllItems();
		try {
			conn = connect();
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
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static String selectRemainingScore(int userId) {
		Connection conn = null;
		String selectScore = "select 501-sum(score) as score from throws, users where throws.userid = users.id and throws.matchid = (SELECT matches.id  FROM matches ORDER BY Id DESC LIMIT 1) and throws.userid = " + userId + " group by userid";
		try {
			conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectScore);

			// loop through the result set
			while (rs.next()) {
				return String.valueOf(rs.getInt("score"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}

	public static void insert(String name) {
		Connection conn = null;
		if (!checkUserExists(name)) {
			try {
				conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(DataClass.insertName);
				pstmt.setString(1, name);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			JFrame jf = new JFrame();
			JOptionPane.showMessageDialog(jf, DataClass.userExists, DataClass.cannotAdd, JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void insertNewMatch(int playerOneId, int playerTwoId) {
		Connection conn = null;
		String insertMatch = "INSERT INTO matches(playerone, playertwo) VALUES (" + playerOneId + ", " + playerTwoId
				+ ")";
		try {
			conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(insertMatch);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void insertNewThrow(int userid, int score) {
		Connection conn = null;
		String insertThrow = "INSERT INTO throws (matchid, userid, score) select (SELECT matches.id  FROM matches ORDER BY Id DESC LIMIT 1), " + userid + ", " + score;
		System.out.println(insertThrow);
		try {
			conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(insertThrow);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void selectThrows(MainWindow mw) {
		Connection conn = null;
		try {
			conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DataClass.selectThrows);
			DefaultTableModel dtm = buildTableModel(rs);
			mw.getJt().setModel(dtm);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public static boolean hasPlayerWon(int id) {
		Connection conn = null;
		String sql = "select sum(score) from throws where matchid = (SELECT matches.id  FROM matches ORDER BY Id DESC LIMIT 1) group by userid";
		try {
			conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			while (rs.next()) {
				if(rs.getInt("sum(score)") == 501) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}