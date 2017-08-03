package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/28.
 */

public class PostAddNewDynamic {


    /**
     * dynamicidata : 天好蓝
     * dynamicimg : 天好蓝
     * favornum : 5
     * channelid : 998
     * creatid : 5554
     * channeltype : 2
     */

    private String dynamicdata;
    private String dynamicimg;
    private int favornum;
    private int channelid;
    private int creatid;
    private int channeltype;

    public PostAddNewDynamic(String dynamicidata, String dynamicimg, int favornum, int channelid, int creatid, int channeltype) {
        this.dynamicdata = dynamicidata;
        this.dynamicimg = dynamicimg;
        this.favornum = favornum;
        this.channelid = channelid;
        this.creatid = creatid;
        this.channeltype = channeltype;
    }

    public int getFavornum() {
        return favornum;
    }

    public void setFavornum(int favornum) {
        this.favornum = favornum;
    }

    public int getChannelid() {
        return channelid;
    }

    public void setChannelid(int channelid) {
        this.channelid = channelid;
    }

    public int getCreatid() {
        return creatid;
    }

    public void setCreatid(int creatid) {
        this.creatid = creatid;
    }

    public int getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(int channeltype) {
        this.channeltype = channeltype;
    }

    public String getDynamicidata() {
        return dynamicdata;
    }

    public void setDynamicidata(String dynamicidata) {
        this.dynamicdata = dynamicidata;
    }

    public String getDynamicimg() {
        return dynamicimg;
    }

    public void setDynamicimg(String dynamicimg) {
        this.dynamicimg = dynamicimg;
    }

}
