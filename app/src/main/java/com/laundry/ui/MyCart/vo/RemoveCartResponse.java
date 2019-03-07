package com.laundry.ui.MyCart.vo;

public class RemoveCartResponse {


    /**
     * msg : Remove Succcessfully
     * status : true
     */
    private String msg;
    private boolean status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isStatus() {
        return status;
    }
}
