package shiro.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import shiro.entity.SysUsers;

/**
 * Created by sunjx on 2018/2/28.
 */
public abstract class PasswordHelper {
    private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static final String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static void encryptPassword(SysUsers user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
