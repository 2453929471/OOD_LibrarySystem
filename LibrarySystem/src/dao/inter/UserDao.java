package dao.inter;

import entity.User;

import java.sql.SQLException;

/**
 * Created by gao on 15/11/15.
 */
public interface UserDao {
    public String confirmLogin(int uid, String pwd)throws SQLException;
    public int  addUser(User user) throws SQLException;
    public User queryUserInfo(int uid) throws SQLException;
    public void updUser(User user) throws SQLException;
}
