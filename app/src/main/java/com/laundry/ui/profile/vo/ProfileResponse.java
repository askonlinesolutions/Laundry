package com.laundry.ui.profile.vo;

public class ProfileResponse {


    /**
     * data : {"usermanage_contact":"9898919168","usermanage_first_name":"jeetesh","usermanage_last_name":"shakya","usermanage_email":"jeetesh@askonlinesolutions.com","usermanage_password":"0144712dd81be0c3d9724f5e56ce6685","usermanage_gender":"male","usermanage_age":"25","usermanage_username":"jeet","usermanage_id":"14","usermanage_status":"1","usermanage_image":"","usermanage_user_notify":"1"}
     * img_url : http://webdevelopmentreviews.net/laundry/upload
     * status : true
     */
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
        /**
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
         */
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
    }
}
