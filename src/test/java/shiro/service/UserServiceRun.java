package shiro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import shiro.entity.SysPermissions;
import shiro.entity.SysRoles;
import shiro.entity.SysUsers;
import shiro.entity.SysUsersRolesKey;

import java.util.Set;

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

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @Test
    public void createSysUsers(){
        String adminName = "sunjx";
        String employeeName = "zhang";

        createAdmin(adminName);
        createEmployee(employeeName);

        showAllRoleByUserName(adminName);
        showAllPermissionByUserName(adminName);

        showAllRoleByUserName(employeeName);
        showAllPermissionByUserName(employeeName);

    }

    //创建admin
    private void createAdmin(String userName){
        //创建用户
        SysUsers user = insertUsers(userName);

        //创建角色
        SysRoles role = insertRoles("admin" , "管理员");

        //用户绑定角色
        userService.correlationRoles(user.getId(), role.getId());

        //创建权限
        SysPermissions permission = insertPermissions("user:*", "对所有用户的所有操作权限");

        //角色绑定权限
        roleService.correlationPermissions(role.getId(), permission.getId());

        //新建权限
        permission = insertPermissions("company:*", "对所有公司的所有操作权限");

        //角色绑定权限
        roleService.correlationPermissions(role.getId(), permission.getId());
    }

    //创建employee
    private void createEmployee(String userName){
        //创建用户
        SysUsers user = insertUsers(userName);

        //创建角色
        SysRoles role = insertRoles("employee" , "普通员工");

        //用户绑定角色
        userService.correlationRoles(user.getId(), role.getId());

        //新建权限
        SysPermissions permission = insertPermissions("company:view", "对所有公司的查看权限");

        //角色绑定权限
        roleService.correlationPermissions(role.getId(), permission.getId());
    }


    private void showAllPermissionByUserName(String userName){
        System.out.println(userName + "的权限: " + userService.findPermissions(userName));
    }

    private void showAllRoleByUserName(String userName){
        System.out.println(userName + "的角色: " + userService.findRoles(userName));
    }

    //新建权限
    private SysPermissions insertPermissions(String permissionVal, String desc) {
        SysPermissions permission = new SysPermissions();
        permission.setAvailable(false);
        permission.setDescription(desc);
        permission.setPermission(permissionVal);

        permissionService.createPermission(permission);
        System.out.println("新增权限: " + permission);
        return permission;
    }

    //新建角色
    private SysRoles insertRoles(String roleName, String desc) {
        SysRoles role = new SysRoles();
        role.setAvailable(false);
        role.setRole(roleName);
        role.setDescription(desc);

        roleService.createRole(role);
        System.out.println("新增角色: " + role);
        return role;
    }

    //新建用户
    private SysUsers insertUsers(String userName) {
        SysUsers user = new SysUsers();
        user.setLocked(false);
        user.setUsername(userName);
        user.setPassword("000000");
        user.setSalt("123456");

        userService.createSysUsers(user);
        System.out.println("新增用户: " + user);
        return user;
    }

}
