package shiro.code;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

/**
 * Created by sunjx on 2018/2/28.
 */
public class HashServiceRun {

    @Test
    public void main(){
        final String source = "hello";
        final String algorithm = "SHA-512";
        final String privateSalt = "123";
        final int iterations = 2;

        //这里可以看做是默认设置
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName(algorithm);
        hashService.setPrivateSalt(new SimpleByteSource(privateSalt)); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(iterations); //生成Hash值的迭代次数

        //使用默认设置
        System.out.println(hashService.computeHash(new HashRequest.Builder().setSource(ByteSource.Util.bytes(source)).build()));

        //这里可以修改算法，盐，hash次数
        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5")
                .setSource(ByteSource.Util.bytes(source))
                .setSalt(ByteSource.Util.bytes("456"))
                .setIterations(3)
                .build();

        //使用修改设置
        System.out.println(hashService.computeHash(request));

    }
}
