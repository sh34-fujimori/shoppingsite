package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DatabaseConnection;
import model.Item;

public class ItemDAO {

	//データベースから商品を読み込む
	public List<Item> Itemyomi() {
		Item item = null;
		List<Item> list = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection()) {
			String sql = "SELECT ITEMID,ITEMNAME,ITEMPRICE,ITEMZAIKO,ITEMPAS FROM ITEM ORDER BY ITEMID";
			PreparedStatement pStmt = connection.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ITEMID");
				String name = rs.getString("ITEMNAME");
				int price = rs.getInt("ITEMPRICE");
				int zaiko = rs.getInt("ITEMZAIKO");
				String pas = rs.getString("ITEMPAS");
				
				item = new Item(id,name, price, zaiko,pas);
				list.add(item);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("データベース発見できず");
			return null;
		}
		return list;
	}
	
	
	 // データベースに商品を追加する処理
	public void ItemInsert(String itemName, int itemPrice, int itemZaiko, String itemPas) {
		
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO ITEM (ITEMNAME, ITEMPRICE, ITEMZAIKO, ITEMPAS) VALUES (?, ?, ?, ?)")) {
			stmt.setString(1, itemName);
			stmt.setInt(2, itemPrice);
			stmt.setInt(3, itemZaiko);
			stmt.setString(4, itemPas);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 商品の内容を更新する処理
	public boolean ItemUpdate(int itemId, String itemName, int itemPrice, int itemZaiko, String itemPas) {
		// データベースに接続
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement("UPDATE ITEM SET ITEMNAME = ?, ITEMPRICE = ?, ITEMZAIKO = ?, ITEMPAS = ?  WHERE ITEMID = ?")) {
	
			// INSERT文の「？」に使用する値を設定してSQL文を完成
			stmt.setString(1, itemName);
			stmt.setInt(2, itemPrice);
			stmt.setInt(3, itemZaiko);
			stmt.setString(4, itemPas);
			stmt.setInt(5, itemId);
			stmt.executeUpdate();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 商品の情報を取得する
	public Item getItem(int itemId) {
		Item item = null;
		try (Connection connection = DatabaseConnection.getConnection()) {
			String sql = "SELECT ITEMNAME,ITEMPRICE,ITEMZAIKO,ITEMPAS FROM ITEM WHERE ITEMID = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, itemId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				
				String name = rs.getString("ITEMNAME");
				int price = rs.getInt("ITEMPRICE");
				int zaiko = rs.getInt("ITEMZAIKO");
				String pas = rs.getString("ITEMPAS");
				item = new Item(itemId, name, price, zaiko, pas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("データベース発見できず");
			return null;
		}
		return item;
	}
	
	
}
