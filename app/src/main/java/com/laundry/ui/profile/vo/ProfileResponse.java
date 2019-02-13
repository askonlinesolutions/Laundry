package com.laundry.ui.profile.vo;

import java.util.List;

public class ProfileResponse {
    /**
     * payment_card : null
     * offer : [{"discount_status":"1","discount_from":"2018-06-15","discount_coupon_val":"8789","discount_dis_value":"8","discount_type":"percent","discount_coupon":"DIWALI","discount_id":"30","discount_to":"2018-06-22"},{"discount_status":"1","discount_from":"2018-07-18","discount_coupon_val":"holi10","discount_dis_value":"20","discount_type":"percent","discount_coupon":"holi","discount_id":"31","discount_to":"2018-07-20"}]
     * address : null
     * data : {"usermanage_first_name":"aayushi","usermanage_email":"aayu@gmail.com","usermanage_age":"0","usermanage_notifi_ord_status":"1","usermanage_id":"49","usermanage_status":"1","usermanage_image":"5c63e65bb401f.png","usermanage_contact":"1234567890","usermanage_last_name":"","usermanage_notifi_msg_status":"1","usermanage_password":"0a2ede56f6523e16b6a2794c26921580","usermanage_gender":"","usermanage_username":"Akhilesh12","usermanage_user_notify":"0"}
     * img_url : http://webdevelopmentreviews.net/laundry/upload
     * status : true
     */
    private String payment_card;
    private List<OfferEntity> offer;
    private String address;
    private DataEntity data;
    private String img_url;
    private boolean status;

    public void setPayment_card(String payment_card) {
        this.payment_card = payment_card;
    }

    public void setOffer(List<OfferEntity> offer) {
        this.offer = offer;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPayment_card() {
        return payment_card;
    }

    public List<OfferEntity> getOffer() {
        return offer;
    }

    public String getAddress() {
        return address;
    }

    public DataEntity getData() {
        return data;
    }

    public String getImg_url() {
        return img_url;
    }

    public boolean isStatus() {
        return status;
    }

    public class OfferEntity {
        /**
         * discount_status : 1
         * discount_from : 2018-06-15
         * discount_coupon_val : 8789
         * discount_dis_value : 8
         * discount_type : percent
         * discount_coupon : DIWALI
         * discount_id : 30
         * discount_to : 2018-06-22
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

    public class DataEntity {
        /**
         * usermanage_first_name : aayushi
         * usermanage_email : aayu@gmail.com
         * usermanage_age : 0
         * usermanage_notifi_ord_status : 1
         * usermanage_id : 49
         * usermanage_status : 1
         * usermanage_image : 5c63e65bb401f.png
         * usermanage_contact : 1234567890
         * usermanage_last_name :
         * usermanage_notifi_msg_status : 1
         * usermanage_password : 0a2ede56f6523e16b6a2794c26921580
         * usermanage_gender :
         * usermanage_username : Akhilesh12
         * usermanage_user_notify : 0
         */
        private String usermanage_first_name;
        private String usermanage_email;
        private String usermanage_age;
        private String usermanage_notifi_ord_status;
        private String usermanage_id;
        private String usermanage_status;
        private String usermanage_image;
        private String usermanage_contact;
        private String usermanage_last_name;
        private String usermanage_notifi_msg_status;
        private String usermanage_password;
        private String usermanage_gender;
        private String usermanage_username;
        private String usermanage_user_notify;

        public void setUsermanage_first_name(String usermanage_first_name) {
            this.usermanage_first_name = usermanage_first_name;
        }

        public void setUsermanage_email(String usermanage_email) {
            this.usermanage_email = usermanage_email;
        }

        public void setUsermanage_age(String usermanage_age) {
            this.usermanage_age = usermanage_age;
        }

        public void setUsermanage_notifi_ord_status(String usermanage_notifi_ord_status) {
            this.usermanage_notifi_ord_status = usermanage_notifi_ord_status;
        }

        public void setUsermanage_id(String usermanage_id) {
            this.usermanage_id = usermanage_id;
        }

        public void setUsermanage_status(String usermanage_status) {
            this.usermanage_status = usermanage_status;
        }

        public void setUsermanage_image(String usermanage_image) {
            this.usermanage_image = usermanage_image;
        }

        public void setUsermanage_contact(String usermanage_contact) {
            this.usermanage_contact = usermanage_contact;
        }

        public void setUsermanage_last_name(String usermanage_last_name) {
            this.usermanage_last_name = usermanage_last_name;
        }

        public void setUsermanage_notifi_msg_status(String usermanage_notifi_msg_status) {
            this.usermanage_notifi_msg_status = usermanage_notifi_msg_status;
        }

        public void setUsermanage_password(String usermanage_password) {
            this.usermanage_password = usermanage_password;
        }

        public void setUsermanage_gender(String usermanage_gender) {
            this.usermanage_gender = usermanage_gender;
        }

        public void setUsermanage_username(String usermanage_username) {
            this.usermanage_username = usermanage_username;
        }

        public void setUsermanage_user_notify(String usermanage_user_notify) {
            this.usermanage_user_notify = usermanage_user_notify;
        }

        public String getUsermanage_first_name() {
            return usermanage_first_name;
        }

        public String getUsermanage_email() {
            return usermanage_email;
        }

        public String getUsermanage_age() {
            return usermanage_age;
        }

        public String getUsermanage_notifi_ord_status() {
            return usermanage_notifi_ord_status;
        }

        public String getUsermanage_id() {
            return usermanage_id;
        }

        public String getUsermanage_status() {
            return usermanage_status;
        }

        public String getUsermanage_image() {
            return usermanage_image;
        }

        public String getUsermanage_contact() {
            return usermanage_contact;
        }

        public String getUsermanage_last_name() {
            return usermanage_last_name;
        }

        public String getUsermanage_notifi_msg_status() {
            return usermanage_notifi_msg_status;
        }

        public String getUsermanage_password() {
            return usermanage_password;
        }

        public String getUsermanage_gender() {
            return usermanage_gender;
        }

        public String getUsermanage_username() {
            return usermanage_username;
        }

        public String getUsermanage_user_notify() {
            return usermanage_user_notify;
        }
    }


    /**
     * payment_card : null
     * offer :
     * address : null
     * data : {"usermanage_first_name":"aayushi","usermanage_email":"aayu@gmail.com","usermanage_age":"0","usermanage_notifi_ord_status":"1","usermanage_id":"49","usermanage_status":"1","usermanage_image":"","usermanage_contact":"9458706191","usermanage_last_name":"","usermanage_notifi_msg_status":"0","usermanage_password":"0a2ede56f6523e16b6a2794c26921580","usermanage_gender":"","usermanage_username":"","usermanage_user_notify":"0"}
     * img_url : http://webdevelopmentreviews.net/laundry/upload
     * status : true
    private String payment_card;
    private String offer;
    private String address;
    private DataEntity data;
    private String img_url;
    private boolean status;

    public void setPayment_card(String payment_card) {
        this.payment_card = payment_card;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPayment_card() {
        return payment_card;
    }

    public String getOffer() {
        return offer;
    }

    public String getAddress() {
        return address;
    }

    public DataEntity getData() {
        return data;
    }

    public String getImg_url() {
        return img_url;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        *
         * usermanage_first_name : aayushi
         * usermanage_email : aayu@gmail.com
         * usermanage_age : 0
         * usermanage_notifi_ord_status : 1
         * usermanage_id : 49
         * usermanage_status : 1
         * usermanage_image :
         * usermanage_contact : 9458706191
         * usermanage_last_name :
         * usermanage_notifi_msg_status : 0
         * usermanage_password : 0a2ede56f6523e16b6a2794c26921580
         * usermanage_gender :
         * usermanage_username :
         * usermanage_user_notify : 0
        private String usermanage_first_name;
        private String usermanage_email;
        private String usermanage_age;
        private String usermanage_notifi_ord_status;
        private String usermanage_id;
        private String usermanage_status;
        private String usermanage_image;
        private String usermanage_contact;
        private String usermanage_last_name;
        private String usermanage_notifi_msg_status;
        private String usermanage_password;
        private String usermanage_gender;
        private String usermanage_username;
        private String usermanage_user_notify;

        public void setUsermanage_first_name(String usermanage_first_name) {
            this.usermanage_first_name = usermanage_first_name;
        }

        public void setUsermanage_email(String usermanage_email) {
            this.usermanage_email = usermanage_email;
        }

        public void setUsermanage_age(String usermanage_age) {
            this.usermanage_age = usermanage_age;
        }

        public void setUsermanage_notifi_ord_status(String usermanage_notifi_ord_status) {
            this.usermanage_notifi_ord_status = usermanage_notifi_ord_status;
        }

        public void setUsermanage_id(String usermanage_id) {
            this.usermanage_id = usermanage_id;
        }

        public void setUsermanage_status(String usermanage_status) {
            this.usermanage_status = usermanage_status;
        }

        public void setUsermanage_image(String usermanage_image) {
            this.usermanage_image = usermanage_image;
        }

        public void setUsermanage_contact(String usermanage_contact) {
            this.usermanage_contact = usermanage_contact;
        }

        public void setUsermanage_last_name(String usermanage_last_name) {
            this.usermanage_last_name = usermanage_last_name;
        }

        public void setUsermanage_notifi_msg_status(String usermanage_notifi_msg_status) {
            this.usermanage_notifi_msg_status = usermanage_notifi_msg_status;
        }

        public void setUsermanage_password(String usermanage_password) {
            this.usermanage_password = usermanage_password;
        }

        public void setUsermanage_gender(String usermanage_gender) {
            this.usermanage_gender = usermanage_gender;
        }

        public void setUsermanage_username(String usermanage_username) {
            this.usermanage_username = usermanage_username;
        }

        public void setUsermanage_user_notify(String usermanage_user_notify) {
            this.usermanage_user_notify = usermanage_user_notify;
        }

        public String getUsermanage_first_name() {
            return usermanage_first_name;
        }

        public String getUsermanage_email() {
            return usermanage_email;
        }

        public String getUsermanage_age() {
            return usermanage_age;
        }

        public String getUsermanage_notifi_ord_status() {
            return usermanage_notifi_ord_status;
        }

        public String getUsermanage_id() {
            return usermanage_id;
        }

        public String getUsermanage_status() {
            return usermanage_status;
        }

        public String getUsermanage_image() {
            return usermanage_image;
        }

        public String getUsermanage_contact() {
            return usermanage_contact;
        }

        public String getUsermanage_last_name() {
            return usermanage_last_name;
        }

        public String getUsermanage_notifi_msg_status() {
            return usermanage_notifi_msg_status;
        }

        public String getUsermanage_password() {
            return usermanage_password;
        }

        public String getUsermanage_gender() {
            return usermanage_gender;
        }

        public String getUsermanage_username() {
            return usermanage_username;
        }

        public String getUsermanage_user_notify() {
            return usermanage_user_notify;
        }
    }






     *
     * data : {"usermanage_contact":"9898919168","usermanage_first_name":"jeetesh","usermanage_last_name":"shakya","usermanage_email":"jeetesh@askonlinesolutions.com","usermanage_password":"0144712dd81be0c3d9724f5e56ce6685","usermanage_gender":"male","usermanage_age":"25","usermanage_username":"jeet","usermanage_id":"14","usermanage_status":"1","usermanage_image":"","usermanage_user_notify":"1"}
     * img_url : http://webdevelopmentreviews.net/laundry/upload
     * status : true

    private DataEntity data;
    private String img_url;
    private boolean status;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getImg_url() {
        return img_url;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        *
         * usermanage_contact : 9898919168
         * usermanage_first_name : jeetesh
         * usermanage_last_name : shakya
         * usermanage_email : jeetesh@askonlinesolutions.com
         * usermanage_password : 0144712dd81be0c3d9724f5e56ce6685
         * usermanage_gender : male
         * usermanage_age : 25
         * usermanage_username : jeet
         * usermanage_id : 14
         * usermanage_status : 1
         * usermanage_image :
         * usermanage_user_notify : 1

        private String usermanage_contact;
        private String usermanage_first_name;
        private String usermanage_last_name;
        private String usermanage_email;
        private String usermanage_password;
        private String usermanage_gender;
        private String usermanage_age;
        private String usermanage_username;
        private String usermanage_id;
        private String usermanage_status;
        private String usermanage_image;
        private String usermanage_user_notify;

        public void setUsermanage_contact(String usermanage_contact) {
            this.usermanage_contact = usermanage_contact;
        }

        public void setUsermanage_first_name(String usermanage_first_name) {
            this.usermanage_first_name = usermanage_first_name;
        }

        public void setUsermanage_last_name(String usermanage_last_name) {
            this.usermanage_last_name = usermanage_last_name;
        }

        public void setUsermanage_email(String usermanage_email) {
            this.usermanage_email = usermanage_email;
        }

        public void setUsermanage_password(String usermanage_password) {
            this.usermanage_password = usermanage_password;
        }

        public void setUsermanage_gender(String usermanage_gender) {
            this.usermanage_gender = usermanage_gender;
        }

        public void setUsermanage_age(String usermanage_age) {
            this.usermanage_age = usermanage_age;
        }

        public void setUsermanage_username(String usermanage_username) {
            this.usermanage_username = usermanage_username;
        }

        public void setUsermanage_id(String usermanage_id) {
            this.usermanage_id = usermanage_id;
        }

        public void setUsermanage_status(String usermanage_status) {
            this.usermanage_status = usermanage_status;
        }

        public void setUsermanage_image(String usermanage_image) {
            this.usermanage_image = usermanage_image;
        }

        public void setUsermanage_user_notify(String usermanage_user_notify) {
            this.usermanage_user_notify = usermanage_user_notify;
        }

        public String getUsermanage_contact() {
            return usermanage_contact;
        }

        public String getUsermanage_first_name() {
            return usermanage_first_name;
        }

        public String getUsermanage_last_name() {
            return usermanage_last_name;
        }

        public String getUsermanage_email() {
            return usermanage_email;
        }

        public String getUsermanage_password() {
            return usermanage_password;
        }

        public String getUsermanage_gender() {
            return usermanage_gender;
        }

        public String getUsermanage_age() {
            return usermanage_age;
        }

        public String getUsermanage_username() {
            return usermanage_username;
        }

        public String getUsermanage_id() {
            return usermanage_id;
        }

        public String getUsermanage_status() {
            return usermanage_status;
        }

        public String getUsermanage_image() {
            return usermanage_image;
        }

        public String getUsermanage_user_notify() {
            return usermanage_user_notify;
        }
    }*/
}
