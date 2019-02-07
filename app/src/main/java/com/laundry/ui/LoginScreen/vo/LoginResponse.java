package com.laundry.ui.LoginScreen.vo;

public class LoginResponse {


    /**
     * msg : Successfully LoggedIn
     * user_detail : {"user_id":"42","phone":"54646464","name":"Akhilesh ","email":"ak@gmail.com","username":""}
     * status : true
     */
    private String msg;
    private User_detailEntity user_detail;
    private boolean status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUser_detail(User_detailEntity user_detail) {
        this.user_detail = user_detail;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public User_detailEntity getUser_detail() {
        return user_detail;
    }

    public boolean isStatus() {
        return status;
    }

    public class User_detailEntity {
        /**
         * user_id : 42
         * phone : 54646464
         * name : Akhilesh
         * email : ak@gmail.com
         * username :
         */
        private String user_id;
        private String phone;
        private String name;
        private String email;
        private String username;

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUser_id() {
            return user_id;
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

        public String getUsername() {
            return username;
        }
    }
}
