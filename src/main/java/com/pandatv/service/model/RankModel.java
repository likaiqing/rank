package com.pandatv.service.model;

/**
 * @author: likaiqing
 * @create: 2019-01-28 12:31
 **/
public class RankModel {
    private String rid;
    private Double score;

    public RankModel() {
        super();
    }

    public RankModel(String rid, Double score) {
        super();
        this.rid = rid;
        this.score = score;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
