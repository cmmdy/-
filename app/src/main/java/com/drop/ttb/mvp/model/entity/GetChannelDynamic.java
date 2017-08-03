package com.drop.ttb.mvp.model.entity;

import java.util.List;

/**
 * Created by Drop on 2017/7/28.
 */

public class GetChannelDynamic {

    /**
     * resultCode : 1
     * resultMessage : 返回成功
     * resultData : [{"id":11,"dynamicdata":"","dynamicimg":"天好蓝","favornum":5,"channelid":998,"creatid":5554,"creatdate":"2017-07-28T16:41:32","channeltype":2},{"id":12,"dynamicdata":"","dynamicimg":"天好蓝","favornum":5,"channelid":998,"creatid":5554,"creatdate":"2017-07-28T16:46:14","channeltype":2},{"id":13,"dynamicdata":"","dynamicimg":"天好蓝","favornum":5,"channelid":998,"creatid":5554,"creatdate":"2017-07-28T16:46:15","channeltype":2},{"id":14,"dynamicdata":"","dynamicimg":"天好蓝","favornum":5,"channelid":998,"creatid":5554,"creatdate":"2017-07-28T16:46:16","channeltype":2}]
     */

    private int resultCode;
    private String resultMessage;
    private List<ResultDataBean> resultData;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * id : 11
         * dynamicdata :
         * dynamicimg : 天好蓝
         * favornum : 5
         * channelid : 998
         * creatid : 5554
         * creatdate : 2017-07-28T16:41:32
         * channeltype : 2
         */

        private int id;
        private String dynamicdata;
        private String dynamicimg;
        private int favornum;
        private int channelid;
        private int creatid;
        private String creatdate;
        private int channeltype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDynamicdata() {
            return dynamicdata;
        }

        public void setDynamicdata(String dynamicdata) {
            this.dynamicdata = dynamicdata;
        }

        public String getDynamicimg() {
            return dynamicimg;
        }

        public void setDynamicimg(String dynamicimg) {
            this.dynamicimg = dynamicimg;
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

        public String getCreatdate() {
            String[] temp  = creatdate.split("T");
            String[] temp1 = temp[0].split("-");
            String   temp2 = temp[1].substring(0, 5);
            String   temp3 = temp1[1] + "月" + temp1[2] + "日" +temp2;
            return temp3;
        }

        public void setCreatdate(String creatdate) {
            this.creatdate = creatdate;
        }

        public int getChanneltype() {
            return channeltype;
        }

        public void setChanneltype(int channeltype) {
            this.channeltype = channeltype;
        }
    }
}
