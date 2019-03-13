package db;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import util.DbUtil;

public class GoodsDB {

	public List<Map<String, String>> getGoodsList(String name, String category, int page) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select c.category_name, g.* from goods g ")
        	.append("left join (select id, name as category_name from category) c ")
        	.append("on g.category_id = c.id ")
            .append("where g.status = 'normal' ");
        if (!StringUtils.isNullOrEmpty(name)) {
            sql.append("and g.name like '%").append(name).append("%' ");
        }
        if (!StringUtils.isNullOrEmpty(category)) {
            sql.append("and g.category_id = '").append(category).append("' ");
        }
        sql.append(" order by name DESC ");
        
        int start = (page-1)*6;
        sql.append(" limit ").append(start).append(", 6");
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getDataBySql(sql.toString());
	}
	
	public int getGoodsCount(String name, String category) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from goods ")
            .append("where status = 'normal' ");
        if (!StringUtils.isNullOrEmpty(name)) {
            sql.append("and name like '%").append(name).append("%' ");
        }
        if (!StringUtils.isNullOrEmpty(category)) {
            sql.append("and category_id = '").append(category).append("' ");
        }
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getCountBySql(sql.toString());
	}
	
	public boolean delGoods(String id) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("update goods set status='deleted' where id = '")
        	.append(id)
        	.append("'; ");
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        int exeCount = dbUtil.executeBySql(sql.toString());
        return exeCount >= 0 ? true : false;
	}
	
	public boolean insertGoods(String id, String name, 
			String categoryId, String stock, String unit, String img) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into goods values ('")
			.append(id).append("','")
			.append(name).append("','")
			.append(categoryId).append("','")
			.append(stock).append("','")
			.append(unit).append("','")
			.append(img).append("','normal')");
		//调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        int exeCount = dbUtil.executeBySql(sql.toString());
        return exeCount >= 0 ? true : false;
	}
	
	public boolean updateGoods(String id, String name, 
			String categoryId, String stock, String unit, String img) {
		StringBuilder sql = new StringBuilder();
		sql.append("update goods set name='")
			.append(name).append("',category_id='")
			.append(categoryId).append("',stock='")
			.append(stock).append("',unit='")
			.append(unit).append("',image='")
			.append(img).append("' where id = '")
			.append(id).append("'");
		//调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        int exeCount = dbUtil.executeBySql(sql.toString());
        return exeCount >= 0 ? true : false;
	}
	
	public Map<String, String> getGoodsById(String id) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select * from goods where id='").append(id).append("'");
        
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        List<Map<String, String>> list = dbUtil.getDataBySql(sql.toString());
        if (list != null && list.size() > 0) {
        	return list.get(0);
        }
        return null;
	}
	
}
