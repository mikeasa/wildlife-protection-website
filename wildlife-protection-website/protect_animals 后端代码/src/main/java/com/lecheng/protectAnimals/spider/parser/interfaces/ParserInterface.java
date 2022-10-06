package com.lecheng.protectAnimals.spider.parser.interfaces;



import com.lecheng.protectAnimals.pojo.ParserResult;

import java.text.ParseException;
import java.util.List;

public interface ParserInterface {
    /**
     * 解析HtmlSource接口
     */

    //爬取中国野生动物保护协会 http://www.forestry.gov.cn/bhxh/651/index.html
    public List<ParserResult> CWPAParserHtml(String htmlSource) throws ParseException;
    //爬取中国野生动物保护协会 http://www.cwca.org.cn/news/tidings/
    public List<ParserResult> CWPA2ParserHtml(String htmlSource) throws ParseException;
}
