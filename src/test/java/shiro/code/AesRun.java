package shiro.code;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

/**
 * Created by sunjx on 2018/2/28.
 */
public class AesRun {

    @Test
    public void main(){
        final int keySize = 128;
        final String source = "hello";

        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(keySize); //设置key长度

        //生成key
        Key key = aesCipherService.generateNewKey();

        //加密
        String encrptText = aesCipherService.encrypt(source.getBytes(), key.getEncoded()).toHex();

        //解密
        String decryptText = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(source, decryptText);
    }
}
