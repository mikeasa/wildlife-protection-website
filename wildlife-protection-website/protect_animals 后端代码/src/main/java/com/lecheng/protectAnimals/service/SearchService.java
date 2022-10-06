package com.lecheng.protectAnimals.service;

import com.lecheng.protectAnimals.pojo.Search;

import java.util.List;

public interface SearchService {
    List<String> getCurrentSearches(Integer userid);
    List<String> getHotSearches();
    int addSearch(Search search);
}
