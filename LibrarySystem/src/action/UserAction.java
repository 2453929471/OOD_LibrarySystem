package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.BookService;
import service.UserService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * Created by gao on 15/10/19.
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
    static Logger logger = LogManager.getLogger(BookAction.class.getName());

    private User user=new User();
    @Override
    public User getModel() {
        return user;
    }

    private int bid;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    private Map<String,Object> req;

    public String login(){
        //System.out.print("enter login");
        logger.entry();
        UserService us=new UserService();
        String result= null;
        try {
            result = us.confirmLogin(user.getUid(),user.getPwd());
            if(result.equals("SUCCESS")||result.equals("ADMIN")){
                Map session = (Map<String,Object>)ActionContext.getContext().getSession();
                session.put("uid",user.getUid());
            }
        } catch (Exception e) {
            logger.error("Exception"+e);
            //e.printStackTrace();
        }
        //System.out.print(result);
        logger.debug("login -- uid:" + user.getUid() + " pwd:" + user.getPwd());
        return result;
    }

    public String queryUserInfo(){
        logger.entry();
        //Map session=ActionContext.getContext().getSession();
        try {
            /*if(session.get("uid")!=null){
                System.out.println("session get uid != null");
                user.setUid((int)session.get("uid"));
            }*/
            //System.out.print(user.getUid());
            UserService us=new UserService();
            User u=us.queryUserInfo(user.getUid());
            if(u==null){
                logger.trace("queryUserInfo u==null");
                return "NORESULT";
            }
            BookService bs=new BookService();
            List blist=bs.queryUserInfo(user.getUid());
            req= (Map<String,Object>)ActionContext.getContext().get("request");
            if(blist.isEmpty()){
                u.setBnum(0);
            }else{
                u.setBnum(blist.size());
            }
            req.put("blist",blist);
            req.put("u",u);
            logger.trace("queryUserInfo success");
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String addUser(){
        logger.info("entry addUser");
        UserService us=new UserService();
        try {
            int userid=us.addUser(user);
            if(userid==0){
                return "ERROR";
            }
            logger.info("addUser success --"+ActionContext.getContext().getSession().get("uid"));
            req= (Map<String,Object>)ActionContext.getContext().get("request");
            user.setUid(userid);
            System.out.print(user.getAuthority()+user.getUid()+user.getName());
            req.put("u",user);
            return "SUCCESS";
        } catch (Exception e) {
            logger.error("Exception"+e);
            //e.printStackTrace();
        }
        return "ERROR";
    }

    public String borrow(){
        logger.info("entry borrow");
        UserService us=new UserService();
        try {
            us.borrow(user.getUid(),bid);
            logger.info(user.getUid()+" borrow "+bid+" --"+ActionContext.getContext().getSession().get("uid"));
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String breturn(){
        logger.entry();
        UserService us=new UserService();
        try {
            us.breturn(bid);
            logger.info("breturn --"+bid);
            return "SUCCESS";
        } catch (SQLException e) {
            logger.error("SQLException"+e);
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String logout(){
        logger.entry();
        UserService us=new UserService();
        Map session=ActionContext.getContext().getSession();
        session.clear();
        return "SUCCESS";
    }


    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getCate(){
        switch (sub){
            case "borrow":
                return "BORROW";
            case "return":
                return "RETURN";
            case "user":
                return "USER";
            case "addUser":
                return "ADDUSER";
            default:
                return "ADMIN";
        }
    }
}
