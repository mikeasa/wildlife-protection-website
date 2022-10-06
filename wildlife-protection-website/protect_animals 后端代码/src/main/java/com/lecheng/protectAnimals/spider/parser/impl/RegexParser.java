package com.lecheng.protectAnimals.spider.parser.impl;



import com.lecheng.protectAnimals.pojo.ParserResult;
import com.lecheng.protectAnimals.spider.parser.interfaces.ParserInterface;
import com.lecheng.protectAnimals.utils.Common;
import com.lecheng.protectAnimals.utils.DateUtil;
import com.lecheng.protectAnimals.utils.RegexUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegexParser implements ParserInterface {
    /**
     * 解析HtmlSource实现
     */


    public List<ParserResult> CWPAParserHtml(String htmlSource) throws ParseException {
        List<ParserResult> parserResults = new ArrayList<>();
        String regexLls="<li\\sclass=\"cl\"[\\s\\S]*</li>";
        String LisText = RegexUtil.getRegexTextResult(htmlSource, regexLls, 0);
        String regexLl="<li\\sclass=\"cl\"([\\s\\S])*?</li>";
        List<String> LiText = RegexUtil.getRegexTextResults(LisText, regexLl,0);
        for (String text:LiText
        ) {
            ParserResult parserResult = new ParserResult();
            //        解析标题
            String regexTitle="<a[\\s\\S]*?>([\\s\\S]*?)</a>";
            String regexTitleResult = RegexUtil.getRegexTextResult(text, regexTitle, 1);
            parserResult.setTitle(regexTitleResult);
            //        解析来源
            String regexSourceUrl="href=\"([\\s\\S]*?)\"";
            String regexSourceUrlResult = RegexUtil.getRegexTextResult(text, regexSourceUrl, 1);
            parserResult.setUrl(Common.ROOT_URL+regexSourceUrlResult);

            //      解析发布时间
            String regexPostTime="<span[\\s\\S]*?>([\\s\\S]*?)</span>";
            Date postDate= DateUtil.stringToFormatDate(RegexUtil.getRegexTextResult(text,regexPostTime,1));
            parserResult.setPostTime(postDate);

            //     设置插入数据库时间
            parserResult.setInsertTime(DateUtil.getCurrentTime());

            parserResults.add(parserResult);
        }

        return parserResults;
    }


    public List<ParserResult> CWPA2ParserHtml(String htmlSource) throws ParseException {
        ArrayList<ParserResult> parserResults = new ArrayList<ParserResult>();
        String regexUl="<li>([\\s\\S]*?)</li>";
        List<String> regexLiTextResults = RegexUtil.getRegexTextResults(htmlSource, regexUl, 1);
        for (String text:regexLiTextResults
             ) {
            ParserResult parserResult = new ParserResult();
            //        解析标题
            String regexTitle="<a[\\s\\S]*?>([\\s\\S]*?)</a>";
           String TitleTextResult = RegexUtil.getRegexTextResult(text, regexTitle, 1);
            parserResult.setTitle(TitleTextResult);
            //        解析来源
            String regexSourceUrl="href=\"([\\s\\S]*?)\"";
            String regexSourceUrlResult = RegexUtil.getRegexTextResult(text, regexSourceUrl, 1);
            parserResult.setUrl(Common.ROOT_URL2+regexSourceUrlResult);

            //      解析发布时间
            String regexPostTime="<span>\\[([\\s\\S]*?)\\]</span>";
            String regexPostTimeResult = RegexUtil.getRegexTextResult(text, regexPostTime, 1);
            Date postDate= DateUtil.stringToFormatDate(regexPostTimeResult);
            parserResult.setPostTime(postDate);

            //     设置插入数据库时间
            parserResult.setInsertTime(DateUtil.getCurrentTime());

            parserResults.add(parserResult);
        }
        return parserResults;
    }
}
