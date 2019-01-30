package com.laundry.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private static MySharedPreference instance;
    private transient SharedPreferences prefs;

    private MySharedPreference(Context context) {
        prefs = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
    }

    public static MySharedPreference getInstance(Context context) {
        if (instance == null)
            instance = new MySharedPreference(context);
        return instance;
    }

    public boolean isLogin(){
        if(prefs.contains(Constant.USER_ID)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPhonNo(){
        if(prefs.contains(Constant.PHONE_NO)){
            return true;
        } else {
            return false;
        }
    }

    public void saveUserData(String login_data) {

        prefs.edit().putString(Constant.LOGIN_DATA, login_data).apply();
    }

    public String getUserData(){
        return prefs.getString(Constant.LOGIN_DATA, "");
    }

    public void saveUserId(String user_id){
        prefs.edit().putString(Constant.USER_ID,user_id).apply();
    }
    public void savePhoneNubmber(String phoneNumber){
        prefs.edit().putString(Constant.PHONE_NO,phoneNumber).apply();
    }
    public void saveUserName(String userName){
        prefs.edit().putString(Constant.USER_NAME,userName).apply();
    }

    public void saveTripId(String tripId){
        prefs.edit().putString(Constant.TRIP_ID,tripId).apply();
    }

    public String getUserId(){
        return prefs.getString(Constant.USER_ID, "");
    }

    public String getUserName(){
        return prefs.getString(Constant.USER_NAME, "");
    }
    public String getPhoneNubmber(){
        return prefs.getString(Constant.PHONE_NO, "");
    }

    public String getTripId(){
        return prefs.getString(Constant.TRIP_ID, "");
    }


    public void saveCurrentData(String current_data) {

        prefs.edit().putString(Constant.LOGIN_DATA, current_data).apply();
    }

    public String getCurrentData(){
        return prefs.getString(Constant.CURRENT_DATA, "");
    }

    public void clearUserData(){
        prefs.edit().remove(Constant.LOGIN_DATA).apply();
        prefs.edit().remove(Constant.USER_ID).apply();
    }
}