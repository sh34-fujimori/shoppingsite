package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import dao.CartDAO;
import dao.CartListDAO;
import dao.CompDAO;
import dao.OrderHistoryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartListItem;
import model.DatabaseConnection;
import model.Users;
import model.naiyou;

@WebServlet("/CompServlet")
public class CompServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        Connection connection = null;
       
        try {
        	
            connection = DatabaseConnection.getConnection();
            OrderHistoryDAO odao = new OrderHistoryDAO();
            
    		// 3/7 仮で追加
    		HttpSession ses = request.getSession();
    		Users users =(Users)ses.getAttribute("Users");
    		String usersId = users.getUsersId();
    		//
    		CartListDAO cdao = new CartListDAO(connection);
    		List<CartListItem> cartListItems = cdao.getCartItems(usersId); // 3/7変更
    		
    		
    		naiyou naiyou =(naiyou)ses.getAttribute("naiyou");
    		String name = naiyou.getName();
    		
    		// 3/6 追加
    		String postcode = naiyou.getPostcode();
    		String address = naiyou.getAddress();
    		String tel = naiyou.getTel();
    		String mail = naiyou.getMail();
    		String pay = naiyou.getPay();
    		Timestamp date = Timestamp.valueOf(LocalDateTime.now());
    		String dateString = date.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日HH：mm"));
    		//
    		
    		int orderId = odao.registerOrderHistory(usersId, name, postcode, address, tel, mail, pay, date);
    		
    		for(var value: cartListItems) {
    			
    			int itemId = value.getItemId();
    		//	System.out.println(itemId);	// テスト
    			int itemKosu = value.getItemKosu();
    			int itemPrice = value.getItemPrice();
    			String itemName = value.getItemName();
    			int sum = itemPrice * itemKosu;
    			String itemPas = value.getItemPas();
    			
    			odao.registerOrderDetail(orderId, itemId, itemKosu,itemPrice, itemName,itemPas, sum);
    		}
    		
    		// 3/6 追加
    		
    		String from = "Wonderful New Future Origin";
    		String title = "注文完了メール";
    		StringBuilder contents = new StringBuilder();
    		int totalsum = 0;
    		
    		contents.append("この度はご注文ありがとうございます。<br><br>");
    		contents.append("＜注文者情報＞<br>");
    		contents.append("注文ID：" + orderId+ "<br>");
    		contents.append("注文日時：" + dateString + "<br>");
    		contents.append("氏名：" + name + "　様<br>");
    		contents.append("住所：<br>");
    		contents.append("〒"+  postcode + "<br>");
    		contents.append(address + "<br>");
    		contents.append("電話番号：" + tel + "<br>");
    		contents.append("メールアドレス：" + mail + "<br>");
    		contents.append("お支払方法：" + pay + "<br><br>");
    		
    		contents.append("＜ご注文内容＞<br>");
    		
    		for(var value: cartListItems) {
    			
    			String itemName = value.getItemName();
    			int itemPrice = value.getItemPrice();
    			int itemKosu = value.getItemKosu();
    			int sum = itemPrice * itemKosu;
    			
    			contents.append("商品名:"+ itemName + "<br>");
    			contents.append("価格："+ itemPrice + "円<br>");
    			contents.append("個数：" + itemKosu + "台<br>");
    			contents.append("小計："+ sum + "円<br>");
    			contents.append("-------------------------------------------<br>");	
    			totalsum += sum;
    		}
    		contents.append("合計金額：" + totalsum + "円");
    		
    		String detail = contents.toString();
    		
//    		try {
//				ShopMail.send(1, mail, from, title, detail, 1);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
    		
    		//
    		
            HttpSession session = request.getSession();  // 現在のセッションを取得

            List<CartListItem> cart = (List<CartListItem>) session.getAttribute("cartList");  // セッションからデータを取得
            
            

            // 配列の初期化
            String[] itemNames = new String[cart.size()];
            int[] itemKosus = new int[cart.size()];
            int j = 0;
            
            // 配列に要素を格納
            for (CartListItem ca : cart) {
                itemNames[j] = ca.getItemName();
                itemKosus[j] = ca.getItemKosu();
                j++;
          
            }

            System.out.println("itemNames: " + Arrays.toString(itemNames));
            System.out.println("itemKosus: " + Arrays.toString(itemKosus));

            if (itemNames != null && itemKosus != null) {
                for (int i = 0; i < itemNames.length; i++) {
                    String itemName = itemNames[i];
                    int itemKosu = itemKosus[i];

                    String sql = "UPDATE ITEM SET ITEMZAIKO = ITEMZAIKO - ? WHERE ITEMNAME = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, itemKosu);
                    statement.setString(2, itemName);

                    int rowsUpdated = statement.executeUpdate();
                    System.out.println("rowsUpdated: " + rowsUpdated);
                    if (rowsUpdated > 0) {
                        System.out.println("在庫が正常に更新されました。");
                    } else {
                        System.out.println("在庫更新に失敗しました。");
                    }
                } 
            }

            CompDAO dao = new CompDAO();
            dao.Cartdel(usersId);
            //dao.Cartres();
            // 3/7仮で追加
            CartDAO cartDAO = new CartDAO();
            int kosuSum = cartDAO.getCartKosu(usersId);
            
            session.setAttribute("kosuSum", kosuSum);
            //
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Comp.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("データベースエラーが発生しました。エラー: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

