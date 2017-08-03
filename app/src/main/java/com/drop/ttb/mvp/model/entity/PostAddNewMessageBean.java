package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/31.
 */

public class PostAddNewMessageBean {
    String voicedata;
    String textdata;
    int communicationstatus;
    int accepterid;
    int creatid;
    int channelid;
    int channeltype;

    public PostAddNewMessageBean(String voicedata, String textdata, int communicationstatus, int accepterid, int creatid, int channelid, int channeltype) {
        this.voicedata = voicedata;
        this.textdata = textdata;
        this.communicationstatus = communicationstatus;
        this.accepterid = accepterid;
        this.creatid = creatid;
        this.channelid = channelid;
        this.channeltype = channeltype;
    }
}
