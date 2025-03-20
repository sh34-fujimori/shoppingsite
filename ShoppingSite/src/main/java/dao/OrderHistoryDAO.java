package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.CartListItem;
import model.DatabaseConnection;
import model.OrderHistoryInfo;

public class OrderHistoryDAO {
	
	// 注文履歴を注文詳細テーブルに登録する
	public boolean registerOrderDetail(int orderId, int itemId, int itemKosu, int itemPrice, String itemName, String itemPas, int sum) {
		try (Connection connection = DatabaseConnection.getConnection()) {
				
			String sql = "INSERT INTO ORDERDETAIL (ORDERID, ITEMID, ITEMKOSU, ITEMPRICE, ITEMNAME, ITEMPAS, SUM) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			    
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, itemId);
			preparedStatement.setInt(3, itemKosu); 
			preparedStatement.setInt(4, itemPrice);
			preparedStatement.setString(5, itemName); 
			preparedStatement.setString(6, itemPas);
			preparedStatement.setInt(7, sum); 
			preparedStatement.executeUpdate();

		     return true;
		            
		            
		 } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		 }
		}
	
	// 注文履歴を登録し、オーダーIDを返す
	public int registerOrderHistory(String loginId, String name, String postcode, String address, String tel, String mail, String pay, Timestamp date) {
		try (Connection connection = DatabaseConnection.getConnection()) {
				
			String sql = "INSERT INTO ORDERHISTORY (LOGINID, USERSNAME, USERSPOSTCODE, USERSADDRESS, USERSTEL, USERSMAIL, USERSPAY, DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			    
			preparedStatement.setString(1, loginId);
			preparedStatement.setString(2, name); 
			preparedStatement.setString(3, postcode); 
			preparedStatement.setString(4, address); 
			preparedStatement.setString(5, tel); 
			preparedStatement.setString(6, mail); 
			preparedStatement.setString(7, pay); 
			preparedStatement.setTimestamp(8, date); 
			
			preparedStatement.executeUpdate();
			int orderId = 0;
	        // orderIDを取得
	        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                orderId = generatedKeys.getInt(1);
	                
	            }
	            return orderId;
	        }
		            
		            
		 } catch (Exception e) {
		        e.printStackTrace();
		        return 0;
		        
		 }
		}
		
		// usersIdから注文履歴を取得する
		public List<OrderHistoryInfo> getOrderHistory(String usersId) {
		
		List<OrderHistoryInfo> orderHistoryList = new ArrayList<>();
		
		List<CartListItem> cartList = new ArrayList<>();
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			

	        String sql = "SELECT ORDERID, USERSNAME, USERSPOSTCODE, USERSADDRESS, USERSTEL, USERSMAIL, USERSPAY, DATE FROM ORDERHISTORY WHERE LOGINID = ?";
	       
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setString(1, usersId);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	OrderHistoryInfo orderHistoryInfo = new OrderHistoryInfo();
	                orderHistoryInfo.setOrderId(rs.getInt("ORDERID"));
	                cartList = getCartListItem(rs.getInt("ORDERID"));
	                
	                orderHistoryInfo.setCartListItem(cartList);
	                		
	                orderHistoryInfo.setUsersName(rs.getString("USERSNAME"));
	                orderHistoryInfo.setPostcode(rs.getString("USERSPOSTCODE"));
	                orderHistoryInfo.setAddress(rs.getString("USERSADDRESS"));
	                orderHistoryInfo.setTel(rs.getString("USERSTEL"));
	                orderHistoryInfo.setMail(rs.getString("USERSMAIL"));
	                orderHistoryInfo.setPay(rs.getString("USERSPAY"));
	                Timestamp date = rs.getTimestamp("DATE");
	                orderHistoryInfo.setDate(date);
	                orderHistoryInfo.setDateString(date.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH：mm")));
	                
	                orderHistoryList.add(orderHistoryInfo);
	            }
	         
		            
		            
		 } catch (Exception e) {
		        e.printStackTrace();
		        
		 }
		 return orderHistoryList;
		}
		
		
		// orderIdから注文商品をリスト化する
		public List<CartListItem> getCartListItem(int orderId) {
			
		List<CartListItem> cartList = new ArrayList<>();
		
		
		try (Connection connection = DatabaseConnection.getConnection()) {
			
			
		//	List<CartListItem> cartList = new ArrayList<>()
			

	        String sql = "SELECT ITEMID, ITEMKOSU FROM ORDERDETAIL WHERE ORDERID = ?";
	       
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, orderId);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	CartListItem item = new CartListItem();
	        	int id = rs.getInt("ITEMID");
	        	int kosu = rs.getInt("ITEMKOSU");
	        	 item.setItemId(id);
	        	 item.setItemKosu(kosu);
	        	 String name = getItemName(rs.getInt("ITEMID"));
	        	 int price = getItemPrice(rs.getInt("ITEMID"));
	        	 String pas = getItemPas(rs.getInt("ITEMID"));
	        	 item.setSum(kosu * price);
	        	 item.setItemName(name);
	        	 item.setItemPrice(price);
	        	 item.setItemPas(pas);
	        	 cartList.add(item);
	                
	            }
	        
		            
		            
		 } catch (Exception e) {
		        e.printStackTrace();
		        
		 }
		 return cartList;
		}
		
		
	    public String getItemName(int id) {
	    	String name = "";
	    	try (Connection connection = DatabaseConnection.getConnection()) {
	    	
	    	
	    	String sql = "SELECT ITEMNAME FROM ORDERDETAIL WHERE ITEMID = ? ";
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	    
	    	    stmt.setInt(1, id); 

	    	    // クエリ実行
	    	    try (ResultSet rs = stmt.executeQuery()) {
	    	        while (rs.next()) {
	    	            // 結果の取得
	    	             name = rs.getString("ITEMNAME");
	    	             
	    	        }
	    	    }
	    	} catch (SQLException e) {
	    	    e.printStackTrace();
	    	}

	    	return name;
	    }
	    
	    public int getItemPrice(int id) {
	    	int price = 0;
	    	try (Connection connection = DatabaseConnection.getConnection()) {
	    	String sql = "SELECT ITEMPRICE FROM ORDERDETAIL WHERE ITEMID = ? ";
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	    
	    	    stmt.setInt(1, id); 

	    	    // クエリ実行
	    	    try (ResultSet rs = stmt.executeQuery()) {
	    	        while (rs.next()) {
	    	            // 結果の取得
	    	        	price = rs.getInt("ITEMPRICE");
	    	        }
	    	    }
	    	} catch (SQLException e) {
	    	    e.printStackTrace();
	    	}
	    	
	    	return price;
	    }
	    
	    public String getItemPas(int id) {
	    	String pas = "";
	    	try (Connection connection = DatabaseConnection.getConnection()) {
	    	String sql = "SELECT ITEMPAS FROM ORDERDETAIL WHERE ITEMID = ? ";
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	    
	    	    stmt.setInt(1, id); 

	    	    // クエリ実行
	    	    try (ResultSet rs = stmt.executeQuery()) {
	    	        while (rs.next()) {
	    	            // 結果の取得
	    	        	pas = rs.getString("ITEMPAS");
	    	        }
	    	    }
	    	} catch (SQLException e) {
	    	    e.printStackTrace();
	    	}
	    	
	    	return pas;
	    }
}
