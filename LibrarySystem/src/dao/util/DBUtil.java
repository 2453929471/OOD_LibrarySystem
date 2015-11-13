package dao.util;

import java.sql.*;

/**
 * Created by gao on 15/10/19.
 */
public class DBUtil {
    protected static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Library";
        String username = "root";
        String password = "88888888";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs,Statement stmt,Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (stmt != null) {   // 关闭声明
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){  // 关闭连接对象
            try{
                conn.close() ;
            }catch(SQLException e){
                e.printStackTrace() ;
            }
        }
    }
}
