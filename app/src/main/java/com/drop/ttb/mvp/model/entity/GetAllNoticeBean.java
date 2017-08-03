package com.drop.ttb.mvp.model.entity;

import java.util.List;

/**
 * Created by Drop on 2017/7/26.
 */

public class GetAllNoticeBean {

    /**
     * resultCode : 1
     * resultMessage : 返回成功
     * resultData : [{"id":2,"noticedata":"背景","channelid":1,"creatid":2,"creatdate":"2017-07-23T16:19:10","channeltype":1},{"id":3,"noticedata":"中国最牛","channelid":1,"creatid":1,"creatdate":"1970-01-01T00:00:00","channeltype":0},{"id":4,"noticedata":"中国最牛","channelid":1,"creatid":1,"creatdate":"2017-07-23T15:44:04","channeltype":0},{"id":5,"noticedata":"中国最牛","channelid":1,"creatid":1,"creatdate":"2017-07-23T15:49:06","channeltype":0},{"id":7,"noticedata":"111111","channelid":null,"creatid":null,"creatdate":"2017-07-26T20:07:47","channeltype":null},{"id":8,"noticedata":"111111","channelid":null,"creatid":null,"creatdate":"2017-07-26T20:21:37","channeltype":null},{"id":9,"noticedata":"1111","channelid":null,"creatid":null,"creatdate":"2017-07-26T20:22:37","channeltype":null},{"id":10,"noticedata":"1111","channelid":5541,"creatid":5541,"creatdate":"2017-07-26T20:24:24","channeltype":2}]
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
         * id : 2
         * noticedata : 背景
         * channelid : 1
         * creatid : 2
         * creatdate : 2017-07-23T16:19:10
         * channeltype : 1
         */

        private int id;
        private String noticedata;
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

        public String getNoticedata() {
            return noticedata;
        }

        public void setNoticedata(String noticedata) {
            this.noticedata = noticedata;
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
