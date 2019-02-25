package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class DbUtil {

    /**
     * 通过sql及参数查count
     * @param sql
     * @param paramList
     * @return
     */
    public int getCountBySql(String sql) {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet re = stmt.executeQuery();  
            if(re.next()) {
                return re.getInt(1);
            }
            return 0;
        } catch (Exception e) {  
            e.printStackTrace();
            return 0;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }
    }
    
    public List<Map<String, String>> getDataBySql(String sql) {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            ResultSet re = stmt.executeQuery();  
            return resultSetToList(re);
        } catch (Exception e) {  
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    
    public int executeBySql(String sql) {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate(sql);
        } catch (Exception e) {  
            e.printStackTrace();
            return 0;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }
    }
    
    private List<Map<String, String>> resultSetToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null) {
            return null;
        }
        ResultSetMetaData md = rs.getMetaData();   
        int columnCount = md.getColumnCount();   
        List<Map<String, String>> list = new ArrayList<Map<String, String>> (); 
        Map<String, String> rowData = new HashMap<String, String>();   
        while (rs.next()) {   
            rowData = new HashMap<String, String> (columnCount);   
            for (int i = 1; i <= columnCount; i++) {   
                rowData.put(md.getColumnName(i), rs.getObject(i) == null?"":rs.getObject(i).toString());   
            }   
            list.add(rowData);   
        }   
        return list;   
    }
    
}
