package com.laundry.WebServices;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laundry.Utils.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {

    private static APIClient sApiClient = new APIClient();
    private ApiInterface mApiInterface;

    private APIClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client =new OkHttpClient.Builder()
                .writeTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000,TimeUnit.SECONDS)
                .connectTimeout(5000,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request;
                        request=chain.request()
                                .newBuilder()
                                .addHeader("content-type","application/x-www-form-urlencoded")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mApiInterface = retrofit.create(ApiInterface.class);
    }

    public static APIClient getInstance() {
        return sApiClient;
    }

    public ApiInterface getApiInterface() {
        return mApiInterface;
    }

}
