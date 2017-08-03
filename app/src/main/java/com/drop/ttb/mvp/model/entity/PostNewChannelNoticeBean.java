package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/27.
 */

public class PostNewChannelNoticeBean {
    String noticedata;
    int channelid;
    int channeltype;
    int creatid;

    public PostNewChannelNoticeBean(String noticedata, int channelid, int channeltype, int creatid) {
        this.noticedata = noticedata;
        this.channelid = channelid;
        this.channeltype = channeltype;
        this.creatid = creatid;
    }
}
