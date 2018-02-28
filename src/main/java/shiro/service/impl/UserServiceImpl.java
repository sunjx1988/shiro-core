package shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import shiro.entity.*;
import shiro.helper.PasswordHelper;
import shiro.mapper.SysRolesMapper;
import shiro.mapper.SysUsersRolesMapper;
import shiro.mapper.custom.CustomUsersMapper;
import shiro.service.UserService;

import java.util.*;

/**
 * Created by sunjx on 2018/2/28.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomUsersMapper sysUsersMapper;

    @Autowired
    private SysUsersRolesMapper sysUsersRolesMapper;

    @Autowired
    private SysRolesMapper sysRolesMapper;

    @Override
    @Transactional
    public SysUsers createSysUsers(SysUsers sysUsers) {
        PasswordHelper.encryptPassword(sysUsers);
        sysUsersMapper.insert(sysUsers);
        return sysUsers;
    }

    @Override
    @Transactional
    public void changePassword(Long sysUsersId, String newPassword) {
        SysUsers user = sysUsersMapper.selectByPrimaryKey(sysUsersId);
        user.setPassword(newPassword);
        PasswordHelper.encryptPassword(user);
        sysUsersMapper.updateByPrimaryKey(user);
    }

    @Override
    @Transactional
    public void correlationRoles(Long sysUsersId, Long... roleIds) {
        SysUsersRolesKey usersRolesKey = new SysUsersRolesKey();
        usersRolesKey.setUserId(sysUsersId);

        for (Long roleid : roleIds){
            usersRolesKey.setRoleId(roleid);
            sysUsersRolesMapper.insert(usersRolesKey);
        }
    }

    @Override
    @Transactional
    public void uncorrelationRoles(Long sysUsersId, Long... roleIds) {
        SysUsersRolesKey usersRolesKey = new SysUsersRolesKey();
        usersRolesKey.setUserId(sysUsersId);

        SysUsersRolesExample example = new SysUsersRolesExample();
        example.createCriteria().andUserIdEqualTo(sysUsersId).andRoleIdIn(Arrays.asList(roleIds));
    }

    @Override
    public SysUsers findBySysUsersname(String sysUsersname) {
        SysUsersExample example = new SysUsersExample();
        example.createCriteria().andUsernameEqualTo(sysUsersname);

        List<SysUsers> users = sysUsersMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(users)){
            return users.get(0);
        }

        return null;
    }

    @Override
    public Set<String> findRoles(String sysUsersname) {
        SysUsers user = findBySysUsersname(sysUsersname);

        if(user != null){
            SysUsersRolesExample example = new SysUsersRolesExample();
            example.createCriteria().andUserIdEqualTo(user.getId());

            List<SysUsersRolesKey> userRoles = sysUsersRolesMapper.selectByExample(example);

            if(!CollectionUtils.isEmpty(userRoles)){
                List<Long> roleids = new ArrayList<>(userRoles.size());

                for (SysUsersRolesKey key : userRoles){
                    roleids.add(key.getRoleId());
                }

                SysRolesExample rolesExample = new SysRolesExample();
                rolesExample.createCriteria().andIdIn(roleids);

                List<SysRoles> sysRolesList = sysRolesMapper.selectByExample(rolesExample);

                if(!CollectionUtils.isEmpty(sysRolesList)){
                    Set<String> roleNames = new HashSet<String>();

                    for (SysRoles role : sysRolesList){
                        roleNames.add(role.getRole());
                    }

                    return roleNames;
                }
            }
        }
        return null;
    }

    @Override
    public Set<String> findPermissions(String sysUsersname) {
        List<SysPermissions> permissions = sysUsersMapper.selectPermissionByUserName(sysUsersname);

        if (!CollectionUtils.isEmpty(permissions)){
            Set<String> permissionNames = new HashSet<>();

            for (SysPermissions p : permissions){
                permissionNames.add(p.getPermission());
            }
            return permissionNames;
        }
        return null;
    }
}
