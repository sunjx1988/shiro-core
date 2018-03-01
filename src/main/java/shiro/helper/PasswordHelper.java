package shiro.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import shiro.entity.SysUsers;

/**
 * Created by sunjx on 2018/2/28.
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private Integer hashIterations = 2;

    public PasswordHelper(String algorithmName, Integer hashIterations) {
        this.algorithmName = algorithmName;
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(SysUsers user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toString();

        user.setPassword(newPassword);
    }

}
