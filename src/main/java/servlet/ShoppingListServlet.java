package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCShoppingListItemDao;
import database.ShoppingListItem;

@WebServlet("/shoppinglist")
public class ShoppingListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		List<ShoppingListItem> items = dao.getAllItems();
		System.out.println(items.get(0).getTitle());
		req.setAttribute("items", items);
		req.getRequestDispatcher("/WEB-INF/list.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		JDBCShoppingListItemDao dao = new JDBCShoppingListItemDao();
		String itemTitle = req.getParameter("title");
		// todo: use the title to create a new product object
		ShoppingListItem item = new ShoppingListItem(0, itemTitle);
		// todo: use the DAO to store the product object into the database
		Boolean addsuccesfull = dao.addItem(item);
		resp.sendRedirect("shoppinglist");
	}
}
