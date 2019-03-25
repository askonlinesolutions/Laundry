package com.laundry.ui.offer.vo;

import java.util.List;

public class OfferResponse {


    /**
     * data : [{"discount_status":"1","discount_from":"2019-03-20","discount_coupon_val":"8789","discount_dis_value":"8","discount_type":"percent","discount_coupon":"DIWALI Bumper Offer","discount_id":"30","discount_to":"2019-03-31"},{"discount_status":"1","discount_from":"2019-03-10","discount_coupon_val":"holi10","discount_dis_value":"20","discount_type":"percent","discount_coupon":"Holi Dhamaka Offer","discount_id":"31","discount_to":"2019-03-31"}]
     * status : true
     */
    private List<DataEntity> data;
    private boolean status;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        /**
         * discount_status : 1
         * discount_from : 2019-03-20
         * discount_coupon_val : 8789
         * discount_dis_value : 8
         * discount_type : percent
         * discount_coupon : DIWALI Bumper Offer
         * discount_id : 30
         * discount_to : 2019-03-31
         */
        private String discount_status;
        private String discount_from;
        private String discount_coupon_val;
        private String discount_dis_value;
        private String discount_type;
        private String discount_coupon;
        private String discount_id;
        private String discount_to;

        public void setDiscount_status(String discount_status) {
            this.discount_status = discount_status;
        }

        public void setDiscount_from(String discount_from) {
            this.discount_from = discount_from;
        }

        public void setDiscount_coupon_val(String discount_coupon_val) {
            this.discount_coupon_val = discount_coupon_val;
        }

        public void setDiscount_dis_value(String discount_dis_value) {
            this.discount_dis_value = discount_dis_value;
        }

        public void setDiscount_type(String discount_type) {
            this.discount_type = discount_type;
        }

        public void setDiscount_coupon(String discount_coupon) {
            this.discount_coupon = discount_coupon;
        }

        public void setDiscount_id(String discount_id) {
            this.discount_id = discount_id;
        }

        public void setDiscount_to(String discount_to) {
            this.discount_to = discount_to;
        }

        public String getDiscount_status() {
            return discount_status;
        }

        public String getDiscount_from() {
            return discount_from;
        }

        public String getDiscount_coupon_val() {
            return discount_coupon_val;
        }

        public String getDiscount_dis_value() {
            return discount_dis_value;
        }

        public String getDiscount_type() {
            return discount_type;
        }

        public String getDiscount_coupon() {
            return discount_coupon;
        }

        public String getDiscount_id() {
            return discount_id;
        }

        public String getDiscount_to() {
            return discount_to;
        }
    }
}
