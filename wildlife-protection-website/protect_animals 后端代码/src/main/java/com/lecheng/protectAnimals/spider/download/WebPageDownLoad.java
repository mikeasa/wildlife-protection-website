package com.lecheng.protectAnimals.spider.download;



import com.lecheng.protectAnimals.spider.util.CharsetDetectorUtil;
import com.lecheng.protectAnimals.utils.Common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WebPageDownLoad {
    /**
     * 打开并获取inputStream流
     * @param url
     * @return
     * @throws Exception
     */
    public static InputStream getInputStream(String url) throws Exception {
        URL urlObj = new URL(url);
        InputStream inputStream=urlObj.openStream();
        return inputStream;
    }

    /**
     * 设置编码，得到缓冲区读取
     * @param inputStream
     * @param charset
     * @return
     * @throws Exception
     */
    public static BufferedReader getBR(InputStream inputStream,String charset) throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        BufferedReader br = new BufferedReader(inputStreamReader);
        return br;
    }

    /**
     * 对BufferedReader getBR（）封装，以此通过url获取到缓冲区读取
     * @param url
     * @param charset
     * @return
     * @throws Exception
     */
    public static BufferedReader getBR(String url,String charset) throws Exception {
        InputStream inputStream = getInputStream(url);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        BufferedReader br = new BufferedReader(inputStreamReader);
        return br;
    }

    /**
     * 获取HtmLSource
     * @param url
     * @param charset
     * @return
     * @throws Exception
     */
    public static String getHtmlSourceByUrl(String url,String charset) throws Exception {
        String detectCharset= CharsetDetectorUtil.getCharset (url);
        if (detectCharset!=null){
            charset=detectCharset;
        }
        InputStream inputStream = getInputStream(url);
        BufferedReader br = getBR(inputStream, charset);
        StringBuilder stringBuilder = new StringBuilder();
        String temp=null;
        int lineCounter=0;
        while ((temp=br.readLine())!=null){
            if (lineCounter>0){
                stringBuilder.append(Common.NEXT_LINE);
            }
            lineCounter++;
            stringBuilder.append(temp);
        }
        br.close();
        return stringBuilder.toString();
    }

}
