package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dao.CartListDAO;
import dao.ItemDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartListItem;
import model.DatabaseConnection;
import model.Item;
import model.Users;

@WebServlet("/CarthanteiServlet")
public class CarthanteiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection(); // DatabaseConnectionクラスを使用して接続を取得
            CartListDAO dao = new CartListDAO(connection);

            HttpSession session = request.getSession();
            Users users = (Users) session.getAttribute("Users");
            String usersId = users.getUsersId();

            // カートアイテムと商品リストを取得
            List<CartListItem> cartList = dao.getCartItems(usersId);
            ItemDAO itemdao = new ItemDAO();
            List<Item> itemlist = itemdao.Itemyomi();

            // 商品名をセットに変換
            Set<String> itemNames = new HashSet<>();
            for (Item item : itemlist) {
                itemNames.add(item.getName());
            }

            List<String> errorMessages = new ArrayList<>(); // エラーメッセージのリスト

         // 存在しない商品をチェック
         List<Item> notfoundItem = new ArrayList<>();
         for (CartListItem cart : cartList) {
             if (!itemNames.contains(cart.getItemName())) {
                 notfoundItem.add(new Item(cart.getItemName()));
             }
         }

         if (!notfoundItem.isEmpty()) {
        	// 商品名を結合して文字列にする
        	String notfoundNames = notfoundItem.stream()
        	                                       .map(Item::getName)
        	                                       .collect(Collectors.joining("、"));
        	// エラーメッセージを作成
        	errorMessages.add(notfoundNames + " は存在しません。該当商品を削除してください。");
        }


         // 在庫が0の商品をチェック
         for (CartListItem cart : cartList) {
             if (cart.getItemZaiko() == 0) {
                 errorMessages.add("在庫のない商品があります。該当商品を削除してください。");
             }
         }

         // 購入数が在庫数を超えている商品をチェック
         for (CartListItem cart : cartList) {
             for (Item item : itemlist) {
                 if (cart.getItemName().equals(item.getName()) &&
                         cart.getItemZaiko() > item.getZaiko()) {
                     errorMessages.add("購入数が在庫数を超えています。購入数を修正してください。");
                 }
             }
         }

         // エラーが存在する場合まとめて表示
         if (!errorMessages.isEmpty()) {
             StringBuilder allErrors = new StringBuilder();
             for (String error : errorMessages) {
                 allErrors.append(error).append("<br>");
             }
             request.setAttribute("errorMessages", allErrors.toString());
             RequestDispatcher dispatcher = request.getRequestDispatcher("CartListServlet");
             dispatcher.forward(request, response);
             return;
         }

         // 問題がなければ次の処理へ
         session.setAttribute("cartList", cartList);
         RequestDispatcher dispatcher = request.getRequestDispatcher("OrderInforServret");
         dispatcher.forward(request, response);


        } catch (SQLException e) {
            throw new ServletException(e);
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
