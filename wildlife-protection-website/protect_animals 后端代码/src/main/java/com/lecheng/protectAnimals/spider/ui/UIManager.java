package com.lecheng.protectAnimals.spider.ui;



import com.lecheng.protectAnimals.spider.util.FileOperatorUtil;

import java.util.List;

/**
 * 负责爬虫系统对外接口的实现
 */
public class UIManager {
    /**
     * 拿到URL
     * @return
     */
    public static String getSeedUrl(){
        return "http://www.forestry.gov.cn/bhxh/651/index.html";
    }
    //爬取中国野生动物保护协会 http://www.forestry.gov.cn/bhxh/651/index.html
    public static List<String> getSeedUrlFromFile() throws Exception {
        String filePath="E:\\backup\\protect_animals\\file\\seeds.txt";
        List<String> fileLineList = FileOperatorUtil.getFileLineList(filePath);
        return fileLineList;
    }
    //爬取中国野生动物保护协会 http://www.cwca.org.cn/news/tidings/
    public static List<String> getSeed2UrlFromFile() throws Exception {
        String filePath="E:\\backup\\protect_animals\\file\\seeds2.txt";
        List<String> fileLineList = FileOperatorUtil.getFileLineList(filePath);
        return fileLineList;
    }


    public static List<String> getSeedUrlFromFile(String filePath) throws Exception {
//        String filePath="http://localhost:8081/ludu_picture_war_exploded/file/seeds.txt";
        List<String> fileLineList = FileOperatorUtil.getFileLineListFromOtherTomcat(filePath);
//        List<String> fileLineList = FileOperatorUtil.getFileLineList(filePath);
        return fileLineList;
    }
}
