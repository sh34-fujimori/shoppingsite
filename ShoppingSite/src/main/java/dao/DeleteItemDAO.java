package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DatabaseConnection;

public class DeleteItemDAO {
	public void DeleteItem(int id) {

		try (Connection connection = DatabaseConnection.getConnection()) {
			String del = "DELETE FROM ITEM WHERE ITEMID = ?";
			PreparedStatement pstmt = connection.prepareStatement(del);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
