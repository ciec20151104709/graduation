package servlet.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import db.GoodsDB;
import db.UserDB;

public class MainPageServlet extends HttpServlet {

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
		if (categoryId == null) {
			categoryId = "";
		}
		// 获得商品数据，传递参数
		GoodsDB goodsDB = new GoodsDB();
		List<Map<String, String>> goodsList = goodsDB.getUserGoodsList(name, categoryId, page);
		req.setAttribute("goodsList", goodsList);
		// 获得商品总数，生成pageList
		int count = goodsDB.getGoodsCount(name, categoryId);
		int pageCount = count % 30 == 0 ? count / 30 : count / 30 + 1;
		req.setAttribute("pageCount", pageCount);
		// 传递当前页数及其他参数
		req.setAttribute("page", page);
		req.setAttribute("name", name);
		req.setAttribute("categoryId", categoryId);
		
		// 判断是否session中有登录数据，有则加载人员信息
		
		if (req.getSession().getAttribute("u_userid") != null) {
			// 通过account获得人员信息
			String account = (String) req.getSession().getAttribute("u_userid");
			UserDB userDB = new UserDB();
			Map<String, String> user = userDB.getUserByAccount(account);
			//获得人员名称
			req.setAttribute("loginName", user.get("name"));
			req.setAttribute("loginId", user.get("id"));
		}

		req.getRequestDispatcher("/page/user/main.jsp").forward(req, resp);
	}

}
