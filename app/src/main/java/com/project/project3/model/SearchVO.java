package com.project.project3.model;

public class SearchVO {

    private String tv_name;
    private String tv_add;
    private String tv_score;

    private String tv_review;

    private int img_list;

    public String getTv_name() {
        return tv_name;
    }

    public String getTv_add() {
        return tv_add;
    }

    public String getTv_score() {
        return tv_score;
    }

    public String getTv_review() {
        return tv_review;
    }

    public int getImg_list() {
        return img_list;
    }

    public SearchVO(String tv_name, String tv_add, String tv_score, String tv_review, int img_list) {
        this.tv_name = tv_name;
        this.tv_add = tv_add;
        this.tv_score = tv_score;
        this.tv_review = tv_review;
        this.img_list = img_list;
    }
}
