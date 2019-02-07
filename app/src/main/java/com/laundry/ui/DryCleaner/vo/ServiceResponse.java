package com.laundry.ui.DryCleaner.vo;

import java.io.Serializable;
import java.util.List;

public class ServiceResponse implements Serializable {


    /**
     * data : [{"service_name":"Steam Press","service_id":"2","service_image":"steam.jpg","service_status":"1","service_hour":"2"},{"service_name":"Onsite Dry Cleaning","service_id":"3","service_image":"images1.png","service_status":"1","service_hour":"3"},{"service_name":"Washing & Iron","service_id":"4","service_image":"download.jpg","service_status":"1","service_hour":"10"},{"service_name":"Dry Cleaning","service_id":"5","service_image":"images1.jpg","service_status":"1","service_hour":"48"},{"service_name":"Express Service","service_id":"6","service_image":"express-dry.jpg","service_status":"1","service_hour":"2"},{"service_name":"Dry wash","service_id":"7","service_image":"indian-prof3-b1.jpg","service_status":"1","service_hour":"20"},{"service_name":"dgdfg","service_id":"15","service_image":"","service_status":"1","service_hour":"2"},{"service_name":"rthrthr","service_id":"16","service_image":"","service_status":"1","service_hour":"4"},{"service_name":"ghfgh","service_id":"17","service_image":"","service_status":"0","service_hour":"4"},{"service_name":"jljklj","service_id":"18","service_image":"","service_status":"1","service_hour":"3"},{"service_name":"gfbvhnvgfh","service_id":"20","service_image":"","service_status":"1","service_hour":"56"},{"service_name":"dfg","service_id":"21","service_image":"","service_status":"1","service_hour":"45"}]
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

    public class DataEntity implements Serializable  {
        /**
         * service_name : Steam Press
         * service_id : 2
         * service_image : steam.jpg
         * service_status : 1
         * service_hour : 2
         */
        private String service_name;
        private String service_id;
        private String service_image;
        private String service_status;
        private String service_hour;

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public void setService_image(String service_image) {
            this.service_image = service_image;
        }

        public void setService_status(String service_status) {
            this.service_status = service_status;
        }

        public void setService_hour(String service_hour) {
            this.service_hour = service_hour;
        }

        public String getService_name() {
            return service_name;
        }

        public String getService_id() {
            return service_id;
        }

        public String getService_image() {
            return service_image;
        }

        public String getService_status() {
            return service_status;
        }

        public String getService_hour() {
            return service_hour;
        }
    }
}
