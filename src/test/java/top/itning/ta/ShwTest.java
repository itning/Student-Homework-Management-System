package top.itning.ta;


import com.ning.util.email.EmailUtils;
import org.junit.Test;

import javax.mail.MessagingException;


public class ShwTest {


    @Test
    public void testMail() throws MessagingException {
        EmailUtils.sendEmail("1157830249@qq.com", "主题内容", "test");
    }
}
