package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DatabaseConnection;

public class DeleteCartDAO {
	
	
//	public void DeleteCart(String name) {
//
//		try (Connection connection = DatabaseConnection.getConnection()) {
//			String del = "DELETE FROM CART WHERE ITEMNAME = ?";
//			PreparedStatement pstmt = connection.prepareStatement(del);
//			pstmt.setString(1, name);
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}

	
	public void DeleteCart(String name, String usersId) {

		try (Connection connection = DatabaseConnection.getConnection()) {
			
			
			String del = "DELETE FROM CART WHERE ITEMNAME = ? AND LOGINID=?";
			PreparedStatement pstmt = connection.prepareStatement(del);
			pstmt.setString(1, name);
			pstmt.setString(2, usersId);
			pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
