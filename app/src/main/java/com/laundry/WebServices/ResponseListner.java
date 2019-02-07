package com.laundry.WebServices;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseListner {

    private OnResponseInterface onResponseInterface;
    private String message;

    public ResponseListner(OnResponseInterface onResponseInterface) {
        this.onResponseInterface = onResponseInterface;
    }

    public void getResponse(Call call) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response ) {
                message = response.message();
                onResponseInterface.onApiResponse(response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onResponseInterface.onApiFailure(message);
            }
        });
    }


}
