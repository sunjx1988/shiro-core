package shiro.component;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shiro.entity.SysUsers;
import shiro.service.UserService;

/**
 * Created by sunjx on 2018/3/1.
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String userName = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(userName));
        authorizationInfo.setStringPermissions(userService.findPermissions(userName));

        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();
        String passWord = new String((char[]) authenticationToken.getCredentials());

        SysUsers user = userService.findBySysUsersname(userName);

        //账号或密码不存在
        if(user == null){
            LOGGER.error("账号或密码不存在:"+userName);
            throw new UnknownAccountException();
        }

        //账号被锁定
        if(user.getLocked()){
            LOGGER.error("账号被锁定:"+userName);
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(userName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
