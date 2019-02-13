package com.laundry.WebServices;


import com.google.maps.internal.ApiResponse;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.LoginScreen.vo.LoginResponse;
import com.laundry.ui.LoginScreen.vo.SignUpResponse;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.forgotPassword.vo.ForgotPasswordResponse;
import com.laundry.ui.myOrder.vo.MyOrderResponse;
import com.laundry.ui.profile.vo.ProfileResponse;
import com.laundry.ui.settings.vo.SettingResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> actionLogin(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("login/signup")
    Call<SignUpResponse> doSignUp(@Field("name") String name,
                                  @Field("phone") String phone,
                                  @Field("email") String email,
                                  @Field("password") String password
    );

    @POST("services")
    Call<ServiceResponse> getServices();


    @GET("order/index/")
    Call<MyOrderResponse> getOrdersList(@Query("orderdetail_cust_id") String orderdetail_cust_id);

    @FormUrlEncoded
    @POST("login/changepassword")
    Call<ChangePwdResponse> changePwd(@Field("user_id") String user_id,
                                      @Field("oldpass") String oldpass,
                                      @Field("newpass") String newpass
    );

    @FormUrlEncoded
    @POST("login/get_profile")
    Call<ProfileResponse> getProgileDetails(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("login/forgotpassword")
    Call<ForgotPasswordResponse> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("login/setting_notification")
    Call<SettingResponse> setting(@Field("user_id") String user_id,
                                  @Field("notification_order_status") String notification_order_status,
                                  @Field("notification_messageg_statu") String notification_messageg_statu);


    /*
    @POST("get_trip")
    Call<UpcomingModal> getTripApi(@Field("driver_id") String driver_id,
                                   @Field("action") String action);
    @FormUrlEncoded
    @POST("get_trip")
    Call<PickUpResponse> getTripCompletedApi(@Field("driver_id") String driver_id,
                                             @Field("action") String action);

    @FormUrlEncoded
    @POST("changePassword")
    Call<ApiResponse> changePassword(@Field("old_password") String old_password,
                                     @Field("new_password") String new_password,
                                     @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("get_completed_trip")
    Call<ApiResponse> getCompletedTrip(@Field("driver_id") String driver_id);

//    @FormUrlEncoded
//    @POST("pickup_list")
//    Call<UpcomingBookingResponse> getUpcomingBooking(@Field("action") String action,
//                                                     @Field("source_stopage_id") String source_stoppage_id,
//                                                     @Field("trip_id") String trip_id);

    @FormUrlEncoded
    @POST("pickup_list")
    Call<PickUpResponse> getUpcomingPickupBottom(@Field("action") String action,
                                                 @Field("trip_id") String trip_id);

    @FormUrlEncoded
    @POST("pickup_list")
    Call<PickUpResponse> getUpcomingPickup(@Field("action") String action,
                                           @Field("source_stopage_id") String source_stopage_id,
                                           @Field("trip_id") String trip_id);

    @FormUrlEncoded
    @POST("complete_stopage")
    Call<ApiResponse> getCompletedStoppage(@Field("stopage_id") String action);

    @FormUrlEncoded
    @POST("upcoming_booking")
    Call<PickupHomeResponse> getUpcomingPickupHome(@Field("booking_route_id") String booking_route_id,
                                                   @Field("action") String action);

    @FormUrlEncoded
    @POST("drop_list")
    Call<DropResponse> getUpcomingDrop(@Field("action") String action,
                                       @Field("destination_stop_id") String destination_stop_id,
                                       @Field("trip_id") String trip_id);

    @FormUrlEncoded
    @POST("drop_list")
    Call<DropResponse> getUpcomingDropBottom(@Field("action") String action,
                                             @Field("trip_id") String trip_id);


    @FormUrlEncoded
    @POST("upcoming_booking")
    Call<DropHomeResponse> getUpcomingDropHome(@Field("action") String action,
                                               @Field("booking_route_id") String booking_route_id);

    @GET("current_trip_status")
    Call<BookingStatusResponse> getTripStatus(@Query("trip_id") String trip_id);

    @GET("trip_detail")
    Call<TripDetailsResponse> getTripDetails(@Query("trip_id") String trip_id);

    @FormUrlEncoded
    @POST("current_trip")
    Call<CurrentTripResponse> getCurrentTrip(@Field("driver_id") String driver_id);


    @FormUrlEncoded
    @POST("current_trip")
    Call<TripStoppegeResponse> getTripStoppege(@Field("driver_id") int driver_id);

    @GET("booking_detail")
    Call<BookingDetailsResponse> getBookingDetails(@Query("booking_id") String booking_id);


    @FormUrlEncoded
    @POST("update_booking_status")
    Call<BreakDownResponse> getBreakDownStatus(@Field("action") int booking_id,
                                               @Field("user_id") int user_id,
                                               @Field("driver_id") int driver_id,
                                               @Field("action") String action);

    @GET("conversation_list/{user_id}")
    Call<MessageResponse> getMessageList(@Query("user_id") String user_id);


    @GET("chat_messages/{conversation_id}")
    Call<ConversationResponse> getConversationList(@Query("conversation_id") String conversation_id);


//    @FormUrlEncoded
//    @POST("upcoming_booking")
//    Call<UpcomingStopDropRes> getUpcomingStopDrop(@Field("stopage_id") String stopage_id,
//                                                  @Field("booking_route_id") String booking_route_id,
//                                                  @Field("action") String action);


    @POST("change_user_settings")
    Call<SettingResponse> changeUserSetting(@Body SettingRequest settingRequest);



    @GET("booking_list/{action},{booking_route_id}")
    Call<CancelledTripResponse> getCancelledResponse(@Query("action") String action,
                                                     @Query("booking_route_id") String booking_route_id);


    @FormUrlEncoded
    @POST("change_user_settings")
    Call<SettingResponse> changeUserSetting(@Field("setting_user_id") String setting_user_id,
                                            @Field("notification_client_message") int notification_client_message,
                                            @Field("notification_new_order") int notification_new_order,
                                            @Field("notification_pickup_alert") int notification_pickup_alert,
                                            @Field("notification_drop_alert") int notification_drop_alert,
                                            @Field("pickup_alert_miles") int pickup_alert_miles,
                                            @Field("drop_alert_miles") int drop_alert_miles
    );


    @FormUrlEncoded
    @POST("change_phonenumber")
    Call<UpdatePhoneResponse> updatePhoneNo(@Field("user_id") String user_id,
                                            @Field("phone_number") String phone_number);


//    @FormUrlEncoded
//    @POST("conversation")
//    Call<ChatResponse> getChatConversation(@Field("from_id") String from_id,
//                                           @Field("to_id") String to_id,
//                                           @Field("message") String message);

    //
    @POST("conversation")
    Call<ChatResponse> getChatConversation(@Body ChatRequest chatRequest);


    @GET("view_notification_log/{to_id}")
    Call<NotificationResponse> getNotifications(@Query("to_id") String to_id);


    @FormUrlEncoded
    @POST("get_report_tocompany")
    Call<ApiResponse> getReportToCompany(@Field("types_message") String types_message,
                                         @Field("company_id") String company_id,
                                         @Field("stoppage_location") String stoppage_location,
                                         @Field("deiver_id") String deiver_id);


    @FormUrlEncoded
    @POST("get_report_tocompany")
    Call<ApiResponse> getReportToCompany(@Field("types_message") String types_message,
                                         @Field("company_id") String company_id,
                                         @Field("stoppage_location") String stoppage_location,
                                         @Field("deiver_id") String deiver_id,
                                         @Field("image_name") String image_name
    );

    @GET("view_report_tocompany/{driver_id}")
    Call<ReportToCompanyResponse> getViewReportToCompany(@Query("driver_id") String driver_id);


    @FormUrlEncoded
    @POST("search_by_order_custumer")
    Call<SearchResponse> getSearchData(@Field("order_no") String order_no,
                                       @Field("customer_name") String customer_name);*/

//    int setting_user_id;
//    private int notification_client_message;
//    private int notification_new_order;
//    private int notification_pickup_alert;
//    private int notification_drop_alert;
//    private int pickup_alert_miles;
//    private int drop_alert_miles;
//


//    @FormUrlEncoded
//    @POST("conversation")
//    Call<ChatResponse> getChatConversation(@Body ChatRequest chatRequest);
//


}

