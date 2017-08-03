package com.drop.ttb.mvp.model.entity;

/**
 * Created by Drop on 2017/7/28.
 */

public class AddNewComment {

    /**
     * resultCode : 1
     * resultMessage : 添加成功
     * resultData : {"id":15,"commentdata":"5554:是啊天好蓝","dynamicid":11,"creatid":5554,"creatdate":"2017-07-28T16:54:46"}
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
         * id : 15
         * commentdata : 5554:是啊天好蓝
         * dynamicid : 11
         * creatid : 5554
         * creatdate : 2017-07-28T16:54:46
         */

        private int id;
        private String commentdata;
        private int dynamicid;
        private int creatid;
        private String creatdate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCommentdata() {
            return commentdata;
        }

        public void setCommentdata(String commentdata) {
            this.commentdata = commentdata;
        }

        public int getDynamicid() {
            return dynamicid;
        }

        public void setDynamicid(int dynamicid) {
            this.dynamicid = dynamicid;
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
    }
}
