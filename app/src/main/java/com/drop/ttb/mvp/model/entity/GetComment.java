package com.drop.ttb.mvp.model.entity;

import java.util.List;

/**
 * Created by Drop on 2017/7/28.
 */

public class GetComment {

    /**
     * resultCode : 1
     * resultMessage : 返回成功
     * resultData : [{"id":12,"commentdata":"5554:是啊天好蓝","dynamicid":11,"creatid":5554,"creatdate":"2017-07-28T16:54:44"},{"id":13,"commentdata":"5554:是啊天好蓝","dynamicid":11,"creatid":5554,"creatdate":"2017-07-28T16:54:45"},{"id":14,"commentdata":"5554:是啊天好蓝","dynamicid":11,"creatid":5554,"creatdate":"2017-07-28T16:54:46"},{"id":15,"commentdata":"5554:是啊天好蓝","dynamicid":11,"creatid":5554,"creatdate":"2017-07-28T16:54:46"}]
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
         * id : 12
         * commentdata : 5554:是啊天好蓝
         * dynamicid : 11
         * creatid : 5554
         * creatdate : 2017-07-28T16:54:44
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
