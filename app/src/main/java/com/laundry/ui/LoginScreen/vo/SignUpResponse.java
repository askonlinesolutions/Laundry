package com.laundry.ui.LoginScreen.vo;

public class SignUpResponse {


    /**
     * msg : User registered successfully
     * data : {"password":"1121434","phone":"87487348","name":"Anjana Verma","email":"sdjfk@dld.ck"}
     * status : true
     */
    private String msg;
    private DataEntity data;
    private boolean status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        /**
         * password : 1121434
         * phone : 87487348
         * name : Anjana Verma
         * email : sdjfk@dld.ck
         */
        private String password;
        private String phone;
        private String name;
        private String email;

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public String getPhone() {
            return phone;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
