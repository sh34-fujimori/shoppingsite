package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CartListDAO;
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


@WebServlet("/CartCheckServlet")
public class CartCheckServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	    	
	    	 Connection connection = null;
	         try {
	             connection = DatabaseConnection.getConnection(); // DatabaseConnectionクラスを使用して接続を取得
	             CartListDAO dao = new CartListDAO(connection);
	             
	     		// 3/7 仮で追加
	     		HttpSession ses = request.getSession();
	     		Users users =(Users)ses.getAttribute("Users");
	     		String usersId = users.getUsersId();
	     		//
	             
	             List<CartListItem> cartList = dao.getCartItems(usersId);
	             request.setAttribute("cartList", cartList);
	             String name = request.getParameter("usersName"); 
	 			String postcode = request.getParameter("usersPostcode"); 
	 			String address = request.getParameter("usersAddress"); 
	 			String tel = request.getParameter("usersTel"); 
	 			String mail = request.getParameter("usersMail"); 
	 			String pay = request.getParameter("usersPay"); 
	 			
	 			naiyou n=new naiyou(name,postcode,address,tel,mail,pay);
	 			System.out.println(n.getPay());
	 			HttpSession session = request.getSession();
	 		    session.setAttribute("naiyou", n);
	             
	             request.getRequestDispatcher("WEB-INF/jsp/cartCheck.jsp").forward(request, response);
	             
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
