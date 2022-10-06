package com.lecheng.protectAnimals.spider.util;



import com.lecheng.protectAnimals.spider.download.WebPageDownLoad;
import com.lecheng.protectAnimals.utils.Common;

import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class CharsetDetectorUtil {
    /**
     *Html编码探测，取得编码
     */
    public static String getCharset (String url) throws Exception {
        String finalCharset=null;
        URL urlObj = new URL(url);
        URLConnection urlConnection = urlObj.openConnection();
        //用header来获取url的charset
        Map<String, List<String>> allHeaderMap = urlConnection.getHeaderFields();
        List<String> kvList = allHeaderMap.get("Content-Type");
        if (kvList != null &&!kvList.isEmpty()){
            String line=kvList.get(0);
            String[] kvArray = line.split(";");
            for (String kv:kvArray
                 ) {
                kv.trim();
                String[] eleMap=kv.split ("=");
                if (eleMap.length==2){
                    if (eleMap[0].equals("charset")){
                        finalCharset = eleMap[1].trim();
                    }
                }
            }
        }
        if (finalCharset==null){
            BufferedReader br= WebPageDownLoad.getBR (url, Common.DEFAULT_ENCODING);
            String temp=null;
            while ((temp=br.readLine())!=null){
                temp.toLowerCase();
                String charset4Line = getCharset4Line(temp);
                if (charset4Line!=null){
                    finalCharset=charset4Line;
                    break;
                }
                if (temp.contains("</head>")){
                    break;
                }
            }
            br.close();
        }

        return finalCharset;
    }

    /**
     * 进行正则匹配取出charset
     * @param line
     * @return
     */
    public static String getCharset4Line(String line){
        String regex="charset=\"?(.+?)\"?\\s?/?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        String charsetValue = null;
        if (matcher.find()){
            charsetValue=matcher.group(1);
        }
        return charsetValue;
    }

}
