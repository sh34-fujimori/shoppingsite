package servlet;

import java.io.IOException;

import dao.kanriKeyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

@WebServlet("/ConceptServlet")
public class ConceptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("Users");
		
		kanriKeyDAO dao = new kanriKeyDAO();
		int key = dao.keyGet(user.getUsersId());
		System.out.println(user.getUsersId());
		System.out.println(key);
		
		request.setAttribute("userKey", key);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/concept.jsp");
		dispatcher.forward(request, response);
	}

}
