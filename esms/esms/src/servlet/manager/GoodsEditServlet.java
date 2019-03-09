package servlet.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CategoryDB;
import db.GoodsDB;

public class GoodsEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		GoodsDB goodsDB = new GoodsDB();
		Map<String, String> goods = goodsDB.getGoodsById(id);

		req.setAttribute("goods", goods);

		// 获得类别数据，传递参数
		CategoryDB categoryDB = new CategoryDB();
		List<Map<String, String>> categoryList = categoryDB.getAllCategory();
		req.setAttribute("categoryList", categoryList);

		req.getRequestDispatcher("/page/manager/goods_edit.jsp").forward(req, resp);
	}

}
