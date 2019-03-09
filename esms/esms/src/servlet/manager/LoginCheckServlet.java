package servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDB;

public class LoginCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		// 对数据库进行查询
		UserDB userDB = new UserDB();
		boolean success = userDB.adminLoginCheck(account, password);
		if (success) {
			// 设置session信息
			req.getSession().setAttribute("m_userid", account);
			// 登陆成功
			resp.sendRedirect("/esms/manager/main_page");
		} else {
			// 登陆失败
			req.setAttribute("loginInfo", "登录失败,请重新登录");
			req.getRequestDispatcher("/manager/login_page").forward(req, resp);
		}
	}

}
