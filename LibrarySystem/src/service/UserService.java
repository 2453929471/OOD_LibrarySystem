package service;

import dao.impl.BookDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Book;
import entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by gao on 15/10/19.
 */
public class UserService {
    public String confirmLogin(int uid,String pwd) throws SQLException{
        UserDaoImpl udi=new UserDaoImpl();
        return udi.confirmLogin(uid, pwd);
    }

    public User queryUserInfo(int uid) throws SQLException{
        UserDaoImpl udi=new UserDaoImpl();
        return udi.queryUserInfo(uid);
    }
    public int addUser(User user) throws SQLException{
        UserDaoImpl udi=new UserDaoImpl();
        return udi.addUser(user);
    }

    public void borrow(int uid,int bid) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        bdi.borrow(uid,bid);
    }

    public void breturn(int bid) throws SQLException{
        BookDaoImpl bdi=new BookDaoImpl();
        bdi.breturn(bid);
    }
}
