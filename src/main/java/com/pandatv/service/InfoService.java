package com.pandatv.service;

import com.pandatv.service.model.InfoModel;

import java.util.List;

/**
 * @author: likaiqing
 * @create: 2019-01-28 13:03
 **/
public interface InfoService {
    InfoModel getSetInfo(String rid);

    List<String> nickNameByUids(List<String> uids);
}
