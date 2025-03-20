package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DatabaseConnection;

public class KosuChangeDAO {
	public void Changekosu(String name, int kosu) {

		try (Connection connection = DatabaseConnection.getConnection()) {
			String up = "UPDATE CART SET ITEMKOSU = ? WHERE ITEMNAME = ?";
			PreparedStatement pstmt = connection.prepareStatement(up);
			pstmt.setInt(1, kosu);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("データベース発見できず");
		}
	}
}
