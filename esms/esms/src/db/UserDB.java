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

}
