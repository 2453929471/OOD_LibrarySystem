package action;

import com.opensymphony.xwork2.ActionSupport;
import service.UserService;

/**
 * Created by gao on 15/10/19.
 */
public class UserAction{
    private int uid;
    private String pwd;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String login(){
        //Process proc=Runtime.getRuntime().exec("python ~/Documents/hello.py");

        //proc.waitFor();
        //System.out.print(proc.toString());
        UserService us=new UserService();
        String result= null;
        try {
            result = us.confirmLogin(uid,pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(result);
        return result;
    }

}
