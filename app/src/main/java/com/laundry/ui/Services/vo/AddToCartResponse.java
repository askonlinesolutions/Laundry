package com.laundry.ui.Services.vo;

public class AddToCartResponse {
    /**
     * data : true
     * count : 12
     * message : Add Successfully
     * status : 1
     */
    private boolean data;
    private int count;
    private String message;
    private int status;

    public void setData(boolean data) {
        this.data = data;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isData() {
        return data;
    }

    public int getCount() {
        return count;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }



    /*
    *//**
     * data : true
     * message : Add Successfully
     * status : 1
     *//*
    private boolean data;
    private String message;
    private int status;

    public void setData(boolean data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }*/
}
