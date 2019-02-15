package com.pandatv.service;

import com.pandatv.service.model.RankModel;

import java.util.List;
import java.util.Map;

public interface RankService {
    List<RankModel> getRank(String key, long start, long end);

    List<String> getRankFirMap(String key, List<String> fields);

}
