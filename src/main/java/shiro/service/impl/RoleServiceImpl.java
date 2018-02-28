package shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shiro.entity.SysRoles;
import shiro.entity.SysRolesPermissionsExample;
import shiro.entity.SysRolesPermissionsKey;
import shiro.mapper.SysRolesMapper;
import shiro.mapper.SysRolesPermissionsMapper;
import shiro.service.RoleService;

import java.util.Arrays;

/**
 * Created by sunjx on 2018/2/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRolesMapper sysRolesMapper;

    @Autowired
    private SysRolesPermissionsMapper sysRolesPermissionsMapper;

    @Override
    @Transactional
    public SysRoles createRole(SysRoles role) {
        sysRolesMapper.insert(role);
        return role;
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        sysRolesMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    @Transactional
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        SysRolesPermissionsKey key = new SysRolesPermissionsKey();
        key.setRoleId(roleId);

        for (Long permissionId : permissionIds){
            key.setPermissionId(permissionId);
            sysRolesPermissionsMapper.insert(key);
        }
    }

    @Override
    @Transactional
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        SysRolesPermissionsExample example = new SysRolesPermissionsExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andPermissionIdIn(Arrays.asList(permissionIds));
    }
}
