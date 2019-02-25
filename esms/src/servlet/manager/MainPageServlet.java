package servlet.manager;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDB;

public class MainPageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获得数据信息，放到前台页面展示
		// 1、人员信息
		String account = (String) req.getSession().getAttribute("m_userid");
		UserDB userDB = new UserDB();
		Map<String, String> user = userDB.getUserByAccount(account);
		
		req.setAttribute("user", user);
		
		req.getRequestDispatcher("/page/manager/main.jsp").forward(req,resp);
	}
	
}
