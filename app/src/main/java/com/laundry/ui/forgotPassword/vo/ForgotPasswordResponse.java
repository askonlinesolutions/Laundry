package com.laundry.ui.forgotPassword.vo;

public class ForgotPasswordResponse {


    /**
     * msg : Your password send to your email
     * error_code : 101
     * status : true
     */
    private String msg;
    private int error_code;
    private boolean status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public int getError_code() {
        return error_code;
    }

    public boolean isStatus() {
        return status;
    }
}
