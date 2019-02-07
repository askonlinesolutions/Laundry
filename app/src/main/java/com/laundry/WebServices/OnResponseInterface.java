package com.laundry.WebServices;

public interface OnResponseInterface {
    void onApiResponse(Object response);
    void onApiFailure(String message);
}
