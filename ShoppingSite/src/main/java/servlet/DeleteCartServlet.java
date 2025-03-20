package servlet;

import java.io.IOException;

import dao.DeleteCartDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("delname");
		System.out.println(name);
 		// 3/7 仮で追加
 		HttpSession ses = request.getSession();
 		Users users =(Users)ses.getAttribute("Users");
 		String usersId = users.getUsersId();
 		//
		
		DeleteCartDAO dao = new DeleteCartDAO();
		dao.DeleteCart(name, usersId);
		
		// 3/7仮で追加
		HttpSession session = request.getSession();
		int kosuSum =(int)session.getAttribute("kosuSum");
		kosuSum -= 1;
		if (kosuSum < 0) {
			kosuSum = 0;
		}
		session.setAttribute("kosuSum", kosuSum);
		//
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CartListServlet");
		dispatcher.forward(request, response);
	}
}