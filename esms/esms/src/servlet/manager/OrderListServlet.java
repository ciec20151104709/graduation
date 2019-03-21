package servlet.manager;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import db.OrderDB;

public class OrderListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获得前台参数
		String pageStr = req.getParameter("page");
		String goodsName = req.getParameter("goodsName");
		
		int page = 1;
		if (!StringUtils.isNullOrEmpty(pageStr)) {
			page = Integer.parseInt(pageStr);
		}
		
		// 获得商品数据，传递参数
		OrderDB orderDB = new OrderDB();
		List<Map<String, String>> orderList = orderDB.getOrderList(goodsName, page);
		req.setAttribute("orderList", orderList);
		// 获得商品总数，生成pageList
		int count = orderDB.getOrderCount(goodsName);
		int pageCount = count%6 == 0 ? count/6 : count/6+1;
		req.setAttribute("pageCount", pageCount);
		// 传递当前页数及其他参数
		req.setAttribute("page", page);
		req.setAttribute("goodsName", goodsName);
		
		req.getRequestDispatcher("/page/manager/order_list.jsp").forward(req, resp);
	
	}

	
	
	
}
