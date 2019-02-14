package com.laundry.ui.FAQ.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FaqResponse {


    /**
     * data : [{"faq_man_answer":"","faq_man_id":"2","faq_man_question":"What times does Happy Laundry pickup and deliver?","faq_man_status":"1"}]
     * status : true
     */
    @SerializedName("data")
    public List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @SerializedName("status")
    public boolean status;

    public class DataEntity {
        /**
         * faq_man_answer :
         * faq_man_id : 2
         * faq_man_question : What times does Happy Laundry pickup and deliver?
         * faq_man_status : 1
         */
        @SerializedName("faq_man_answer")
        public String faq_man_answer;

        public String getFaq_man_answer() {
            return faq_man_answer;
        }

        public void setFaq_man_answer(String faq_man_answer) {
            this.faq_man_answer = faq_man_answer;
        }

        public String getFaq_man_id() {
            return faq_man_id;
        }

        public void setFaq_man_id(String faq_man_id) {
            this.faq_man_id = faq_man_id;
        }

        public String getFaq_man_question() {
            return faq_man_question;
        }

        public void setFaq_man_question(String faq_man_question) {
            this.faq_man_question = faq_man_question;
        }

        public String getFaq_man_status() {
            return faq_man_status;
        }

        public void setFaq_man_status(String faq_man_status) {
            this.faq_man_status = faq_man_status;
        }

        @SerializedName("faq_man_id")
        public String faq_man_id;
        @SerializedName("faq_man_question")
        public String faq_man_question;
        @SerializedName("faq_man_status")
        public String faq_man_status;
    }
}
