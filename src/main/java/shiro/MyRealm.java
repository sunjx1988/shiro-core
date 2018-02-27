package shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by sunjx on 2018/2/27.
 */
public class MyRealm implements Realm{

    private static final String USER_NAME = "sunjx";
    private static final String PASS_WORD = "000000";

    @Override
    public String getName() {
        return "myrealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        if(!userName.equals(USER_NAME)){
            throw new UnknownAccountException();
        }

        if(!password.equals(PASS_WORD)){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
