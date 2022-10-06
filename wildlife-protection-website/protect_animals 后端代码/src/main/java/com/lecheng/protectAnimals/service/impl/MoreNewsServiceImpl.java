package com.lecheng.protectAnimals.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.dao.MoreNewsDao;
import com.lecheng.protectAnimals.pojo.ParserResult;
import com.lecheng.protectAnimals.pojo.VolunteerActivityEntrance;
import com.lecheng.protectAnimals.service.MoreNewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MoreNewsServiceImpl implements MoreNewsService {
    @Autowired
    private MoreNewsDao moreNewsDao;
    //日志打印
    private static Logger logger = Logger.getLogger(MoreNewsServiceImpl.class);
    @Override
    public int addMoreNews(List<ParserResult> list) {
        try {
            return moreNewsDao.addMoreNews(list);
        } catch (Exception e) {
            logger.error("插入MoreNews失败");
            return -1;
        }
    }


    @Override
    public int truncateTable(String tableName) {
        try {
            return moreNewsDao.truncateTable(tableName);
        } catch (Exception e) {
            logger.error("清除表数据失败");
            return -1;
        }
    }

    @Override
    public PageInfo<ParserResult> getParserResult(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<ParserResult> result = moreNewsDao.getParserResultByLimit();
        PageInfo<ParserResult> pageInfo=new PageInfo(result);
        return pageInfo;
    }

}
