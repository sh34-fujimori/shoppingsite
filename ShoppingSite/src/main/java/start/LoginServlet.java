package start;

import java.io.IOException;

import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String UsersId = request.getParameter("userId"); 
        String password = request.getParameter("password");

        UsersDAO UsersDAO = new UsersDAO();
        Users Users = UsersDAO.findUsersById(UsersId);

        if (Users == null) {
            //response.sendRedirect("LoginServlet?error=not_found&UsersId=" + UsersId);
        	request.setAttribute("errorMessage1", "ユーザーIDが見つかりません。");
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        } else if (!Users.getPassword().equals(password)) {
            //response.sendRedirect("LoginServlet?error=invalid_password&UsersId=" + UsersId);
        	request.setAttribute("errorMessage2", "パスワードが間違っています。");
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Users", Users);
            session.setAttribute("loginSuccess", "ようこそ" + Users.getUsersName() + "さん！");
            response.sendRedirect("MenuServlet");
        }
    }
}
