package com.pandatv.service;

import com.pandatv.service.model.InfoModel;

import java.io.IOException;

/**
 * @author: likaiqing
 * @create: 2019-01-28 13:03
 **/
public interface InfoService {
    InfoModel getSetInfo(String rid);
}
