package com.lecheng.protectAnimals.controller;


import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.pojo.ParserResult;
import com.lecheng.protectAnimals.pojo.ResponseMessage;
import com.lecheng.protectAnimals.service.MoreNewsService;
import com.lecheng.protectAnimals.spider.download.DownLoadManager;
import com.lecheng.protectAnimals.spider.parser.HtmlParserManager;
import com.lecheng.protectAnimals.spider.schedule.ScheduleManager;
import com.lecheng.protectAnimals.spider.ui.UIManager;
import com.lecheng.protectAnimals.utils.Common;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moreNews")
public class MoreNewsController {
    /**
     * 日志打印
     */
    private static Logger log = Logger.getLogger(MoreNewsController.class);
    @Autowired
    private MoreNewsService moreNewsService;
    /**
     * 控制爬虫系统开关
     */
    private static Boolean isOpen=false;

    @RequestMapping("/getMoreNewsByLimit")
    public ResponseMessage getMoreNewsByLimit(int page){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            PageInfo<ParserResult> result =  moreNewsService.getParserResult(page, 13);
            responseMessage.setData(result);
            responseMessage.setMsg("获取成功");
            responseMessage.setCode(200);
            return responseMessage;
        } catch (Exception e) {
            return responseMessage.False();
        }

    }

    /**
     * 拉取更多新闻接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/managerMoreNews")
    public ResponseMessage toAddMoreNews(Boolean toIsOpen) throws Exception {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        log.info(toIsOpen.toString());
        try {
            isOpen=toIsOpen;
            timingSpider();
        } catch (Exception e) {
            log.error("爬虫系统出错！");
            return responseMessage.False();
        }
        responseMessage.setMsg("已退出spider系统");
        responseMessage.setCode(200);
        return responseMessage;
    }

    /**
     * 设置爬虫系统定时爬取，数据库信息自动更新
     * @throws Exception
     */
    public void timingSpider() throws Exception {
        if (isOpen == false){
            log.info("已关闭爬虫系统！");
        }
        while (isOpen){
            moreNewsService.truncateTable("morenews");
            log.info("spider开始运行");
            log.info("拉取http://www.forestry.gov.cn");
            addMoreNews();
            log.info("拉取http://www.cwca.org.cn");
            addMoreNews2();
            log.info("本次采集结束，spider开始睡眠1小时");
            Thread.sleep(3600000);//睡眠1h
//            Thread.sleep(5000);//睡眠
        }
    }


    /**
     * 拉取中国野生动物保护协会http://www.forestry.gov.cn的最新文字新闻
     * @return
     * @throws Exception
     */
    public void addMoreNews() throws Exception {
        //拿到url
//        String seedUrl= UIManager.getSeedUrl();
        List<String> seedUrls =
                UIManager.getSeedUrlFromFile("http://47.108.95.77/protect_animals/file/seeds.txt");
        //交给任务调度层用于之后的分发
        ScheduleManager.addSeedUrlToTaskList(seedUrls);
        //下载拿到的HtmlSource
        String htmlSource= DownLoadManager.downloadHtmlSource();
        while (htmlSource != null){
            //解析下载下来的HtmlSource
            List<ParserResult> parserResults = HtmlParserManager.CWPAParserHtml(htmlSource);
            //解析下来的数据进行持久化
            try {
                moreNewsService.addMoreNews(parserResults);
            } catch (Exception e) {
                log.error("本轮采集出错！");
            }
            log.info("本轮采集完成！");
            //循环下载到没有任务队列为空
            htmlSource=DownLoadManager.downloadHtmlSource();
        }
        ScheduleManager.todoTaskList.clear();
        ScheduleManager.todoTaskSet.clear();
    }

    /**
     * 拉取中国野生动物保护协会http://www.cwca.org.cn/的最新文字新闻
     * @return
     * @throws Exception
     */
    public void addMoreNews2() throws Exception {
        //拿到url
//        String seedUrl= UIManager.getSeedUrl();
        List<String> seedUrls =
                UIManager.getSeedUrlFromFile("http://47.108.95.77/protect_animals/file/seeds2.txt");
        //交给任务调度层用于之后的分发
        ScheduleManager.addSeedUrlToTaskList(seedUrls);
        //下载拿到的HtmlSource
        String htmlSource= DownLoadManager.downloadHtmlSource();
        while (htmlSource != null){
            //解析下载下来的HtmlSource
            List<ParserResult> parserResults = HtmlParserManager.CWPA2ParserHtml(htmlSource);
            //解析下来的数据进行持久化
            try {
                moreNewsService.addMoreNews(parserResults);
            } catch (Exception e) {
                log.error("本轮采集出错！");
            }
            log.info("本轮采集完成！");
            //循环下载到没有任务队列为空
            htmlSource=DownLoadManager.downloadHtmlSource();
        }
        ScheduleManager.todoTaskList.clear();
        ScheduleManager.todoTaskSet.clear();
    }
}
