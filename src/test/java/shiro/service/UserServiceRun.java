package shiro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shiro.entity.SysUsers;

/**
 * Created by sunjx on 2018/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceRun {

    @Autowired
    UserService userService;

    @Test
    public void createSysUsers(){
        SysUsers user = new SysUsers();
        user.setLocked(false);
        user.setUsername("sunjx" + System.currentTimeMillis());
        user.setPassword("000000");
        user.setSalt("123456");

        userService.createSysUsers(user);

        System.out.println(user);
    }
}
