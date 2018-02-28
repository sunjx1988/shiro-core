package shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shiro.entity.SysUsers;
import shiro.helper.PasswordHelper;
import shiro.mapper.SysUsersMapper;
import shiro.service.UserService;

import java.util.Set;

/**
 * Created by sunjx on 2018/2/28.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    @Transactional
    public SysUsers createSysUsers(SysUsers sysUsers) {
        PasswordHelper.encryptPassword(sysUsers);
        sysUsersMapper.insert(sysUsers);
        return sysUsers;
    }

    @Override
    public void changePassword(Long sysUsersId, String newPassword) {

    }

    @Override
    public void correlationRoles(Long sysUsersId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long sysUsersId, Long... roleIds) {

    }

    @Override
    public SysUsers findBySysUsersname(String sysUsersname) {
        return null;
    }

    @Override
    public Set<String> findRoles(String sysUsersname) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String sysUsersname) {
        return null;
    }
}
