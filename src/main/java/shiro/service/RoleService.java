package shiro.service;

import shiro.entity.SysRoles;

/**
 * Created by sunjx on 2018/2/28.
 */
public interface RoleService {

    /**
     * 创建角色
     * @param role
     * @return
     */
    SysRoles createRole(SysRoles role);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
