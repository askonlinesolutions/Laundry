package com.laundry.ui.DryCleaner.vo;

import java.util.List;

public class BannerResponse {


    /**
     * data : [{"banner_id":"4","banner_status":"1","banner_image":"download_(1)7.jpg","banner_subheading":"subtest","banner_heading":"tests"},{"banner_id":"5","banner_status":"1","banner_image":"indian-prof3-b11.jpg","banner_subheading":"dfdsf5466","banner_heading":"hello"}]
     * status : true
     */
    public List<DataEntity> data;
    public boolean status;

    public class DataEntity {
        /**
         * banner_id : 4
         * banner_status : 1
         * banner_image : download_(1)7.jpg
         * banner_subheading : subtest
         * banner_heading : tests
         */
        public String banner_id;
        public String banner_status;
        public String banner_image;
        public String banner_subheading;
        public String banner_heading;
    }
}
