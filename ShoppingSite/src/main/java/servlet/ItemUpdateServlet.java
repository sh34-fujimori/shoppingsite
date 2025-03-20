package servlet;

import java.io.IOException;

import dao.ItemDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;

@WebServlet("/ItemUpdateServlet")
public class ItemUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
	    int ItemId = Integer.parseInt(request.getParameter("ItemId"));
	   // System.out.println(ItemId);
	    ItemDAO dao = new ItemDAO();
	    Item item = dao.getItem(ItemId);

		// リクエストスコープに保存
		request.setAttribute("item", item);
	    
		// フォワード
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/itemUpdate.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
	    int ItemId = Integer.parseInt(request.getParameter("ItemId"));
		String ItemName = request.getParameter("ItemName");
		int ItemPrice = Integer.parseInt(request.getParameter("ItemPrice"));
		int ItemZaiko = Integer.parseInt(request.getParameter("ItemZaiko"));
		String ItemPas = request.getParameter("ItemPas");
        

        
		
		ItemDAO dao = new ItemDAO();
	    boolean result = dao.ItemUpdate(ItemId, ItemName, ItemPrice, ItemZaiko, ItemPas);
	    
        if (result) {
            // 更新成功時
        	response.sendRedirect("ItemkanriServlet");
  
        } else {
            // 更新失敗時
            response.sendRedirect("ItemUpdateServlet");
        }
	}

}
