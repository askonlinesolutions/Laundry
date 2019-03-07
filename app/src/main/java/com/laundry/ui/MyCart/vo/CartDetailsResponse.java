package com.laundry.ui.MyCart.vo;

import java.util.List;

public class CartDetailsResponse {


    /**
     * data : [{"orderitem_order_id":"26","orderitem_image":"women-coat2.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"4","orderitem_id":"18","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"49","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Coat"},{"orderitem_order_id":"27","orderitem_image":"women-shirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"4","orderitem_id":"19","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"57","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"28","orderitem_image":"skirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"4","orderitem_id":"20","orderitem_qty":"4","orderitem_total":"0","orderitem_item_id":"67","orderitem_price":"15","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Skirt"},{"orderitem_order_id":"29","orderitem_image":"cc.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"4","orderitem_id":"21","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"96","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"30","orderitem_image":"jeans.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"4","orderitem_id":"22","orderitem_qty":"4","orderitem_total":"0","orderitem_item_id":"95","orderitem_price":"100","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Jeans"},{"orderitem_order_id":"31","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"3","orderitem_id":"23","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"45","orderitem_price":"3","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"T-shirt"},{"orderitem_order_id":"32","orderitem_image":"cc.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"3","orderitem_id":"24","orderitem_qty":"3","orderitem_total":"0","orderitem_item_id":"96","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"33","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"4","orderitem_id":"25","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"45","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"T-shirt"},{"orderitem_order_id":"34","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"4","orderitem_id":"26","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"0","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"T-shirt"},{"orderitem_order_id":"35","orderitem_image":"women-shirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"3","orderitem_id":"27","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"57","orderitem_price":"15","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"36","orderitem_image":"skirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"3","orderitem_id":"28","orderitem_qty":"5","orderitem_total":"0","orderitem_item_id":"67","orderitem_price":"25","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Skirt"},{"orderitem_order_id":"37","orderitem_image":"kidsshirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"3","orderitem_id":"29","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"54","orderitem_price":"30","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"shirt"},{"orderitem_order_id":"38","orderitem_image":"trowser.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"4","orderitem_id":"30","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"94","orderitem_price":"75","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Trouser"},{"orderitem_order_id":"39","orderitem_image":"trowser.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"24","orderitem_id":"31","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"94","orderitem_price":"75","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Trouser"},{"orderitem_order_id":"40","orderitem_image":"trowser.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"24","orderitem_id":"32","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"0","orderitem_price":"75","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Trouser"},{"orderitem_order_id":"41","orderitem_image":"women-coat2.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"21","orderitem_service_id":"0","orderitem_id":"33","orderitem_qty":"5","orderitem_total":"0","orderitem_item_id":"49","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Coat"},{"orderitem_order_id":"42","orderitem_image":"women-shirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"21","orderitem_service_id":"0","orderitem_id":"34","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"57","orderitem_price":"7","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"43","orderitem_image":"skirt.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"21","orderitem_service_id":"0","orderitem_id":"35","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"67","orderitem_price":"15","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Skirt"},{"orderitem_order_id":"44","orderitem_image":"women-coat2.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"21","orderitem_service_id":"4","orderitem_id":"36","orderitem_qty":"0","orderitem_total":"0","orderitem_item_id":"49","orderitem_price":"15","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Coat"},{"orderitem_order_id":"45","orderitem_image":"women-coat2.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"21","orderitem_service_id":"5","orderitem_id":"37","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"49","orderitem_price":"8","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Coat"},{"orderitem_order_id":"46","orderitem_image":"trowser.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"5","orderitem_id":"38","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"94","orderitem_price":"50","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Trouser"},{"orderitem_order_id":"47","orderitem_image":"jeans.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"23","orderitem_id":"39","orderitem_qty":"0","orderitem_total":"0","orderitem_item_id":"95","orderitem_price":"200","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Jeans"},{"orderitem_order_id":"48","orderitem_image":"cc.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"23","orderitem_id":"40","orderitem_qty":"0","orderitem_total":"0","orderitem_item_id":"96","orderitem_price":"200","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"49","orderitem_image":"trowser.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"25","orderitem_service_id":"0","orderitem_id":"41","orderitem_qty":"2","orderitem_total":"0","orderitem_item_id":"94","orderitem_price":"75","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Trouser"},{"orderitem_order_id":"50","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"0","orderitem_id":"42","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"45","orderitem_price":"5","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"T-shirt"},{"orderitem_order_id":"51","orderitem_image":"jeans.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"0","orderitem_id":"43","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"95","orderitem_price":"100","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Jeans"},{"orderitem_order_id":"52","orderitem_image":"cc.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"0","orderitem_id":"44","orderitem_qty":"1","orderitem_total":"0","orderitem_item_id":"96","orderitem_price":"50","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"Shirt"},{"orderitem_order_id":"53","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"6","orderitem_id":"45","orderitem_qty":"7","orderitem_total":"0","orderitem_item_id":"46","orderitem_price":"30","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"T-shirt"},{"orderitem_order_id":"54","orderitem_image":"rtrtet","orderitem_note":"","orderitem_status":"0","orderitem_cat_id":"22","orderitem_service_id":"3","orderitem_id":"46","orderitem_qty":"7","orderitem_total":"0","orderitem_item_id":"46","orderitem_price":"303","orderitem_cust_id":"49","orderitem_time":"0","orderitem_name":"T shgirt"}]
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
         * orderitem_order_id : 26
         * orderitem_image : women-coat2.jpg
         * orderitem_note :
         * orderitem_status : 0
         * orderitem_cat_id : 25
         * orderitem_service_id : 4
         * orderitem_id : 18
         * orderitem_qty : 1
         * orderitem_total : 0
         * orderitem_item_id : 49
         * orderitem_price : 5
         * orderitem_discount_price: "0",
         * orderitem_cust_id : 49
         * orderitem_time : 0
         * orderitem_name : Coat
         */
        private String orderitem_order_id;
        private String orderitem_image;
        private String orderitem_note;
        private String orderitem_status;
        private String orderitem_cat_id;
        private String orderitem_service_id;
        private String orderitem_id;
        private String orderitem_qty;
        private String orderitem_total;
        private String orderitem_item_id;
        private String orderitem_price;
        private String orderitem_cust_id;
        private String orderitem_time;
        private String orderitem_name;
        private String orderitem_discount_price;

        public String getOrderitem_discount_price() {
            return orderitem_discount_price;
        }

        public void setOrderitem_discount_price(String orderitem_discount_price) {
            this.orderitem_discount_price = orderitem_discount_price;
        }

        public void setOrderitem_order_id(String orderitem_order_id) {
            this.orderitem_order_id = orderitem_order_id;
        }

        public void setOrderitem_image(String orderitem_image) {
            this.orderitem_image = orderitem_image;
        }

        public void setOrderitem_note(String orderitem_note) {
            this.orderitem_note = orderitem_note;
        }

        public void setOrderitem_status(String orderitem_status) {
            this.orderitem_status = orderitem_status;
        }

        public void setOrderitem_cat_id(String orderitem_cat_id) {
            this.orderitem_cat_id = orderitem_cat_id;
        }

        public void setOrderitem_service_id(String orderitem_service_id) {
            this.orderitem_service_id = orderitem_service_id;
        }

        public void setOrderitem_id(String orderitem_id) {
            this.orderitem_id = orderitem_id;
        }

        public void setOrderitem_qty(String orderitem_qty) {
            this.orderitem_qty = orderitem_qty;
        }

        public void setOrderitem_total(String orderitem_total) {
            this.orderitem_total = orderitem_total;
        }

        public void setOrderitem_item_id(String orderitem_item_id) {
            this.orderitem_item_id = orderitem_item_id;
        }

        public void setOrderitem_price(String orderitem_price) {
            this.orderitem_price = orderitem_price;
        }

        public void setOrderitem_cust_id(String orderitem_cust_id) {
            this.orderitem_cust_id = orderitem_cust_id;
        }

        public void setOrderitem_time(String orderitem_time) {
            this.orderitem_time = orderitem_time;
        }

        public void setOrderitem_name(String orderitem_name) {
            this.orderitem_name = orderitem_name;
        }

        public String getOrderitem_order_id() {
            return orderitem_order_id;
        }

        public String getOrderitem_image() {
            return orderitem_image;
        }

        public String getOrderitem_note() {
            return orderitem_note;
        }

        public String getOrderitem_status() {
            return orderitem_status;
        }

        public String getOrderitem_cat_id() {
            return orderitem_cat_id;
        }

        public String getOrderitem_service_id() {
            return orderitem_service_id;
        }

        public String getOrderitem_id() {
            return orderitem_id;
        }

        public String getOrderitem_qty() {
            return orderitem_qty;
        }

        public String getOrderitem_total() {
            return orderitem_total;
        }

        public String getOrderitem_item_id() {
            return orderitem_item_id;
        }

        public String getOrderitem_price() {
            return orderitem_price;
        }

        public String getOrderitem_cust_id() {
            return orderitem_cust_id;
        }

        public String getOrderitem_time() {
            return orderitem_time;
        }

        public String getOrderitem_name() {
            return orderitem_name;
        }
    }
}
