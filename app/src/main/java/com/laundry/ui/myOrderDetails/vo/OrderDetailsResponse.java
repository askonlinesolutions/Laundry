package com.laundry.ui.myOrderDetails.vo;

import java.util.List;

public class OrderDetailsResponse {


    /**
     * data : {"orderdetail_id":"18","orderdetail_promo_type":"","orderdetail_totalhour":"3","orderdetail_tax":"14","itemcount":2,"orderdetail_cust_id":"14","orderdetail_promo_val":"0","orderdetail_order_no":"72613329","orderdetail_promo":"","orderdetail_placed_status":"8","orderdetail_pick_address":"16","orderdetail_pickup":"2019-01-23","orderdetail_order_month":"1","orderdetail_tax_amount":"2.1","orderdetail_drop_address":null,"orderdetail_netpayble":"17.1","orderdetail_notify":"1","orderdetail_note":"","orderdetail_drop":"2019-01-24","orderdetail_order_date":"2019-01-23","orderdetail_type":"crm","orderdetail_pickup_time":"03:00","orderdetail_totalprice":"15","orderdetail_order_year":"2019","orderdetail_drop_time":"01:00","orderdetail_pay_type":"card","orderdetail_status":"1","orderdetail_card_no":"4111111111111111","orderdetail_transaction":"12434343","orderdetail_driver_assign":"1","orderdetail_drop_assign":"0","items":[{"orderitem_order_id":"18","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"1","orderitem_cat_id":"22","orderitem_service_id":"3","orderitem_id":"19","orderitem_qty":"1","orderitem_total":"3","orderitem_item_id":"45","orderitem_price":"3","orderitem_cust_id":"14","orderitem_time":"3","orderitem_name":"T-shirt"},{"orderitem_order_id":"18","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"1","orderitem_cat_id":"22","orderitem_service_id":"5","orderitem_id":"20","orderitem_qty":"3","orderitem_total":"12","orderitem_item_id":"45","orderitem_price":"4","orderitem_cust_id":"14","orderitem_time":"3","orderitem_name":"T-shirt"}]}
     * status : true
     */
    private DataEntity data;
    private boolean status;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        /**
         * orderdetail_id : 18
         * orderdetail_promo_type :
         * orderdetail_totalhour : 3
         * orderdetail_tax : 14
         * itemcount : 2
         * orderdetail_cust_id : 14
         * orderdetail_promo_val : 0
         * orderdetail_order_no : 72613329
         * orderdetail_promo :
         * orderdetail_placed_status : 8
         * orderdetail_pick_address : 16
         * orderdetail_pickup : 2019-01-23
         * orderdetail_order_month : 1
         * orderdetail_tax_amount : 2.1
         * orderdetail_drop_address : null
         * orderdetail_netpayble : 17.1
         * orderdetail_notify : 1
         * orderdetail_note :
         * orderdetail_drop : 2019-01-24
         * orderdetail_order_date : 2019-01-23
         * orderdetail_type : crm
         * orderdetail_pickup_time : 03:00
         * orderdetail_totalprice : 15
         * orderdetail_order_year : 2019
         * orderdetail_drop_time : 01:00
         * orderdetail_pay_type : card
         * orderdetail_status : 1
         * orderdetail_card_no : 4111111111111111
         * orderdetail_transaction : 12434343
         * orderdetail_driver_assign : 1
         * orderdetail_drop_assign : 0
         * items : [{"orderitem_order_id":"18","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"1","orderitem_cat_id":"22","orderitem_service_id":"3","orderitem_id":"19","orderitem_qty":"1","orderitem_total":"3","orderitem_item_id":"45","orderitem_price":"3","orderitem_cust_id":"14","orderitem_time":"3","orderitem_name":"T-shirt"},{"orderitem_order_id":"18","orderitem_image":"download1.jpg","orderitem_note":"","orderitem_status":"1","orderitem_cat_id":"22","orderitem_service_id":"5","orderitem_id":"20","orderitem_qty":"3","orderitem_total":"12","orderitem_item_id":"45","orderitem_price":"4","orderitem_cust_id":"14","orderitem_time":"3","orderitem_name":"T-shirt"}]
         */
        private String orderdetail_id;
        private String orderdetail_promo_type;
        private String orderdetail_totalhour;
        private String orderdetail_tax;
        private String itemcount;
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
        private List<ItemsEntity> items;

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

        public void setItemcount(String itemcount) {
            this.itemcount = itemcount;
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

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
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

        public String getItemcount() {
            return itemcount;
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

        public List<ItemsEntity> getItems() {
            return items;
        }

        public class ItemsEntity {
            /**
             * orderitem_order_id : 18
             * orderitem_image : download1.jpg
             * orderitem_note :
             * orderitem_status : 1
             * orderitem_cat_id : 22
             * orderitem_service_id : 3
             * orderitem_id : 19
             * orderitem_qty : 1
             * orderitem_total : 3
             * orderitem_item_id : 45
             * orderitem_price : 3
             * orderitem_cust_id : 14
             * orderitem_time : 3
             * orderitem_name : T-shirt
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
}
