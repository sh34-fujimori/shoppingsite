package servlet;

import java.io.IOException;

import dao.KosuChangeDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/kosuChangeServlet")
public class kosuChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mei = request.getParameter("chamei");
		int kosu = Integer.parseInt(request.getParameter("kosu"));
		System.out.println(mei);
		System.out.println(kosu);
		KosuChangeDAO dao = new KosuChangeDAO();
		dao.Changekosu(mei, kosu);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CartListServlet");
		dispatcher.forward(request, response);
	}
}
