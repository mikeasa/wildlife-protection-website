package com.lecheng.protectAnimals.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLUtils {
    /**
     * Get请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        try {
            URL u = new URL(url);
            String res = inputStreamToString(u.openStream());
            return res;
        } catch (Exception e) {
            System.out.println(url + "：get请求出错");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * InputStream 转 String
     * 经测试，代理成功
     * @param inputStream
     */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int read = 0;
        while ((read = inputStream.read(buf)) > 0) {
            out.write(buf, 0, read);
        }
        //可设置gbk编码
        String res = new String(out.toByteArray(), "utf-8");
        out.close();
        return res;
    }

}
