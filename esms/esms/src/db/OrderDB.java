package db;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import util.DbUtil;

public class OrderDB {

	public List<Map<String, String>> getOrderList(String goodsName, int page) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select o.*, g.goods_name, g.unit, u.user_name from order_form o ")
        	.append("LEFT JOIN (select id, name as goods_name, unit from goods) g on o.goods_id = g.id ")
        	.append("LEFT JOIN (select id, name as user_name from user) u on o.user_id = u.id ")
            .append("where o.status = 'normal' ");
        if (!StringUtils.isNullOrEmpty(goodsName)) {
            sql.append("and g.goods_name like '%").append(goodsName).append("%' ");
        }
        sql.append(" order by o.create_date DESC ");
        
        int start = (page-1)*6;
        sql.append(" limit ").append(start).append(", 6");
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getDataBySql(sql.toString());
	}
	
	public int getOrderCount(String goodsName) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select count(o.id) from order_form o ")
        	.append("LEFT JOIN (select id, name as goods_name from goods) g on o.goods_id = g.id ")
        	.append("LEFT JOIN (select id, name as user_name from user) u on o.user_id = u.id ")
            .append("where o.status = 'normal' ");
        if (!StringUtils.isNullOrEmpty(goodsName)) {
            sql.append("and g.goods_name like '%").append(goodsName).append("%' ");
        }
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getCountBySql(sql.toString());
	}
	
	public Map<String, String> getOrderById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from order_form where id ='").append(id).append("'");
		//调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        List<Map<String, String>> list = dbUtil.getDataBySql(sql.toString());
        if (list != null && list.size() == 1) {
        	return list.get(0);
        }
        return null;
	}

	public boolean orderPass(String id) {
		boolean result = false;
		Map<String, String> orderForm = getOrderById(id);
		if (orderForm == null) {
			return result;
		}
		// 对商品数据进行减法操作
		StringBuilder sql = new StringBuilder();
		sql.append("update goods set stock = stock - ")
			.append(orderForm.get("amount"))
			.append(" where id = '")
			.append(orderForm.get("goods_id")).append("'");
		//调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        if (dbUtil.executeBySql(sql.toString()) >= 0) {
        	sql = new StringBuilder();
        	sql.append("update order_form set status = 'passed' where id = '")
        		.append(id).append("'");
        	if (dbUtil.executeBySql(sql.toString()) >= 0) {
        		return true;
        	}
        }
        return result;
	}
	
	public boolean orderRefuse(String id) {
		StringBuilder sql = new StringBuilder();
    	sql.append("update order_form set status = 'refused' where id = '")
			.append(id).append("'");
    	DbUtil dbUtil = new DbUtil();
    	if (dbUtil.executeBySql(sql.toString()) >= 0) {
    		return true;
    	}
    	return false;
	}
	
	public List<Map<String, String>> getOrderHistoryList(String goodsName, int page) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select o.*, g.goods_name, g.unit, u.user_name from order_form o ")
        	.append("LEFT JOIN (select id, name as goods_name, unit from goods) g on o.goods_id = g.id ")
        	.append("LEFT JOIN (select id, name as user_name from user) u on o.user_id = u.id ")
            .append("where o.status <> 'normal' ");
        if (!StringUtils.isNullOrEmpty(goodsName)) {
            sql.append("and g.goods_name like '%").append(goodsName).append("%' ");
        }
        sql.append(" order by o.create_date DESC ");
        
        int start = (page-1)*6;
        sql.append(" limit ").append(start).append(", 6");
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getDataBySql(sql.toString());
	}
	
	public int getOrderHistoryCount(String goodsName) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select count(o.id) from order_form o ")
        	.append("LEFT JOIN (select id, name as goods_name from goods) g on o.goods_id = g.id ")
        	.append("LEFT JOIN (select id, name as user_name from user) u on o.user_id = u.id ")
            .append("where o.status <> 'normal' ");
        if (!StringUtils.isNullOrEmpty(goodsName)) {
            sql.append("and g.goods_name like '%").append(goodsName).append("%' ");
        }
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getCountBySql(sql.toString());
	}
	
}
