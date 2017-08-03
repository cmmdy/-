package com.drop.ttb.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Drop on 2017/7/26.
 */

public class PostChannelAddNewChannelBean {
    int id;
    String name;
    String channelimg;
    int channeltype;
    int guideid;
    int guidestatus;

    public PostChannelAddNewChannelBean(int id, String name, String channelimg, int channeltype, int guideid, int guidestatus) {
        this.id = id;
        this.name = name;
        this.channelimg = channelimg;
        this.channeltype = channeltype;
        this.guideid = guideid;
        this.guidestatus = guidestatus;
    }
}
