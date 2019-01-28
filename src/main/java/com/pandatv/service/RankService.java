package com.pandatv.service;

import com.pandatv.service.model.RankModel;

import java.util.List;

public interface RankService {
    List<RankModel> getRank(String key, long start, long end);


}
