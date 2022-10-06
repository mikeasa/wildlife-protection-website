package com.lecheng.protectAnimals.dao;

import com.lecheng.protectAnimals.pojo.Search;

import java.util.List;

public interface SearchDao {
    List<String> getCurrentSearches(Integer userid);
    List<String> getHotSearches();
    int addSearch(Search search);
}
