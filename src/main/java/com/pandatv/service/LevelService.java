package com.pandatv.service;

import java.util.List;
import java.util.Map;

/**
 * @author: likaiqing
 * @create: 2019-01-28 15:49
 **/
public interface LevelService {
    Map<String, Integer> getLevelMap(List<String> rids);

}
