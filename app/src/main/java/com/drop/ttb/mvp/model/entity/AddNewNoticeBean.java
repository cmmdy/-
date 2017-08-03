package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/26.
 */

public class AddNewNoticeBean {

    /**
     * resultCode : 1
     * resultMessage : 添加成功
     * resultData : {"id":10,"noticedata":"1111","channelid":5541,"creatid":5541,"creatdate":"2017-07-26T20:24:24","channeltype":2}
     */

    private int resultCode;
    private String resultMessage;
    private ResultDataBean resultData;

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

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * id : 10
         * noticedata : 1111
         * channelid : 5541
         * creatid : 5541
         * creatdate : 2017-07-26T20:24:24
         * channeltype : 2
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
            return creatdate;
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
