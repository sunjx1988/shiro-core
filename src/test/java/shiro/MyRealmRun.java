package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sunjx on 2018/2/27.
 */

public class MyRealmRun {

    private static final String INI_PATH = "classpath:myrealm-shiro.ini";
    private static final String USER_NAME = "sunjx";
    private static final String PASS_WORD = "000000";

    @Test
    public void main(){

        //1.创建securityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(INI_PATH);
        SecurityManager securityManager = factory.getInstance();

        //2.给SecurityUtils设置manager
        SecurityUtils.setSecurityManager(securityManager);

        //3.创建subject
        Subject subject = SecurityUtils.getSubject();

        //4.创建用户名，密码验证的token
        UsernamePasswordToken token = new UsernamePasswordToken(USER_NAME,PASS_WORD);

        //5.进行登录验证
        try {
            subject.login(token);

            //用户名或密码错误
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("用户名密码错误！！！");
        }

        //断言认证过了
        Assert.assertTrue(subject.isAuthenticated());

        //登出
        subject.logout();
    }
}
