package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.OrderDB;
import util.UUIDTool;

public class OrderGoodsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String goodsId = req.getParameter("id");
		String amount = req.getParameter("amount");
		String userId = req.getParameter("userId");;
		
		OrderDB orderDB = new OrderDB();
		String id = UUIDTool.getUUID();
		boolean success = orderDB.insertOrder(id, goodsId, userId, amount);
		
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
