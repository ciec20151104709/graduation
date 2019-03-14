package db;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import util.DbUtil;

public class EvaluateDB {

	public List<Map<String, String>> getEvaluateList(String content, String goodsName, int page) {
		StringBuilder sql = new StringBuilder();
		sql.append("select e.*, g.goods_name, u.user_name from evaluate e ")
			.append("LEFT JOIN (select id, name as goods_name from goods) g on e.goods_id = g.id ")
			.append("LEFT JOIN (select id, name as user_name from user) u on e.user_id = u.id ")
			.append("where e.status = 'normal' ");
		if (!StringUtils.isNullOrEmpty(content)) {
			sql.append("and e.content like '%")
				.append(content)
				.append("%' ");
		}
		if (!StringUtils.isNullOrEmpty(goodsName)) {
			sql.append("and g.goods_name like '%")
				.append(goodsName)
				.append("%' ");
		}
		sql.append("order by create_date desc ");
		
		int start = (page-1)*6;
        sql.append(" limit ").append(start).append(", 6");
        
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getDataBySql(sql.toString());
	}
	
	public int getEvaluateCount(String content, String goodsName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(e.id) from evaluate e ")
			.append("LEFT JOIN (select id, name as goods_name from goods) g on e.goods_id = g.id ")
			.append("LEFT JOIN (select id, name as user_name from user) u on e.user_id = u.id ")
			.append("where e.status = 'normal' ");
		if (!StringUtils.isNullOrEmpty(content)) {
			sql.append("and e.content like '%")
				.append(content)
				.append("%' ");
		}
		if (!StringUtils.isNullOrEmpty(goodsName)) {
			sql.append("and g.goods_name like '%")
				.append(goodsName)
				.append("%' ");
		}
        
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getCountBySql(sql.toString());
	}

	public boolean deleteEvaluateById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("update evaluate set status='deleted' where id = '")
			.append(id).append("'");
		//调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        int count = dbUtil.executeBySql(sql.toString());
        return count >= 0 ? true : false;
		
	}
	
}
