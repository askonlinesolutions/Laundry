package com.laundry.WebServices;


import com.google.maps.internal.ApiResponse;
import com.laundry.ui.AddNewAddress.vo.AddAddressResponse;
import com.laundry.ui.Contact.vo.ContactUsResponse;
import com.laundry.ui.DryCleaner.vo.BannerResponse;
import com.laundry.ui.DryCleaner.vo.LogoutResponse;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.FAQ.vo.FaqResponse;
import com.laundry.ui.LoginScreen.vo.LoginResponse;
import com.laundry.ui.LoginScreen.vo.SignUpResponse;
import com.laundry.ui.MyCart.vo.CartDetailsResponse;
import com.laundry.ui.MyCart.vo.RemoveCartResponse;
import com.laundry.ui.MyCart.vo.TextResponse;
import com.laundry.ui.MyPayment.vo.AddPaymentCardResponse;
import com.laundry.ui.MyPayment.vo.PaymentDeleteResponse;
import com.laundry.ui.Services.vo.AddToCartResponse;
import com.laundry.ui.Services.vo.CartCountResponse;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.editProfile.vo.EditProfileResponse;
import com.laundry.ui.forgotPassword.vo.ForgotPasswordResponse;
import com.laundry.ui.manageAddress.vo.DeleteAddressResponse;
import com.laundry.ui.manageAddress.vo.ManageAddressResponse;
import com.laundry.ui.manageAddress.vo.UpdateAddressStatus;
import com.laundry.ui.myOrder.vo.MyOrderResponse;
import com.laundry.ui.myOrderDetails.vo.OrderDetailsResponse;
import com.laundry.ui.offer.vo.OfferResponse;
import com.laundry.ui.profile.vo.ProfileResponse;
import com.laundry.ui.settings.vo.SettingResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> actionLogin(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("device_id") String device_id,
                                    @Field("device_type") String device_type);

    @FormUrlEncoded
    @POST("login/signup")
    Call<SignUpResponse> doSignUp(@Field("name") String name,
                                  @Field("phone") String phone,
                                  @Field("email") String email,
                                  @Field("password") String password);

    @POST("services")
    Call<ServiceResponse> getServices();


    @GET("order/index/")
    Call<MyOrderResponse> getOrdersList(@Query("orderdetail_cust_id") String orderdetail_cust_id);

    @FormUrlEncoded
    @POST("login/changepassword")
    Call<ChangePwdResponse> changePwd(@Field("user_id") String user_id,
                                      @Field("oldpass") String oldpass,
                                      @Field("newpass") String newpass);

    @FormUrlEncoded
    @POST("login/get_profile")
    Call<ProfileResponse> getProgileDetails(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("login/forgotpassword")
    Call<ForgotPasswordResponse> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("login/setting_notification")
    Call<SettingResponse> updateSetting(@Field("user_id") String user_id,
                                        @Field("notification_order_status") int notification_order_status,
                                        @Field("notification_messageg_status") int notification_messageg_status);


    @FormUrlEncoded
    @POST("login/Update_profile")
    Call<EditProfileResponse> editProile(@Field("user_id") String user_id,
                                         @Field("user_name") String user_name,
                                         @Field("contact") String contact,
                                         @Field("user_img") String user_img);


    @POST("faq/contact")
    Call<ContactUsResponse> getContacts();


    @FormUrlEncoded
    @POST("order/order_by_orderId")
    Call<OrderDetailsResponse> getOrderDetails(@Field("order_id") String order_id,
                                               @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("login/get_address")
    Call<ManageAddressResponse> getAddressList(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("login/delete_address")
    Call<DeleteAddressResponse> deleteAddress(@Field("user_id") String user_id,
                                              @Field("address_id") String address_id);

    @FormUrlEncoded
    @POST("login/update_address_status")
    Call<UpdateAddressStatus> updateAddressStatus(@Field("user_id") String user_id,
                                                  @Field("address_id") String address_id,
                                                  @Field("status") String status);


    @POST("faq")
    Call<FaqResponse> getfaq();

    @FormUrlEncoded
    @POST("login/log_out")
    Call<LogoutResponse> getlogout(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("login/add_useraddress")
    Call<AddAddressResponse> addAddress(@Field("user_id") String user_id,
                                        @Field("zipcode") String zipcode,
                                        @Field("address_title") String address_title,
                                        @Field("state") String state,
                                        @Field("address") String address,
                                        @Field("city") String city,
                                        @Field("landmark") String landmark,
                                        @Field("address_lat") Double address_lat,
                                        @Field("address_long") Double address_long);


    @POST("faq")
    Call<BannerResponse> getbanner();

    @FormUrlEncoded
    @POST("order/card_add")
    Call<AddPaymentCardResponse> addPaymentCard(@Field("card_type") String card_type,
                                                @Field("card_no") String card_no,
                                                @Field("card_trans") String card_trans,
                                                @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("order/card_delete")
    Call<PaymentDeleteResponse> getpaymentdelete(@Field("usercard_id") String usercard_id);


    @FormUrlEncoded
    @POST("login/ed_address")
    Call<DeleteAddressResponse> editAddress(@Field("address_id") String address_id);


    @FormUrlEncoded
    @POST("cart/add_to_cart")
    Call<AddToCartResponse> addTocard(@Field("user_id") String user_id,
                                      @Field("service_id") String service_id,
                                      @Field("cat_id") String cat_id,
                                      @Field("item_name") String item_name,
                                      @Field("item_id") String item_id,
                                      @Field("item_quantity") int item_quantity,
                                      @Field("item_price") String item_price,
                                      @Field("item_image") String item_image,
                                      @Field("discount_price") String discount_price,
                                      @Field("order_time") String order_time);

    @FormUrlEncoded
    @POST("cart/cart_count")
    Call<CartCountResponse> getCartCount(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("cart/cart_detail")
    Call<CartDetailsResponse> getCartDetails(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("cart/remove_from_cart")
    Call<RemoveCartResponse> removeFromCart(@Field("user_id") String user_id,
                                            @Field("service_id") String service_id,
                                            @Field("cat_id") String cat_id,
                                            @Field("item_id") String item_id,
                                            @Field("item_quantity") String item_quantity,
                                            @Field("item_price") String item_price,
                                            @Field("discount_price") String discount_price);

    @POST("cart/taxlist")
    Call<TextResponse> getTextList();


    @POST("cart/discountlist")
    Call<OfferResponse> getOfferList();


//    @GET("logistic_rating")
//    Call<ApiResponse> getlogistic(@Query("user_id") String user_id);


}


//http://pikship.com/api/customer/logistic_rating?user_id=50