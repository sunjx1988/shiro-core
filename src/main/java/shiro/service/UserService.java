package shiro.service;

import shiro.entity.SysUsers;

import java.util.Set;

/**
 * Created by sunjx on 2018/2/28.
 */
public interface UserService {
    /**
     * 创建用户
     *
     */
    SysUsers createSysUsers(SysUsers sysUsers);

    /**
     * 修改密码
     *
     */
    void changePassword(Long sysUsersId, String newPassword);

    /**
     * 添加用户-角色关系
     *
     */
    void correlationRoles(Long sysUsersId, Long... roleIds);


    /**
     * 移除用户-角色关系
     *
     */
    void uncorrelationRoles(Long sysUsersId, Long... roleIds);

    /**
     * 根据用户名查找用户
     *
     */
    SysUsers findBySysUsersname(String sysUsersname);

    /**
     * 根据用户名查找其角色
     *
     */
    Set<String> findRoles(String sysUsersname);

    /**
     * 根据用户名查找其权限
     *
     */
    Set<String> findPermissions(String sysUsersname);
}
