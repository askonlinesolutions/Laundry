package com.laundry.ui.settings.vo;

public class SettingResponse {


    /**
     * msg : Update Successfully
     * usermanage_notifi_ord_status : 0
     * status : true
     */
    private String msg;
    private String usermanage_notifi_ord_status;
    private boolean status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUsermanage_notifi_ord_status(String usermanage_notifi_ord_status) {
        this.usermanage_notifi_ord_status = usermanage_notifi_ord_status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public String getUsermanage_notifi_ord_status() {
        return usermanage_notifi_ord_status;
    }

    public boolean isStatus() {
        return status;
    }
}
