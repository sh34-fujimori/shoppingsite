package servlet;

import java.io.IOException;

import dao.DeleteItemDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteItem")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("del");
		System.out.println(id);
		DeleteItemDAO dao = new DeleteItemDAO();

		dao.DeleteItem(Integer.parseInt(id));

		RequestDispatcher dispatcher = request.getRequestDispatcher("ItemkanriServlet");
		dispatcher.forward(request, response);
	}

}
