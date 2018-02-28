package shiro.service;

import shiro.entity.SysPermissions;

/**
 * Created by sunjx on 2018/2/28.
 */
public interface PermissionService {

    /**
     * 创建权限
     * @param permission
     * @return
     */
    SysPermissions createPermission(SysPermissions permission);

    /**
     * 删除权限
     * @param permissionId
     */
    void deletePermission(Long permissionId);
}
