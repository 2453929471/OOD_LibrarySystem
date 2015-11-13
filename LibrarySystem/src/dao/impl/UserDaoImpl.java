package dao.impl;
import dao.util.Cons;
import dao.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gao on 15/10/19.
 */
public class UserDaoImpl extends DBUtil{
    public String confirmLogin(int uid,String pwd) throws SQLException{
        if(uid==Cons.getAdminUid()&& pwd.equals(Cons.getAdminPwd())){
            return "ADMIN";
        }
        Connection conn= DBUtil.getConn();
        String sql="select * from user where uid="+uid;
        PreparedStatement pstmt= (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (!rs.next()) {
                return "NOEXIST";
        }
        if(rs.getString(6).equals(pwd)){
                return "SUCCESS";
        }
        return "PWDWRONG";
    }

}
