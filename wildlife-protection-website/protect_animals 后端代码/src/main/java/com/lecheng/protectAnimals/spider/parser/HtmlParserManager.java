package com.lecheng.protectAnimals.spider.parser;



import com.lecheng.protectAnimals.pojo.ParserResult;
import com.lecheng.protectAnimals.spider.parser.impl.RegexParser;
import com.lecheng.protectAnimals.spider.parser.interfaces.ParserInterface;

import java.text.ParseException;
import java.util.List;

/**
 * 解析HtmlSource
 */
public class HtmlParserManager {
    //调用htmlSource解析接口
    private static ParserInterface parserInterface=new RegexParser();

    //爬取中国野生动物保护协会 http://www.forestry.gov.cn/bhxh/651/index.html
    public static List<ParserResult> CWPAParserHtml(String htmlSource) throws ParseException {
        return parserInterface.CWPAParserHtml(htmlSource);
    }
    //爬取中国野生动物保护协会 http://www.cwca.org.cn/news/tidings/
    public static List<ParserResult> CWPA2ParserHtml(String htmlSource) throws ParseException {
        return parserInterface.CWPA2ParserHtml(htmlSource);
    }
}
