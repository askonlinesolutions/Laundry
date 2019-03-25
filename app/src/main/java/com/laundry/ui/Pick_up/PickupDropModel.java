package com.laundry.ui.Pick_up;

public class PickupDropModel {

    private String pickAddredd;
    private String pickLat;
    private String pickLong;
    private String dropAddredd;
    private String dropLat;
    private String dropLong;
    private String dropDate;
    private String dropTime;
    private String pickDate;
    private String pickTime;
    private String dateTime;

    String getPickAddredd() {
        return pickAddredd;
    }

    void setPickAddredd(String pickAddredd) {
        this.pickAddredd = pickAddredd;
    }

    public String getPickLat() {
        return pickLat;
    }

    void setPickLat(String pickLat) {
        this.pickLat = pickLat;
    }

    public String getPickLong() {
        return pickLong;
    }

    void setPickLong(String pickLong) {
        this.pickLong = pickLong;
    }

    String getDropAddredd() {
        return dropAddredd;
    }

    void setDropAddredd(String dropAddredd) {
        this.dropAddredd = dropAddredd;
    }

    private String getDropLat() {
        return dropLat;
    }

    void setDropLat(String dropLat) {
        this.dropLat = dropLat;
    }

    public String getDropLong() {
        return dropLong;
    }

    void setDropLong(String dropLong) {
        this.dropLong = dropLong;
    }

    String getDropDate() {
        return dropDate;
    }

    void setDropDate(String dropDate) {
        this.dropDate = dropDate;
    }

    String getDropTime() {
        return dropTime;
    }

    void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

    String getPickDate() {
        return pickDate;
    }

    void setPickDate(String pickDate) {
        this.pickDate = pickDate;
    }

    String getPickTime() {
        return pickTime;
    }

    void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    private String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
