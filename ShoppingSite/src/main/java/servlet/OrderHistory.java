package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import dao.CartDAO;
import dao.OrderHistoryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DatabaseConnection;
import model.OrderHistoryInfo;
import model.Users;

@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection connection = DatabaseConnection.getConnection()) {
		
		
		OrderHistoryDAO dao = new OrderHistoryDAO();
		
		HttpSession session = request.getSession();
		Users Users =(Users)session.getAttribute("Users");
		String UsersId = Users.getUsersId();
		
        // 3/7仮で追加
        CartDAO cartDAO = new CartDAO();
        int kosuSum = cartDAO.getCartKosu(UsersId);
        
        session.setAttribute("kosuSum", kosuSum);
        //

		List<OrderHistoryInfo> orderHistoryList = dao.getOrderHistory(UsersId);
		
		if (orderHistoryList.size() == 0) {
			// リクエストスコープに保存
			String notFound = "購入履歴はありません。";
			request.setAttribute("notFound", notFound);
		}
		// リクエストスコープに保存
		request.setAttribute("orderHistoryList", orderHistoryList);
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/orderHistory.jsp");
		dispatcher.forward(request, response);
		
		 } catch (Exception e) {
		        e.printStackTrace();
		        
		 }
	}
}