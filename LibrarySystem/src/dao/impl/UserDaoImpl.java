package dao.impl;
import dao.inter.UserDao;
import dao.util.Cons;
import dao.util.DBUtil;
import entity.User;

import java.sql.*;

/**
 * Created by gao on 15/10/19.
 */
public class UserDaoImpl extends DBUtil implements UserDao{
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

    @Override
    public int addUser(User user) throws SQLException{
        Connection conn=DBUtil.getConn();
        String sql="insert into user(name,phone,email,sfz,pwd,authority) values(?,?,?,?,?,?)";
        PreparedStatement pstmt= (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,user.getName());
        pstmt.setString(2,user.getPhone());
        pstmt.setString(3,user.getEmail());
        pstmt.setString(4,user.getSfz());
        pstmt.setString(5,user.getPwd());
        pstmt.setInt(6,user.getAuthority());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        int uid=0;
        if (rs.next()) {
            uid = rs.getInt(1);
            System.out.println("数据主键：" + uid);
        }
        pstmt.close();
        conn.close();
        return uid;
    }

    @Override
    public User queryUserInfo(int uid) throws SQLException{
        User user=null;
        Connection conn= DBUtil.getConn();
        String sql="select * from user where uid="+uid;
        PreparedStatement pstmt= (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user=new User();
            user.setUid(uid);
            user.setName(rs.getString(2));
            user.setPhone(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setSfz(rs.getString(5));
            user.setAuthority(rs.getInt(7));
        }
        return user;
    }


    @Override
    public void updUser(User user) {

    }

}
