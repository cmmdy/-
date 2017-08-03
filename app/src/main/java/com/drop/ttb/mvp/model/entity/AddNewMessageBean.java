package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/31.
 */

public class AddNewMessageBean {

    /**
     * resultCode : 1
     * resultMessage : 添加成功
     * resultData : {"id":12,"voicedata":" ","textdata":"d","communicationstatus":1,"accepterid":1,"creatid":1,"creatdate":"2017-07-31T13:57:19","channelid":"1l","channeltype":1}
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
         * id : 12
         * voicedata :
         * textdata : d
         * communicationstatus : 1
         * accepterid : 1
         * creatid : 1
         * creatdate : 2017-07-31T13:57:19
         * channelid : 1l
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
            return creatdate;
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
