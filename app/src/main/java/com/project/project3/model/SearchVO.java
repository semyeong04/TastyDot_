package com.project.project3.model;

public class SearchVO {

    private String tv_name;
    private String store_category;
    private String tv_score;
    private String tv_add;
    private String tv_hash;

    private String tv_rate;

    private int tv_review_cnt;
    private int img_list;
    private String hashtag; // json의 menu해당

    public String getTv_name() {
        return tv_name;
    }
    public String getStore_category() {
        return store_category;
    }

    public String getTv_score() {
        return tv_rate;
    }

    public String getTv_add() {
        return tv_add;
    }

    public String getTv_hash() {
        return tv_hash;
    }

    public int getImg_list() {
        return img_list;
    }

    public SearchVO(String tv_name, String tv_add, String tv_hash, String tv_rate, int img_list) {
        this.tv_name = tv_name;
        this.tv_add = tv_add;
        this.tv_rate = tv_rate;
        this.img_list = img_list;
        this.tv_hash = tv_hash;
    }
}
