package com.laundry.ui.MyCart.vo;

import java.util.List;

public class TextResponse {


    /**
     * data : [{"tax_value":"2","tax_status":"1","tax_name":"electric","tax_id":"1","tax_ref":"tax5665"},{"tax_value":"10","tax_status":"1","tax_name":"vat","tax_id":"2","tax_ref":"tacx6936"}]
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
         * tax_value : 2
         * tax_status : 1
         * tax_name : electric
         * tax_id : 1
         * tax_ref : tax5665
         */
        private String tax_value;
        private String tax_status;
        private String tax_name;
        private String tax_id;
        private String tax_ref;
        private String perValue;

        public String getPerValue() {
            return perValue;
        }

        public void setPerValue(String perValue) {
            this.perValue = perValue;
        }

        public void setTax_value(String tax_value) {
            this.tax_value = tax_value;
        }

        public void setTax_status(String tax_status) {
            this.tax_status = tax_status;
        }

        public void setTax_name(String tax_name) {
            this.tax_name = tax_name;
        }

        public void setTax_id(String tax_id) {
            this.tax_id = tax_id;
        }

        public void setTax_ref(String tax_ref) {
            this.tax_ref = tax_ref;
        }

        public String getTax_value() {
            return tax_value;
        }

        public String getTax_status() {
            return tax_status;
        }

        public String getTax_name() {
            return tax_name;
        }

        public String getTax_id() {
            return tax_id;
        }

        public String getTax_ref() {
            return tax_ref;
        }
    }
}
