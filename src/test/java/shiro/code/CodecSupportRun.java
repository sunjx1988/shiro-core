package shiro.code;

import org.apache.shiro.codec.CodecSupport;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sunjx on 2018/2/28.
 */
public class CodecSupportRun {

    @Test
    public void main(){
        String string = "CodecSupport";
        String charSet = "utf-8";

        //如果不指定字符编码，将使用系统默认编码
        byte[] bytes = CodecSupport.toBytes(string,charSet);
        Assert.assertEquals(string,CodecSupport.toString(bytes,charSet));
    }
}
