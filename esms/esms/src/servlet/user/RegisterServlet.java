package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDB;
import util.UUIDTool;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String account = req.getParameter("account");
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		String sex = req.getParameter("sex");
		String phoneNum = req.getParameter("phoneNum");
		String address = req.getParameter("address");
		
		String id = UUIDTool.getUUID();
		
		UserDB userDB = new UserDB();
		
		boolean success = userDB.insertUser(id, account, name, password, sex, phoneNum, address);
		
		String result = "ERROR";
		if (success) {
			result = "SUCCESS";
		}
		
		resp.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(result);
        out.close();
		
	}

	
}
