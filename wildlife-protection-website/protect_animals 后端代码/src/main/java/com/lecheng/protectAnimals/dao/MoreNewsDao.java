package com.lecheng.protectAnimals.dao;


import com.lecheng.protectAnimals.pojo.ParserResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MoreNewsDao {
    int addMoreNews(List<ParserResult> list);
    List<ParserResult> getParserResultByLimit();
    int truncateTable(@Param("tableName") String tableName);
}
