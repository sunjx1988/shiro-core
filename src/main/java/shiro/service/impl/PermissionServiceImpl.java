package shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shiro.entity.SysPermissions;
import shiro.mapper.SysPermissionsMapper;
import shiro.service.PermissionService;

/**
 * Created by sunjx on 2018/2/28.
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionsMapper sysPermissionsMapper;

    @Override
    @Transactional
    public SysPermissions createPermission(SysPermissions permission) {
        sysPermissionsMapper.insert(permission);
        return permission;
    }

    @Override
    @Transactional
    public void deletePermission(Long permissionId) {
        sysPermissionsMapper.deleteByPrimaryKey(permissionId);
    }
}
