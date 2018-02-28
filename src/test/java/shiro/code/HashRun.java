package shiro.code;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * Created by sunjx on 2018/2/28.
 */
public class HashRun {

    @Test
    public void md5Main(){
        String string = "md5";
        String slat = "slat";

        //第三个参数表示hash的次数
        String md5 = new Md5Hash(string , slat , 2).toString();
        System.out.println(md5);
    }
}
