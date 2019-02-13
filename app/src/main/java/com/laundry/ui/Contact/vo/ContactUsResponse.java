package com.laundry.ui.Contact.vo;

import java.util.List;

public class ContactUsResponse {


    /**
     * data : [{"contact_phone":"9873861094","contact_address":"noida sector 63"}]
     * status : true
     */
    private List<DataEntity> data;
    private boolean status;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        /**
         * contact_phone : 9873861094
         * contact_address : noida sector 63
         */
        private String contact_phone;
        private String contact_address;

        public void setContact_phone(String contact_phone) {
            this.contact_phone = contact_phone;
        }

        public void setContact_address(String contact_address) {
            this.contact_address = contact_address;
        }

        public String getContact_phone() {
            return contact_phone;
        }

        public String getContact_address() {
            return contact_address;
        }
    }
}
