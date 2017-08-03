package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/26.
 */

public class ChannelAddNewChannelBean {

    /**
     * resultCode : 1
     * resultMessage : 添加成功
     * resultData : {"id":55,"name":"","channelimg":"","channeltype":null,"guideid":null,"guidestatus":null}
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

    @Override
    public String toString() {
        return "ChannelAddNewChannelBean{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultData=" + resultData +
                '}';
    }

    public static class ResultDataBean {
        /**
         * id : 55
         * name :
         * channelimg :
         * channeltype : null
         * guideid : null
         * guidestatus : null
         */

        private int id;
        private String name;
        private String channelimg;
        private int channeltype;
        private int guideid;
        private int guidestatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getChannelimg() {
            return channelimg;
        }

        public void setChannelimg(String channelimg) {
            this.channelimg = channelimg;
        }

        public int getChanneltype() {
            return channeltype;
        }

        public void setChanneltype(int channeltype) {
            this.channeltype = channeltype;
        }

        public int getGuideid() {
            return guideid;
        }

        public void setGuideid(int guideid) {
            this.guideid = guideid;
        }

        public int getGuidestatus() {
            return guidestatus;
        }

        public void setGuidestatus(int guidestatus) {
            this.guidestatus = guidestatus;
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", channelimg='" + channelimg + '\'' +
                    ", channeltype=" + channeltype +
                    ", guideid=" + guideid +
                    ", guidestatus=" + guidestatus +
                    '}';
        }
    }
}
