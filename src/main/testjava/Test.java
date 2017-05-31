import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by wangn on 2017/5/22.
 */
public class Test {
    @org.junit.Test
    public void test1() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        System.out.println("格式化后的日期：" + dateNowStr);
    }

    @org.junit.Test
    public void test2() {
        String test = "2017051.6202630.png";
        String prefix = test.substring(test.lastIndexOf(".") + 1);
        System.out.println(prefix);
    }

    @org.junit.Test
    public void test3() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replace("-", ""));
    }

    private int k = 1; // 定义递归次数变量

    private void zip(String zipFileName, File inputFile) throws Exception {
        System.out.println("压缩中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, inputFile, inputFile.getName(), bo);
        bo.close();
        out.close(); // 输出流关闭
        System.out.println("压缩完成");
    }

    private void zip(ZipOutputStream out, File f, String base,
                     BufferedOutputStream bo) throws Exception { // 方法重载
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (fl.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
                System.out.println(base + "/");
            }
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
            }
            System.out.println("第" + k + "次递归");
            k++;
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
            System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bi.close();
            in.close(); // 输入流关闭
        }
    }

    @org.junit.Test
    public void test4() throws Exception {
        List<String> filesname=new ArrayList<String>();
        filesname.add("201601010317C++第二次作业.png");
        filesname.add("201601010317C++第一次试验作业.docx");
        //ZipFile("d:/hello.txt", "d:/hello.zip");
        File zipFile = new File("C:/Users/wangn/Desktop/test.zip");
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        for (String filename:filesname){
            File file = new File("C:/Users/wangn/Desktop/upfile/"+filename);
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(file.getName()));
            IOUtils.copy(input, zipOut);
            input.close();
        }
        zipOut.close();
    }

    public static void ZipFile(String filepath ,String zippath) {

    }

    @org.junit.Test
    public void test5(){
        String a="1";
        System.out.println(Boolean.parseBoolean(a));
    }
}
