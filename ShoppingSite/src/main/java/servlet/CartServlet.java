package servlet;

import java.io.IOException;

import dao.CartDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Users;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int kosuSum = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int zaiko = Integer.parseInt(request.getParameter("zaiko"));
		int kosu = Integer.parseInt(request.getParameter("kosu"));
		String pas = request.getParameter("pas");
		// 3/7仮で追加
		HttpSession session = request.getSession();
		Users Users =(Users)session.getAttribute("Users");
		String usersId = Users.getUsersId();
		
		//
		Cart cart = new Cart();
		cart.addSougaku(price);
		CartDAO dao = new CartDAO();
		dao.CartAdd(name, price, zaiko,kosu,id,pas, usersId);
		
        // 3/7仮で追加
        int kosuSum = dao.getCartKosu(usersId);
		session.setAttribute("kosuSum", kosuSum);
		//
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Item.jsp");
		dispatcher.forward(request, response);
	}
}
