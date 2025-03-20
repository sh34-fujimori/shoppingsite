package servlet;

import java.io.IOException;

import dao.CartDAO;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users loggedInUser = (Users) session.getAttribute("Users");
        
        // 3/7仮で追加
        String UsersId = loggedInUser.getUsersId();
        CartDAO cartDAO = new CartDAO();
        int kosuSum = cartDAO.getCartKosu(UsersId);
        
        session.setAttribute("kosuSum", kosuSum);

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet?error=not_logged_in");
            return;
        }

        request.setAttribute("users", loggedInUser);
        request.getRequestDispatcher("WEB-INF/jsp/userupdate.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String usersId = request.getParameter("UsersId");
        String password = request.getParameter("password");
        String usersName = request.getParameter("usersName");
        String usersPostcode = request.getParameter("usersPostcode");
        String usersAddress = request.getParameter("usersAddress");
        String usersTel = request.getParameter("usersTel");
        String usersMail = request.getParameter("usersMail");
        String usersPay = request.getParameter("usersPay");

        // 入力チェック
        if (usersName == null || usersName.isEmpty() ||
            usersPostcode == null || usersPostcode.isEmpty() ||
            usersAddress == null || usersAddress.isEmpty() ||
            usersTel == null || usersTel.isEmpty() ||
            usersMail == null || usersMail.isEmpty() ||
            usersPay == null || usersPay.isEmpty()) {

            request.setAttribute("errorMessage", "すべての項目を入力してください。");
            request.getRequestDispatcher("WEB-INF/jsp/userupdate.jsp").forward(request, response);
            return;
        }

        Users users = new Users(usersId, password, usersName, usersPostcode, usersAddress, usersTel, usersMail, usersPay);
        UsersDAO usersDAO = new UsersDAO();

        if (usersDAO.isUsersIdExists(usersId)) {
            usersDAO.updateUsers(users);
            HttpSession session = request.getSession();
            session.setAttribute("Users", users); 
            request.setAttribute("successMessage", "ユーザー情報が更新されました。");
            request.getRequestDispatcher("WEB-INF/jsp/userupdate.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "存在しないIDです");
            request.setAttribute("users", users);
            request.getRequestDispatcher("WEB-INF/jsp/userupdate.jsp").forward(request, response);
        }
    }


}
