package com.laundry.ui.DryCleaner.vo;

import java.util.List;

public class BannerResponse {


    /**
     * data : [{"banner_id":"4","banner_status":"1","banner_image":"download_(1)7.jpg","banner_subheading":"subtest","banner_heading":"tests"},{"banner_id":"5","banner_status":"1","banner_image":"indian-prof3-b11.jpg","banner_subheading":"dfdsf5466","banner_heading":"hello"}]
     * img_url : http://webdevelopmentreviews.net/laundry/upload
     * status : true
     */
    private List<DataEntity> data;
    private String img_url;
    private boolean status;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getImg_url() {
        return img_url;
    }

    public boolean isStatus() {
        return status;
    }

    public class DataEntity {
        /**
         * banner_id : 4
         * banner_status : 1
         * banner_image : download_(1)7.jpg
         * banner_subheading : subtest
         * banner_heading : tests
         */
        private String banner_id;
        private String banner_status;
        private String banner_image;
        private String banner_subheading;
        private String banner_heading;

        public void setBanner_id(String banner_id) {
            this.banner_id = banner_id;
        }

        public void setBanner_status(String banner_status) {
            this.banner_status = banner_status;
        }

        public void setBanner_image(String banner_image) {
            this.banner_image = banner_image;
        }

        public void setBanner_subheading(String banner_subheading) {
            this.banner_subheading = banner_subheading;
        }

        public void setBanner_heading(String banner_heading) {
            this.banner_heading = banner_heading;
        }

        public String getBanner_id() {
            return banner_id;
        }

        public String getBanner_status() {
            return banner_status;
        }

        public String getBanner_image() {
            return banner_image;
        }

        public String getBanner_subheading() {
            return banner_subheading;
        }

        public String getBanner_heading() {
            return banner_heading;
        }
    }
}
