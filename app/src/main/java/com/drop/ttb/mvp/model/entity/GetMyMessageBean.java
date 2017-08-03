package com.drop.ttb.mvp.model.entity;

import java.util.List;

/**
 * Created by Drop on 2017/7/31.
 */

public class GetMyMessageBean {

    /**
     * resultCode : 1
     * resultMessage : 返回成功
     * resultData : [{"id":3,"voicedata":"azwsxd","textdata":"过得怎么样","communicationstatus":0,"accepterid":1,"creatid":2,"creatdate":"2017-07-25T20:04:59","channelid":1,"channeltype":1},{"id":10,"voicedata":"azwsxd","textdata":"过得怎么样","communicationstatus":0,"accepterid":1,"creatid":2,"creatdate":"2017-07-25T22:26:14","channelid":1,"channeltype":1},{"id":11,"voicedata":"azwsxd","textdata":"过得怎么样","communicationstatus":0,"accepterid":1,"creatid":2,"creatdate":"2017-07-26T09:16:07","channelid":1,"channeltype":1}]
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
         * id : 3
         * voicedata : azwsxd
         * textdata : 过得怎么样
         * communicationstatus : 0
         * accepterid : 1
         * creatid : 2
         * creatdate : 2017-07-25T20:04:59
         * channelid : 1
         * channeltype : 1
         */

        private int id;
        private String voicedata;
        private String textdata;
        private int communicationstatus;
        private int accepterid;
        private int creatid;
        private String creatdate;
        private int channelid;
        private int channeltype;

        public String getChannelname() {
            return channelname;
        }

        public void setChannelname(String channelname) {
            this.channelname = channelname;
        }

        private String channelname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVoicedata() {
            return voicedata;
        }

        public void setVoicedata(String voicedata) {
            this.voicedata = voicedata;
        }

        public String getTextdata() {
            return textdata;
        }

        public void setTextdata(String textdata) {
            this.textdata = textdata;
        }

        public int getCommunicationstatus() {
            return communicationstatus;
        }

        public void setCommunicationstatus(int communicationstatus) {
            this.communicationstatus = communicationstatus;
        }

        public int getAccepterid() {
            return accepterid;
        }

        public void setAccepterid(int accepterid) {
            this.accepterid = accepterid;
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

        public int getChannelid() {
            return channelid;
        }

        public void setChannelid(int channelid) {
            this.channelid = channelid;
        }

        public int getChanneltype() {
            return channeltype;
        }

        public void setChanneltype(int channeltype) {
            this.channeltype = channeltype;
        }
    }
}
