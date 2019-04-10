package db;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import util.DbUtil;

public class UserDB {

	/*
	 * 通过账号密码判断是否登录成功
	 */
	public boolean adminLoginCheck(String account, String password) {
		if (StringUtils.isNullOrEmpty(account) || StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		// 拼接sql语句
		String sql = "select * from user "
				+ "where account = '" + account + "' "
				+ "and password = '" + password + "' "
				+ "and is_admin = 0 "
				+ "and status='normal'";
		DbUtil dbUtil = new DbUtil();
		List<Map<String, String>> adminList = dbUtil.getDataBySql(sql);
		if (adminList != null && adminList.size() == 1) {
			return true;
		}
		return false;
	}
	
	public boolean userLoginCheck(String account, String password) {
		if (StringUtils.isNullOrEmpty(account) || StringUtils.isNullOrEmpty(password)) {
			return false;
		}
		// 拼接sql语句
		String sql = "select * from user "
				+ "where account = '" + account + "' "
				+ "and password = '" + password + "' "
				+ "and is_admin = 1 "
				+ "and status='normal'";
		DbUtil dbUtil = new DbUtil();
		List<Map<String, String>> adminList = dbUtil.getDataBySql(sql);
		if (adminList != null && adminList.size() == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 通过账号获得人员信息
	 */
	public Map<String, String> getUserByAccount(String account) {
		if (StringUtils.isNullOrEmpty(account)) {
			return null;
		}
		// 拼接sql语句
		String sql = "select * from user where account = '" 
				+ account + "'";
		DbUtil dbUtil = new DbUtil();
		List<Map<String, String>> adminList = dbUtil.getDataBySql(sql);
		if (adminList != null && adminList.size() == 1) {
			return adminList.get(0);
		}
		return null;
	}
	
	public List<Map<String, String>> getUserList(String name, String account, int page) {
		//拼接sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select * from user where status = 'normal' and is_admin = 1 ");
        if (!StringUtils.isNullOrEmpty(name)) {
            sql.append("and name like '%").append(name).append("%' ");
        }
        if (!StringUtils.isNullOrEmpty(account)) {
            sql.append("and account like '%").append(account).append("%' ");
        }
        sql.append(" order by account DESC ");
        
        int start = (page-1)*6;
        sql.append(" limit ").append(start).append(", 6");
        //调用数据库工具类执行查询
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getDataBySql(sql.toString());
	}

	public int getUserCount(String name, String account) {
		StringBuilder sql = new StringBuilder();
        sql.append("select count(*) from user where status = 'normal' and is_admin = 1 ");
        if (!StringUtils.isNullOrEmpty(name)) {
            sql.append("and name like '%").append(name).append("%' ");
        }
        if (!StringUtils.isNullOrEmpty(account)) {
            sql.append("and account like '%").append(account).append("%' ");
        }
        DbUtil dbUtil = new DbUtil();
        return dbUtil.getCountBySql(sql.toString());
	}
	
	public boolean deleteUserById(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append("update user set status = 'deleted' where id ='").append(id).append("'");
		
		DbUtil dbUtil = new DbUtil();
		int count = dbUtil.executeBySql(sql.toString());
		return count >= 0 ? true : false;
	}

	public boolean insertUser(String id, String account, String name, String password, String sex, String phoneNum, String address) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into user values('")
			.append(id).append("','")
			.append(account).append("','")
			.append(name).append("','")
			.append(password).append("','")
			.append(sex).append("','")
			.append(phoneNum).append("','")
			.append(address).append("','normal','1')");
		
		DbUtil dbUtil = new DbUtil();
		int count = dbUtil.executeBySql(sql.toString());
		return count >= 0 ? true : false;
		
	}

}
