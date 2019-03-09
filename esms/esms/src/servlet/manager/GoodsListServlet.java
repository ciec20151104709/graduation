package servlet.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import db.CategoryDB;
import db.GoodsDB;

public class GoodsListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获得前台参数
		String pageStr = req.getParameter("page");
		String name = req.getParameter("name");
		String categoryId = req.getParameter("categoryId");
		
		int page = 1;
		if (!StringUtils.isNullOrEmpty(pageStr)) {
			page = Integer.parseInt(pageStr);
		}
		
		// 获得商品数据，传递参数
		GoodsDB goodsDB = new GoodsDB();
		List<Map<String, String>> goodsList = goodsDB.getGoodsList(name, categoryId, page);
		req.setAttribute("goodsList", goodsList);
		// 获得商品总数，生成pageList
		int count = goodsDB.getGoodsCount(name, categoryId);
		int pageCount = count%6 == 0 ? count/6 : count/6+1;
		req.setAttribute("pageCount", pageCount);
		// 传递当前页数及其他参数
		req.setAttribute("page", page);
		req.setAttribute("name", name);
		req.setAttribute("categoryId", categoryId);
		// 获得类别数据，传递参数
		CategoryDB categoryDB = new CategoryDB();
		List<Map<String, String>> categoryList = categoryDB.getAllCategory();
		req.setAttribute("categoryList", categoryList);
		
		req.getRequestDispatcher("/page/manager/goods_list.jsp").forward(req, resp);
	}
	
}
