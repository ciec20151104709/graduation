package servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import db.GoodsDB;
import util.UUIDTool;

public class GoodsSaveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String categoryId = req.getParameter("categoryId");
		String stock = req.getParameter("stock");
		String unit = req.getParameter("unit");
		String img = req.getParameter("img");
		
		String result = "ERROR";
		GoodsDB goodsDB = new GoodsDB();
		if (StringUtils.isNullOrEmpty(id)) {
			// 插入
			id = UUIDTool.getUUID();
			if (goodsDB.insertGoods(id, name, categoryId, stock, unit, img)) {
				result = "SUCCESS";
			}
		} else {
			// 更新
			if (goodsDB.updateGoods(id, name, categoryId, stock, unit, img)) {
				result = "SUCCESS";
			}
		}
		
		resp.setContentType("text/xml;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(result);
        writer.close();
	}

	
	
}
