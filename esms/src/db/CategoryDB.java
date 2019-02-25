package db;

import java.util.List;
import java.util.Map;

import util.DbUtil;

public class CategoryDB {

	public List<Map<String, String>> getAllCategory() {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select * from category order by id");
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getDataBySql(sql.toString());
	}
	
}
