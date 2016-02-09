package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Created by gao on 15/12/29.
 */
public class MyInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation invoker) throws Exception {
        Object uid = ActionContext.getContext().getSession().get("uid");
        if(uid == null){
            return Action.LOGIN;  // 这里返回用户登录页面视图
        }
        return invoker.invoke();
    }
}
