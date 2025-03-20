package servlet;

import java.io.IOException;

import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Users;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/userregister.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String UsersId = request.getParameter("UsersId");
        String password = request.getParameter("password");
        
        String users_name = request.getParameter("users_name");
        String users_postcode = request.getParameter("users_postcode");
        String users_address = request.getParameter("users_address");
        String users_tel = request.getParameter("users_tel");
        String users_mail = request.getParameter("users_mail");
        String users_pay = request.getParameter("users_pay");

        if (UsersId == null || UsersId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "ユーザーIDは必須です。");
            request.setAttribute("UsersId", UsersId);
            request.setAttribute("password", password);
            request.setAttribute("users_name", users_name);
            request.setAttribute("users_postcode", users_postcode);
            request.setAttribute("users_address", users_address);
            request.setAttribute("users_tel", users_tel);
            request.setAttribute("users_mail", users_mail);
            request.setAttribute("users_pay", users_pay);
            request.getRequestDispatcher("WEB-INF/jsp/userregister.jsp").forward(request, response);
            return;
        }

        Users users = new Users(UsersId, password, users_name, users_postcode, users_address, users_tel, users_mail, users_pay);
        UsersDAO usersDAO = new UsersDAO();

        if (usersDAO.isUsersIdExists(UsersId)) {
            request.setAttribute("errorMessage", "すでに存在するIDです。");
            request.setAttribute("UsersId", UsersId);
            request.setAttribute("password", password);
            request.setAttribute("users_name", users_name);
            request.setAttribute("users_postcode", users_postcode);
            request.setAttribute("users_address", users_address);
            request.setAttribute("users_tel", users_tel);
            request.setAttribute("users_mail", users_mail);
            request.setAttribute("users_pay", users_pay);
            request.getRequestDispatcher("WEB-INF/jsp/userregister.jsp").forward(request, response);
        } else {
        	usersDAO.insertUsers(users);
        	request.setAttribute("successMessage", "会員登録が完了しました。");         	
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }
        
    }


}
