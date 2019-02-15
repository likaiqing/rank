package com.pandatv.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: likaiqing
 * @create: 2019-01-28 13:02
 **/
@Data
public class InfoModel {
    private String rid;
    private String roomId;
    private String avatar;
    private String nickName;
}
