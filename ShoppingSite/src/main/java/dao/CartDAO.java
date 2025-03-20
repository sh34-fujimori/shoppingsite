package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DatabaseConnection;

public class CartDAO {

//	public void CartAdd(String name, int price, int zaiko, int kosu,int id,String pas) {
//
//		
//		try (Connection connection = DatabaseConnection.getConnection()) {
//
//			String up = "UPDATE CART SET ITEMKOSU = ITEMKOSU + ? WHERE ITEMNAME = ?";
//			String in = "INSERT INTO CART (ITEMNAME, ITEMPRICE, ITEMZAIKO, ITEMKOSU,ITEMNO,ITEMPAS) VALUES (?, ?, ?, ?,?,?)";
//			PreparedStatement pstmt = connection.prepareStatement(up);
//			PreparedStatement pStmt = connection.prepareStatement(in);
//
//			pstmt.setInt(1, kosu);
//			pstmt.setString(2, name);
//			int han = pstmt.executeUpdate();
//			if (han == 0) {
//				pStmt.setString(1, name);
//				pStmt.setInt(2, price);
//				pStmt.setInt(3, zaiko);
//				pStmt.setInt(4, kosu);
//				pStmt.setInt(5, id);
//				pStmt.setString(6, pas);
//
//				pStmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("データベース発見できず");
//		}
//	}
	
	public void CartAdd(String name, int price, int zaiko, 
			int kosu,int id, String pas, String usersId) {

		
		try (Connection connection = DatabaseConnection.getConnection()) {

			String up = "UPDATE CART SET ITEMKOSU = ITEMKOSU + ? WHERE ITEMNAME = ? AND LOGINID = ?";
			String in = "INSERT INTO CART (ITEMNAME, ITEMPRICE, ITEMZAIKO, ITEMKOSU,ITEMNO,ITEMPAS, LOGINID) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(up);
			PreparedStatement pStmt = connection.prepareStatement(in);

			pstmt.setInt(1, kosu);
			pstmt.setString(2, name);
			pstmt.setString(3, usersId);
			int han = pstmt.executeUpdate();
			if (han == 0) {
				pStmt.setString(1, name);
				pStmt.setInt(2, price);
				pStmt.setInt(3, zaiko);
				pStmt.setInt(4, kosu);
				pStmt.setInt(5, id);
				pStmt.setString(6, pas);
				pStmt.setString(7, usersId);
				pStmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("データベース発見できず");
		}
	}
	
	public int getCartKosu(String usersId) {
		
		int kosuSum = 0;
		try (Connection connection = DatabaseConnection.getConnection()) {
	        String sql = "SELECT SUM(ITEMKOSU) AS TOTALKOSU FROM CART WHERE LOGINID = ?";
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, usersId);
	        ResultSet rs = stmt.executeQuery();
	        	
	            while (rs.next()) {
	                kosuSum = rs.getInt("TOTALKOSU");
	     
	            }
	        
	        return kosuSum;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("データベース発見できず");
			return 0;
		}
	}
}
