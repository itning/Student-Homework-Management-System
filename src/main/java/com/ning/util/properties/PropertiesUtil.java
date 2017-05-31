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
 * Created by wangn on 2017/5/25.
 */
public class PropertiesUtil {
    public static String getUpLoadFilePath() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(request.getSession().getServletContext().getRealPath("/WEB-INF/classes/context.properties"))));
        } catch (IOException e) {
            throw new FileException("获取文件路径失败！"+e.getMessage());
        }
        String filepath = properties.getProperty("upLoadFilePath");
        if(filepath==null||filepath.equals("")){
            throw new FileException("文件路径不正确：upLoadFilePath="+filepath);
        }
        return filepath;
    }
}
