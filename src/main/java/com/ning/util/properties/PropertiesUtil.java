package com.ning.util.properties;

import com.ning.exception.file.FileException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性工具类
 *
 * @author wangn
 * @date 2017/5/25
 */
public class PropertiesUtil {
    public static String getUpLoadFilePath() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(request.getSession().getServletContext().getRealPath("/WEB-INF/classes/context.properties"))));
        } catch (IOException e) {
            throw new FileException("获取文件路径失败！" + e.getMessage());
        }
        String filepath = properties.getProperty("upLoadFilePath");
        if (filepath == null || "".equals(filepath)) {
            throw new FileException("文件路径不正确：upLoadFilePath=" + filepath);
        }
        return filepath;
    }

    public static String filterOutUrl(String teststr) throws Exception{
        if (teststr.charAt(0) == '[' && teststr.charAt(teststr.length() - 1) == ')'){
            int count = 0;
            count += teststr.chars().filter(ch -> ch == '[').count();
            count += teststr.chars().filter(ch -> ch == ']').count();
            count += teststr.chars().filter(ch -> ch == '(').count();
            count += teststr.chars().filter(ch -> ch == ')').count();

            if (count == 4) {
                String ptext = teststr.substring(teststr.lastIndexOf('[') + 1, teststr.lastIndexOf(']'));
                return ptext;
            }
        }

        return  teststr;
    }
}
