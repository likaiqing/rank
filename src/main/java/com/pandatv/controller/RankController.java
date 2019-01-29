package com.pandatv.controller;

import com.pandatv.controller.viewobject.RankVO;
import com.pandatv.response.CommonReturnType;
import com.pandatv.service.InfoService;
import com.pandatv.service.LevelService;
import com.pandatv.service.RankService;
import com.pandatv.service.model.InfoModel;
import com.pandatv.service.model.RankModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: likaiqing
 * @create: 2019-01-24 19:10
 **/
@Controller(value = "/rank")
@RequestMapping("/rank")
public class RankController {

    @Autowired
    RankService rankService;

    @Autowired
    InfoService infoService;

    @Autowired
    LevelService levelService;

    //    @GetMapping("/rank")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType rank(@RequestParam(name = "key") String key,
                                 @RequestParam(name = "start", required = false, defaultValue = "0") long start,
                                 @RequestParam(name = "end", required = false, defaultValue = "110") long end,
                                 @RequestParam(name = "needLevel", required = false, defaultValue = "false") boolean needLevel,
                                 @RequestParam(name = "needInfo", required = false, defaultValue = "false") boolean needInfo) {

        List<RankModel> rank = rankService.getRank(key, start, end);
        List<RankVO> ranks = new ArrayList<>();
        for (RankModel model : rank) {
            RankVO rankVO = convertVOFromModel(model, needInfo);
            ranks.add(rankVO);
        }
        if (needLevel) {
            List<String> rids = ranks.stream().filter(r -> r != null).map(r -> r.getRid()).collect(Collectors.toList());
            Map<String, Integer> levelMap = levelService.getLevelMap(rids);
            for (int i = 0; i < ranks.size(); i++) {
                RankVO rankVO = ranks.get(i);
                if (rankVO == null) {
                    continue;
                }
                if (levelMap.containsKey(rankVO.getRid())) {
                    rankVO.setLevel(levelMap.get(rankVO.getRid()));
                }
            }
        }
        return CommonReturnType.create(ranks);
    }

    /**
     * 置顶key榜单置顶名次之内对应的第一名用户昵称
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/u2q", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType u2qWithAncKey(@RequestParam(name = "key") String key,
                                          @RequestParam(name = "start", required = false, defaultValue = "0") long start,
                                          @RequestParam(name = "end", required = false, defaultValue = "110") long end) {
        List<RankModel> ranks = rankService.getRank(key, start, end);
        List<String> qids = ranks.stream().map(r -> r.getRid()).collect(Collectors.toList());
        String mapKey = key.replace("anc", "u2q").replace(":rank", ":map").replace(":signUp:", ":");
        List<String> uids = rankService.getRankFirMap(mapKey, qids);
        List<String> uidsNickNames = infoService.nickNameByUids(uids);
        Map<String, String> qidNicknameMap = new HashMap<>();
        for (int i = 0; i < qids.size(); i++) {
            String qid = qids.get(i);
            String nickName = uidsNickNames.get(i);
            qidNicknameMap.put(qid, nickName);
        }
        return CommonReturnType.create(qidNicknameMap);
    }

    private RankVO convertVOFromModel(RankModel model, boolean needInfo) {
        if (model == null) {
            return null;
        }
        RankVO rankVO = new RankVO();
        BeanUtils.copyProperties(model, rankVO);
        if (needInfo) {
            InfoModel info = null;
            info = infoService.getSetInfo(model.getRid());
            BeanUtils.copyProperties(info, rankVO);
        }
        return rankVO;
    }


}
