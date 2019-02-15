package com.pandatv.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandatv.conf.Conf;
import com.pandatv.service.LevelService;
import com.pandatv.tools.HttpTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: likaiqing
 * @create: 2019-01-28 15:50
 **/
@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    HttpTools httpTools;

    @Override
    public Map<String, Integer> getLevelMap(List<String> rids) {
        Map<String, Integer> levelMap = new HashMap<>();
        List<String> keys = rids.stream().map(rid -> Conf.levelKeyPre + rid).collect(Collectors.toList());
        List<String> levels = stringRedisTemplate.opsForValue().multiGet(keys);
        ObjectMapper mapper = null;
        for (int i = 0; i < levels.size(); i++) {
            String rid = keys.get(i).substring(keys.get(i).lastIndexOf(":") + 1);
            if (StringUtils.isNotEmpty(levels.get(i))) {
                levelMap.put(rid, Integer.parseInt(levels.get(i)));
            } else {
                if (null == mapper) {
                    mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                }
                String levelInfo = null;
                try {
                    levelInfo = httpTools.httpGet(new StringBuffer(Conf.levelUrlPre).append(rid).toString());
                    String level = mapper.readTree(levelInfo).get("data").get(rid).get("level").asText();
                    levelMap.put(rid, Integer.parseInt(level));
                    stringRedisTemplate.opsForValue().set(keys.get(i), level);
                    stringRedisTemplate.expire(keys.get(i), 40, TimeUnit.DAYS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return levelMap;
    }
}
