package servlet;

import java.io.IOException;

import dao.CartDAO;
import dao.kanriKeyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

@WebServlet("/MenuServlet")
public class MenyuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("Users");
		String usersId = user.getUsersId();
		
		kanriKeyDAO dao = new kanriKeyDAO();
		int key = dao.keyGet(user.getUsersId());
		System.out.println(user.getUsersId());
		System.out.println(key);
		
		request.setAttribute("userKey", key);
		
        // 3/7仮で追加
        CartDAO cartDAO = new CartDAO();
        int kosuSum = cartDAO.getCartKosu(usersId);
        
        session.setAttribute("kosuSum", kosuSum);
        //
        
        //0310追加
        String loginSuccess = (String) session.getAttribute("loginSuccess");
        
        if (loginSuccess != null) {
            request.setAttribute("loginSuccess", loginSuccess);
            session.removeAttribute("loginSuccess"); // セッションから削除
        }
        //
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/menyu.jsp");
		dispatcher.forward(request, response);
	}

}
