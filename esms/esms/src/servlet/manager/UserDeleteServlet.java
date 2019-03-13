package servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UserDB;

public class UserDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		UserDB userDB = new UserDB();
		String result = "ERROR";
		if (userDB.deleteUserById(id)) {
			result = "SUCCESS";
		}
		
		resp.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(result);
        out.close();
	}
	
	

}
