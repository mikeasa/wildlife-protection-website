package com.lecheng.protectAnimals.spider.download;


import com.lecheng.protectAnimals.pojo.UrlTask;
import com.lecheng.protectAnimals.spider.schedule.ScheduleManager;
import com.lecheng.protectAnimals.utils.Common;

/**
 * 下载HTMLSOURCE管理器
 */
public class DownLoadManager {
    public DownLoadManager() {
    }
    public static String downloadHtmlSource() throws Exception {
        UrlTask task = ScheduleManager.getUrlTaskFromTodoTTaskList();
        if (task != null){
            return WebPageDownLoad.getHtmlSourceByUrl(task.getUrl(), Common.DEFAULT_ENCODING);
        }
        return null;
    }

//    public static void main(String[] args) throws Exception {
//        List<String> seedUrlFromFile = UIManager.getSeed2UrlFromFile();
//        ScheduleManager.addSeedUrlToTaskList(seedUrlFromFile);
//        String s = DownLoadManager.downloadHtmlSource();
//        List<ParserResult> parserResults = HtmlParserManager.CWPA2ParserHtml(s);
//        System.out.println(parserResults.size());
//        System.out.println(parserResults.toString());
//        System.out.println("done");
//    }
}
