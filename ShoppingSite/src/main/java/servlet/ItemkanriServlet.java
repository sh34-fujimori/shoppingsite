package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.ItemDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Item;

@WebServlet("/ItemkanriServlet")
public class ItemkanriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Item> list = new ArrayList<>();
		
		ItemDAO dao = new ItemDAO();
		
		list = dao.Itemyomi();

		HttpSession session = request.getSession();
	    session.setAttribute("kanriList", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Itemkanri.jsp");
		dispatcher.forward(request, response);
	}
}
