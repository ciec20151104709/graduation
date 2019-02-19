package db;

import java.util.List;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

import util.DbUtil;

public class UserDB {

	public Map<String, String> getAdminByAccountAndPassword(String account, String password) {
		if (StringUtils.isNullOrEmpty(account) || StringUtils.isNullOrEmpty(password)) {
			return null;
		}
		// 拼接sql语句
		String sql = "select * from user where account = '" 
				+ account + "' and password = '" + password + "'";
		DbUtil dbUtil = new DbUtil();
		List<Map<String, String>> adminList = dbUtil.getDataBySql(sql);
		if (adminList != null && adminList.size() == 1) {
			return adminList.get(0);
		}
		return null;
	}

}
