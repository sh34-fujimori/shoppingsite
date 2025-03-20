package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CartListItem;

public class CartListDAO {
    private Connection connection;

    public CartListDAO(Connection connection) {
        this.connection = connection;
    }

//    public List<CartListItem> getCartItems() throws SQLException {
//        List<CartListItem> cartList = new ArrayList<>();
//        String sql = "SELECT ITEMID,ITEMNAME, ITEMPRICE, ITEMZAIKO, SUM(ITEMKOSU) AS TOTALKOSU,ITEMNO,ITEMPAS FROM CART GROUP BY ITEMID,ITEMNAME, ITEMPRICE, ITEMZAIKO";
//        try (PreparedStatement stmt = connection.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                CartListItem item = new CartListItem();
//                item.setItemId(rs.getInt("ITEMNO"));
//                item.setItemName(rs.getString("ITEMNAME"));
//                item.setItemPrice(rs.getInt("ITEMPRICE"));
//                item.setItemZaiko(rs.getInt("ITEMZAIKO"));
//                item.setItemKosu(rs.getInt("TOTALKOSU"));
//                item.setItemPas(rs.getString("ITEMPAS"));
//                
//                cartList.add(item);
//            }
//        }
//        return cartList;
//    }
    
    public List<CartListItem> getCartItems(String usersId) throws SQLException {
        List<CartListItem> cartList = new ArrayList<>();
        String sql = "SELECT ITEMID,ITEMNAME, ITEMPRICE, ITEMZAIKO, SUM(ITEMKOSU) AS TOTALKOSU,ITEMNO,ITEMPAS FROM CART WHERE LOGINID = ?  GROUP BY ITEMID,ITEMNAME, ITEMPRICE, ITEMZAIKO ORDER BY ITEMID";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
        	stmt.setString(1, usersId);
            ResultSet rs = stmt.executeQuery();
        	
            while (rs.next()) {
                CartListItem item = new CartListItem();
               
                item.setItemId(rs.getInt("ITEMNO"));
                item.setItemName(rs.getString("ITEMNAME"));
                item.setItemPrice(rs.getInt("ITEMPRICE"));
                item.setItemZaiko(rs.getInt("ITEMZAIKO"));
                item.setItemKosu(rs.getInt("TOTALKOSU"));
                item.setItemPas(rs.getString("ITEMPAS"));
                
                cartList.add(item);
            }
        }
        return cartList;
    }
    
}
