package com.lecheng.protectAnimals.spider.util;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件中种子
 */
public class FileOperatorUtil {
    public static List<String> getFileLineList(String filePath) throws Exception {
        File file = new File(filePath);
        InputStreamReader isr=new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);
        ArrayList<String> linList = new ArrayList<String>();
        String temp=null;
        while ((temp=br.readLine())!=null){
          temp=temp.trim();
          if (temp.length()>0){
              linList.add(temp);
          }
        }
        br.close();
        return linList;
    }
    //另一台服务器
    public static List<String> getFileLineListFromOtherTomcat(String filePath) throws Exception {
        List<String> fileLineList= new ArrayList<String>();
        URL urlObj = new URL(filePath);
        InputStream inputStream=urlObj.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
        BufferedReader br = new BufferedReader(inputStreamReader);
        String temp=null;
        while ((temp=br.readLine())!=null){
            fileLineList.add(temp);
        }
        br.close();
        return fileLineList;
    }
}
