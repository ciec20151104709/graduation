package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDB;

public class LoginCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String account = req.getParameter("username");
		String password = req.getParameter("password");

		// 对数据库进行查询
		UserDB userDB = new UserDB();
		boolean success = userDB.userLoginCheck(account, password);
		String result = "ERROR";
		if (success) {
			req.getSession().setAttribute("u_userid", account);
			result = "SUCCESS";
		}
		
		resp.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(result);
        out.close();
	}

}
