package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DatabaseConnection;

public class CompDAO {
//	public void Cartres() {
//		
//		try (Connection connection = DatabaseConnection.getConnection()) {
//			String del = "ALTER SEQUENCE CART_ITEMID_SEQ RESTART WITH 1";
//			PreparedStatement pstmt = connection.prepareStatement(del);
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("データベース発見できず");
//		}
//	}
//	public void Cartdel() {
//
//		try (Connection connection = DatabaseConnection.getConnection()) {
//			String res = "DELETE FROM CART";
//			PreparedStatement pStmt = connection.prepareStatement(res);
//			pStmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("データベース発見できず");
//		}
//	}
	
	public void Cartdel(String usersId) {

		try (Connection connection = DatabaseConnection.getConnection()) {
			String res = "DELETE FROM CART WHERE LOGINID=?";
			PreparedStatement pStmt = connection.prepareStatement(res);
			pStmt.setString(1, usersId);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("データベース発見できず");
		}
	}
	
	public void zaikoBuy() {
		
	}
}
