package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DatabaseConnection;

public class kanriKeyDAO {
	public int keyGet(String id) {
		int key=0;
	try (Connection connection = 
			DatabaseConnection.getConnection()) {
		String sql = 
		"SELECT USERSKEY FROM USERS WHERE LOGINID = ? ORDER BY LOGINID;";
		PreparedStatement pStmt = connection.prepareStatement(sql);
		pStmt.setString(1, id);
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			key = rs.getInt("USERSKEY");
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return key;
	}
}
