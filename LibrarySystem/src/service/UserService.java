package service;

import dao.impl.UserDaoImpl;

/**
 * Created by gao on 15/10/19.
 */
public class UserService {
    public String confirmLogin(int uid,String pwd) throws Exception{
        UserDaoImpl ui=new UserDaoImpl();
        return ui.confirmLogin(uid, pwd);
    }
}
