package servlet;

import java.io.IOException;

import dao.ItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterItemServlet")
public class RegisterItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
        int itemZaiko = Integer.parseInt(request.getParameter("itemZaiko"));
        String itemPas = request.getParameter("itemPas"); // 画像のパスを文字列として受け取る

        ItemDAO dao = new ItemDAO();
        dao.ItemInsert(itemName, itemPrice, itemZaiko, itemPas);

        // 商品管理ページにリダイレクト
        response.sendRedirect("ItemkanriServlet");
    }
}
