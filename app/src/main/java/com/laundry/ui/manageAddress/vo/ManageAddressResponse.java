package com.laundry.ui.manageAddress.vo;

import java.util.List;

public class ManageAddressResponse {


    /**
     * data : [{"useraddress_address":"fgdfg","useraddress_id":"15","useraddress_title":"dfgdf","useraddress_type":"1","useraddress_status":"1","useraddress_userid":"14","useraddress_state":"34","address_lat":"","address_long":"","useraddress_landmark":"ghgh","useraddress_zipcode":"201301","useraddress_city":"ggfh"},{"useraddress_address":"ds","useraddress_id":"16","useraddress_title":"dfgdf","useraddress_type":"2","useraddress_status":"1","useraddress_userid":"14","useraddress_state":"3","address_lat":"","address_long":"","useraddress_landmark":"fddf","useraddress_zipcode":"3","useraddress_city":"df"},{"useraddress_address":"4341 B Street, Suite 202 Anchorage,","useraddress_id":"21","useraddress_title":"dfgdsf","useraddress_type":"2","useraddress_status":"1","useraddress_userid":"14","useraddress_state":"Anchorage","address_lat":"","address_long":"","useraddress_landmark":"Alaska","useraddress_zipcode":"99503","useraddress_city":"Valdez"}]
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
         * useraddress_address : fgdfg
         * useraddress_id : 15
         * useraddress_title : dfgdf
         * useraddress_type : 1
         * useraddress_status : 1
         * useraddress_userid : 14
         * useraddress_state : 34
         * address_lat :
         * address_long :
         * useraddress_landmark : ghgh
         * useraddress_zipcode : 201301
         * useraddress_city : ggfh
         */
        private String useraddress_address;
        private String useraddress_id;
        private String useraddress_title;
        private String useraddress_type;
        private String useraddress_status;
        private String useraddress_userid;
        private String useraddress_state;
        private String address_lat;
        private String address_long;
        private String useraddress_landmark;
        private String useraddress_zipcode;
        private String useraddress_city;

        public void setUseraddress_address(String useraddress_address) {
            this.useraddress_address = useraddress_address;
        }

        public void setUseraddress_id(String useraddress_id) {
            this.useraddress_id = useraddress_id;
        }

        public void setUseraddress_title(String useraddress_title) {
            this.useraddress_title = useraddress_title;
        }

        public void setUseraddress_type(String useraddress_type) {
            this.useraddress_type = useraddress_type;
        }

        public void setUseraddress_status(String useraddress_status) {
            this.useraddress_status = useraddress_status;
        }

        public void setUseraddress_userid(String useraddress_userid) {
            this.useraddress_userid = useraddress_userid;
        }

        public void setUseraddress_state(String useraddress_state) {
            this.useraddress_state = useraddress_state;
        }

        public void setAddress_lat(String address_lat) {
            this.address_lat = address_lat;
        }

        public void setAddress_long(String address_long) {
            this.address_long = address_long;
        }

        public void setUseraddress_landmark(String useraddress_landmark) {
            this.useraddress_landmark = useraddress_landmark;
        }

        public void setUseraddress_zipcode(String useraddress_zipcode) {
            this.useraddress_zipcode = useraddress_zipcode;
        }

        public void setUseraddress_city(String useraddress_city) {
            this.useraddress_city = useraddress_city;
        }

        public String getUseraddress_address() {
            return useraddress_address;
        }

        public String getUseraddress_id() {
            return useraddress_id;
        }

        public String getUseraddress_title() {
            return useraddress_title;
        }

        public String getUseraddress_type() {
            return useraddress_type;
        }

        public String getUseraddress_status() {
            return useraddress_status;
        }

        public String getUseraddress_userid() {
            return useraddress_userid;
        }

        public String getUseraddress_state() {
            return useraddress_state;
        }

        public String getAddress_lat() {
            return address_lat;
        }

        public String getAddress_long() {
            return address_long;
        }

        public String getUseraddress_landmark() {
            return useraddress_landmark;
        }

        public String getUseraddress_zipcode() {
            return useraddress_zipcode;
        }

        public String getUseraddress_city() {
            return useraddress_city;
        }
    }
}
