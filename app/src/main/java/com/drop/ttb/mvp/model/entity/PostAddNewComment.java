package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/28.
 */

public class PostAddNewComment {

    /**
     * commentdata : 5554:是啊天好蓝
     * dynamicid : 11
     * creatid : 5554
     */

    private String commentdata;
    private int dynamicid;
    private int creatid;


    public PostAddNewComment(String commentdata, int dynamicid, int creatid) {
        this.commentdata = commentdata;
        this.dynamicid = dynamicid;
        this.creatid = creatid;
    }
}
