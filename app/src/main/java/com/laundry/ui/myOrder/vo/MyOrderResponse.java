package com.laundry.ui.myOrder.vo;

import java.util.List;

public class MyOrderResponse {
    /**
     * data : [{"orderdetail_id":"1","orderdetail_order_no":"18526422","orderdetail_drop":"2018-07-12","orderdetail_pickup":"2018-07-12","ItemCount":1,"orderdetail_pickup_time":"11:11","orderdetail_totalprice":"4","orderdetail_drop_time":"14:21","orderdetail_status":"1"},{"orderdetail_id":"2","orderdetail_order_no":"38539911","orderdetail_drop":"2018-07-12","orderdetail_pickup":"1970-01-01","ItemCount":1,"orderdetail_pickup_time":null,"orderdetail_totalprice":"30","orderdetail_drop_time":"11:11","orderdetail_status":"1"}]
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
         * orderdetail_id : 1
         * orderdetail_order_no : 18526422
         * orderdetail_drop : 2018-07-12
         * orderdetail_pickup : 2018-07-12
         * ItemCount : 1
         * orderdetail_pickup_time : 11:11
         * orderdetail_totalprice : 4
         * orderdetail_drop_time : 14:21
         * orderdetail_status : 1
         */
        private String orderdetail_id;
        private String orderdetail_order_no;
        private String orderdetail_drop;
        private String orderdetail_pickup;
        private String ItemCount;
        private String orderdetail_pickup_time;
        private String orderdetail_totalprice;
        private String orderdetail_drop_time;
        private String orderdetail_status;

        public void setOrderdetail_id(String orderdetail_id) {
            this.orderdetail_id = orderdetail_id;
        }

        public void setOrderdetail_order_no(String orderdetail_order_no) {
            this.orderdetail_order_no = orderdetail_order_no;
        }

        public void setOrderdetail_drop(String orderdetail_drop) {
            this.orderdetail_drop = orderdetail_drop;
        }

        public void setOrderdetail_pickup(String orderdetail_pickup) {
            this.orderdetail_pickup = orderdetail_pickup;
        }

        public void setItemCount(String ItemCount) {
            this.ItemCount = ItemCount;
        }

        public void setOrderdetail_pickup_time(String orderdetail_pickup_time) {
            this.orderdetail_pickup_time = orderdetail_pickup_time;
        }

        public void setOrderdetail_totalprice(String orderdetail_totalprice) {
            this.orderdetail_totalprice = orderdetail_totalprice;
        }

        public void setOrderdetail_drop_time(String orderdetail_drop_time) {
            this.orderdetail_drop_time = orderdetail_drop_time;
        }

        public void setOrderdetail_status(String orderdetail_status) {
            this.orderdetail_status = orderdetail_status;
        }

        public String getOrderdetail_id() {
            return orderdetail_id;
        }

        public String getOrderdetail_order_no() {
            return orderdetail_order_no;
        }

        public String getOrderdetail_drop() {
            return orderdetail_drop;
        }

        public String getOrderdetail_pickup() {
            return orderdetail_pickup;
        }

        public String getItemCount() {
            return ItemCount;
        }

        public String getOrderdetail_pickup_time() {
            return orderdetail_pickup_time;
        }

        public String getOrderdetail_totalprice() {
            return orderdetail_totalprice;
        }

        public String getOrderdetail_drop_time() {
            return orderdetail_drop_time;
        }

        public String getOrderdetail_status() {
            return orderdetail_status;
        }
    }





    /*
    *//**
     * data : [{"orderdetail_id":"1","orderdetail_promo_type":"","orderdetail_totalhour":"10","orderdetail_tax":"33","orderdetail_cust_id":"26","orderdetail_promo_val":"0","orderdetail_order_no":"18526422","orderdetail_promo":"","orderdetail_placed_status":"6","orderdetail_pick_address":"13","orderdetail_pickup":"2018-07-12","orderdetail_order_month":"7","orderdetail_tax_amount":"1.32","orderdetail_drop_address":"12","orderdetail_netpayble":"5.32","orderdetail_notify":"1","orderdetail_note":"","orderdetail_drop":"2018-07-12","orderdetail_order_date":"2018-07-12","orderdetail_type":"crm","orderdetail_pickup_time":"11:11","orderdetail_totalprice":"4","orderdetail_order_year":"2018","orderdetail_drop_time":"14:21","orderdetail_pay_type":"cash","orderdetail_status":"1","orderdetail_card_no":"","orderdetail_transaction":"","orderdetail_driver_assign":"1","orderdetail_drop_assign":"1"},{"orderdetail_id":"2","orderdetail_promo_type":"","orderdetail_totalhour":"3","orderdetail_tax":"33","orderdetail_cust_id":"26","orderdetail_promo_val":"0","orderdetail_order_no":"38539911","orderdetail_promo":"","orderdetail_placed_status":"8","orderdetail_pick_address":null,"orderdetail_pickup":"1970-01-01","orderdetail_order_month":"7","orderdetail_tax_amount":"9.9","orderdetail_drop_address":"12","orderdetail_netpayble":"39.9","orderdetail_notify":"1","orderdetail_note":"","orderdetail_drop":"2018-07-12","orderdetail_order_date":"2018-07-12","orderdetail_type":"walking","orderdetail_pickup_time":null,"orderdetail_totalprice":"30","orderdetail_order_year":"2018","orderdetail_drop_time":"11:11","orderdetail_pay_type":"cash","orderdetail_status":"1","orderdetail_card_no":"","orderdetail_transaction":"","orderdetail_driver_assign":"0","orderdetail_drop_assign":"0"}]
     * status : true
     *//*
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
        *//**
         * orderdetail_id : 1
         * orderdetail_promo_type :
         * orderdetail_totalhour : 10
         * orderdetail_tax : 33
         * orderdetail_cust_id : 26
         * orderdetail_promo_val : 0
         * orderdetail_order_no : 18526422
         * orderdetail_promo :
         * orderdetail_placed_status : 6
         * orderdetail_pick_address : 13
         * orderdetail_pickup : 2018-07-12
         * orderdetail_order_month : 7
         * orderdetail_tax_amount : 1.32
         * orderdetail_drop_address : 12
         * orderdetail_netpayble : 5.32
         * orderdetail_notify : 1
         * orderdetail_note :
         * orderdetail_drop : 2018-07-12
         * orderdetail_order_date : 2018-07-12
         * orderdetail_type : crm
         * orderdetail_pickup_time : 11:11
         * orderdetail_totalprice : 4
         * orderdetail_order_year : 2018
         * orderdetail_drop_time : 14:21
         * orderdetail_pay_type : cash
         * orderdetail_status : 1
         * orderdetail_card_no :
         * orderdetail_transaction :
         * orderdetail_driver_assign : 1
         * orderdetail_drop_assign : 1
         *//*
        private String orderdetail_id;
        private String orderdetail_promo_type;
        private String orderdetail_totalhour;
        private String orderdetail_tax;
        private String orderdetail_cust_id;
        private String orderdetail_promo_val;
        private String orderdetail_order_no;
        private String orderdetail_promo;
        private String orderdetail_placed_status;
        private String orderdetail_pick_address;
        private String orderdetail_pickup;
        private String orderdetail_order_month;
        private String orderdetail_tax_amount;
        private String orderdetail_drop_address;
        private String orderdetail_netpayble;
        private String orderdetail_notify;
        private String orderdetail_note;
        private String orderdetail_drop;
        private String orderdetail_order_date;
        private String orderdetail_type;
        private String orderdetail_pickup_time;
        private String orderdetail_totalprice;
        private String orderdetail_order_year;
        private String orderdetail_drop_time;
        private String orderdetail_pay_type;
        private String orderdetail_status;
        private String orderdetail_card_no;
        private String orderdetail_transaction;
        private String orderdetail_driver_assign;
        private String orderdetail_drop_assign;

        public void setOrderdetail_id(String orderdetail_id) {
            this.orderdetail_id = orderdetail_id;
        }

        public void setOrderdetail_promo_type(String orderdetail_promo_type) {
            this.orderdetail_promo_type = orderdetail_promo_type;
        }

        public void setOrderdetail_totalhour(String orderdetail_totalhour) {
            this.orderdetail_totalhour = orderdetail_totalhour;
        }

        public void setOrderdetail_tax(String orderdetail_tax) {
            this.orderdetail_tax = orderdetail_tax;
        }

        public void setOrderdetail_cust_id(String orderdetail_cust_id) {
            this.orderdetail_cust_id = orderdetail_cust_id;
        }

        public void setOrderdetail_promo_val(String orderdetail_promo_val) {
            this.orderdetail_promo_val = orderdetail_promo_val;
        }

        public void setOrderdetail_order_no(String orderdetail_order_no) {
            this.orderdetail_order_no = orderdetail_order_no;
        }

        public void setOrderdetail_promo(String orderdetail_promo) {
            this.orderdetail_promo = orderdetail_promo;
        }

        public void setOrderdetail_placed_status(String orderdetail_placed_status) {
            this.orderdetail_placed_status = orderdetail_placed_status;
        }

        public void setOrderdetail_pick_address(String orderdetail_pick_address) {
            this.orderdetail_pick_address = orderdetail_pick_address;
        }

        public void setOrderdetail_pickup(String orderdetail_pickup) {
            this.orderdetail_pickup = orderdetail_pickup;
        }

        public void setOrderdetail_order_month(String orderdetail_order_month) {
            this.orderdetail_order_month = orderdetail_order_month;
        }

        public void setOrderdetail_tax_amount(String orderdetail_tax_amount) {
            this.orderdetail_tax_amount = orderdetail_tax_amount;
        }

        public void setOrderdetail_drop_address(String orderdetail_drop_address) {
            this.orderdetail_drop_address = orderdetail_drop_address;
        }

        public void setOrderdetail_netpayble(String orderdetail_netpayble) {
            this.orderdetail_netpayble = orderdetail_netpayble;
        }

        public void setOrderdetail_notify(String orderdetail_notify) {
            this.orderdetail_notify = orderdetail_notify;
        }

        public void setOrderdetail_note(String orderdetail_note) {
            this.orderdetail_note = orderdetail_note;
        }

        public void setOrderdetail_drop(String orderdetail_drop) {
            this.orderdetail_drop = orderdetail_drop;
        }

        public void setOrderdetail_order_date(String orderdetail_order_date) {
            this.orderdetail_order_date = orderdetail_order_date;
        }

        public void setOrderdetail_type(String orderdetail_type) {
            this.orderdetail_type = orderdetail_type;
        }

        public void setOrderdetail_pickup_time(String orderdetail_pickup_time) {
            this.orderdetail_pickup_time = orderdetail_pickup_time;
        }

        public void setOrderdetail_totalprice(String orderdetail_totalprice) {
            this.orderdetail_totalprice = orderdetail_totalprice;
        }

        public void setOrderdetail_order_year(String orderdetail_order_year) {
            this.orderdetail_order_year = orderdetail_order_year;
        }

        public void setOrderdetail_drop_time(String orderdetail_drop_time) {
            this.orderdetail_drop_time = orderdetail_drop_time;
        }

        public void setOrderdetail_pay_type(String orderdetail_pay_type) {
            this.orderdetail_pay_type = orderdetail_pay_type;
        }

        public void setOrderdetail_status(String orderdetail_status) {
            this.orderdetail_status = orderdetail_status;
        }

        public void setOrderdetail_card_no(String orderdetail_card_no) {
            this.orderdetail_card_no = orderdetail_card_no;
        }

        public void setOrderdetail_transaction(String orderdetail_transaction) {
            this.orderdetail_transaction = orderdetail_transaction;
        }

        public void setOrderdetail_driver_assign(String orderdetail_driver_assign) {
            this.orderdetail_driver_assign = orderdetail_driver_assign;
        }

        public void setOrderdetail_drop_assign(String orderdetail_drop_assign) {
            this.orderdetail_drop_assign = orderdetail_drop_assign;
        }

        public String getOrderdetail_id() {
            return orderdetail_id;
        }

        public String getOrderdetail_promo_type() {
            return orderdetail_promo_type;
        }

        public String getOrderdetail_totalhour() {
            return orderdetail_totalhour;
        }

        public String getOrderdetail_tax() {
            return orderdetail_tax;
        }

        public String getOrderdetail_cust_id() {
            return orderdetail_cust_id;
        }

        public String getOrderdetail_promo_val() {
            return orderdetail_promo_val;
        }

        public String getOrderdetail_order_no() {
            return orderdetail_order_no;
        }

        public String getOrderdetail_promo() {
            return orderdetail_promo;
        }

        public String getOrderdetail_placed_status() {
            return orderdetail_placed_status;
        }

        public String getOrderdetail_pick_address() {
            return orderdetail_pick_address;
        }

        public String getOrderdetail_pickup() {
            return orderdetail_pickup;
        }

        public String getOrderdetail_order_month() {
            return orderdetail_order_month;
        }

        public String getOrderdetail_tax_amount() {
            return orderdetail_tax_amount;
        }

        public String getOrderdetail_drop_address() {
            return orderdetail_drop_address;
        }

        public String getOrderdetail_netpayble() {
            return orderdetail_netpayble;
        }

        public String getOrderdetail_notify() {
            return orderdetail_notify;
        }

        public String getOrderdetail_note() {
            return orderdetail_note;
        }

        public String getOrderdetail_drop() {
            return orderdetail_drop;
        }

        public String getOrderdetail_order_date() {
            return orderdetail_order_date;
        }

        public String getOrderdetail_type() {
            return orderdetail_type;
        }

        public String getOrderdetail_pickup_time() {
            return orderdetail_pickup_time;
        }

        public String getOrderdetail_totalprice() {
            return orderdetail_totalprice;
        }

        public String getOrderdetail_order_year() {
            return orderdetail_order_year;
        }

        public String getOrderdetail_drop_time() {
            return orderdetail_drop_time;
        }

        public String getOrderdetail_pay_type() {
            return orderdetail_pay_type;
        }

        public String getOrderdetail_status() {
            return orderdetail_status;
        }

        public String getOrderdetail_card_no() {
            return orderdetail_card_no;
        }

        public String getOrderdetail_transaction() {
            return orderdetail_transaction;
        }

        public String getOrderdetail_driver_assign() {
            return orderdetail_driver_assign;
        }

        public String getOrderdetail_drop_assign() {
            return orderdetail_drop_assign;
        }
    }*/
}
