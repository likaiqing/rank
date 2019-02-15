package com.pandatv.service.impl;

import com.pandatv.service.RankService;
import com.pandatv.service.model.RankModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: likaiqing
 * @create: 2019-01-28 12:33
 **/
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<RankModel> getRank(String key, long start, long end) {
        Set<ZSetOperations.TypedTuple> set = redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
        List<RankModel> list = new ArrayList<>();
        for (ZSetOperations.TypedTuple tuple : set) {
            list.add(new RankModel(tuple.getValue().toString(), tuple.getScore()));
        }
        return list;
    }

    @Override
    public List<String> getRankFirMap(String key, List<String> fields) {
        List<String> u2qUids = redisTemplate.opsForHash().multiGet(key, fields);
        return u2qUids;
    }

}
