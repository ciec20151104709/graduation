package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import db.OrderDB;

public class OrderAllListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		// 获得所有的订单
		OrderDB orderDB = new OrderDB();
		List<Map<String, String>> allOrder = orderDB.getAllList(id);
		
		String content = JSON.toJSONString(allOrder);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(content);
        out.close();
		
	}
	
	

}
