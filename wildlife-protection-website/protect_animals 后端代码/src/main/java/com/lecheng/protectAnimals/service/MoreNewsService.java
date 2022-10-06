package com.lecheng.protectAnimals.service;



import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.pojo.ParserResult;

import java.util.List;

public interface MoreNewsService {
    int addMoreNews(List<ParserResult> list);
    int truncateTable(String tableName);
    PageInfo<ParserResult> getParserResult(Integer page, Integer rows);
}
