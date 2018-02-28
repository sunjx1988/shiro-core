package shiro.service.impl;

import shiro.entity.SysRoles;
import shiro.service.RoleService;

/**
 * Created by sunjx on 2018/2/28.
 */
public class RoleServiceImpl implements RoleService {
    @Override
    public SysRoles createRole(SysRoles role) {
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {

    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {

    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {

    }
}
