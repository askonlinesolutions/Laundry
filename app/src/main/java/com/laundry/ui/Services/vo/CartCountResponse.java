package com.laundry.ui.Services.vo;

public class CartCountResponse {


    /**
     * cart_count : 15
     * status : true
     */
    private int cart_count;
    private boolean status;

    public void setCart_count(int cart_count) {
        this.cart_count = cart_count;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCart_count() {
        return cart_count;
    }

    public boolean isStatus() {
        return status;
    }
}
