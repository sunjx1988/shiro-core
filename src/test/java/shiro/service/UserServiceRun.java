package shiro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import shiro.entity.SysUsers;

/**
 * Created by sunjx on 2018/2/28.
 * 如果是springJunit 如果需要事务机制生效 必须添加 @Transactional<br/>
 * 此处添加@Transactional后如果需要设置事务的方法没有设置事务，那么方法就不受事务的管理<br/>
 * 如果希望所有事务回滚必须添加@Rollback
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Transactional
@Rollback
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
