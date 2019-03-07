package com.laundry.ui.MyCart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityMyCartBinding;

import com.laundry.R;
import com.laundry.ui.MyCart.vo.CartDetailsResponse;
import com.laundry.ui.MyCart.vo.RemoveCartResponse;
import com.laundry.ui.MyPayment.PaymentActivity;
import com.laundry.ui.Services.vo.AddToCartResponse;

import java.util.ArrayList;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class MyCartActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface, MyCartAdapter.OnCartClickListener {

    private RecyclerView mycart_menu;
    private ActivityMyCartBinding binding;
    private static String TAG = MyCartActivity.class.getName();
    private TextView activity_text_discount, text_discount, text_money;
    private boolean isVisible = true;

    private String user_id;
    private ArrayList<CartDetailsResponse.DataEntity> cartDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart);
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        user_id = mySharedPreference.getUserId();
        init();
    }

    private void init() {
        mycart_menu = findViewById(R.id.mycart_menu);
        text_money = findViewById(R.id.text_money);
        text_discount = findViewById(R.id.text_discount);
        activity_text_discount = findViewById(R.id.activity_text_discount);

        binding.activityCartBtnProced.setOnClickListener(this);
        binding.activityCartBtnApply.setOnClickListener(this);
        binding.loginTitle.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mycart_menu.setLayoutManager(linearLayoutManager);

        if (isNetworkConnected(this)) {
            callCartDetailsApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }
    }

    private void setCartAdapter() {
        MyCartAdapter myCartAdapter = new MyCartAdapter(this, cartDetailsList, this);
        mycart_menu.setAdapter(myCartAdapter);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.activity_cart_btn_proced:
                Intent i = new Intent(MyCartActivity.this, PaymentActivity.class);
                startActivity(i);
                break;

            case R.id.activity_cart_btn_apply:
                if (isVisible) {
                    activity_text_discount.setVisibility(View.VISIBLE);
                    text_discount.setTextColor(R.color.dark_grey);
                    text_money.setTextColor(R.color.sky_blue);
                    isVisible = true;
                } else {
                    activity_text_discount.setVisibility(View.VISIBLE);
//                text_discount.setBackgroundColor(R.color.bg_color_2);
                    isVisible = false;
                }
                break;
        }
    }


    @Override
    public void removeItem(int pos, String service_id, String cat_id, String item_id, String quantity, String item_price, String discount_price) {

        if (isNetworkConnected(this)) {
            callRemoveFromCartApi(service_id, cat_id, item_id, quantity, item_price, discount_price);
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void itemDetails(int pos, String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price, String service_id, String cat_id) {

        if (isNetworkConnected(this)) {
            callAddToCartApi(item_name, item_image, item_price, item_qnty, item_id, discount_price, service_id, cat_id);
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }
    }

    private void callAddToCartApi(String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price, String service_id, String cat_id) {
        Call<AddToCartResponse> call = APIClient.getInstance().getApiInterface().addTocard(user_id, service_id, cat_id, item_name, item_id, item_qnty, item_price, item_image, discount_price);
        Log.e("addTocart", call.request().url().toString());
        new ResponseListner(this).getResponse(call);
    }

    private void callRemoveFromCartApi(String service_id, String cat_id, String item_id, String quantity, String item_price, String discount_price) {
        new Utility().showProgressDialog(this);
        Call<RemoveCartResponse> call = APIClient.getInstance().getApiInterface().removeFromCart(user_id, service_id, cat_id, item_id, quantity, item_price, discount_price);
        new ResponseListner(this).getResponse(call);
    }

    private void callCartDetailsApi() {
        new Utility().showProgressDialog(this);
        Call<CartDetailsResponse> call = APIClient.getInstance().getApiInterface().getCartDetails(user_id);
        new ResponseListner(this).getResponse(call);
    }

    @Override
    public void onApiResponse(Object response) {

        new Utility().hideDialog();
        if (response != null) {
            try {
                if (response instanceof CartDetailsResponse) {
                    CartDetailsResponse cartDetailsResponse = (CartDetailsResponse) response;
                    if (cartDetailsResponse.isStatus()) {
                        if (cartDetailsList.size() > 0) {
                            cartDetailsList.clear();
                        }
                        if (cartDetailsResponse.getData().size() > 0) {
                            cartDetailsList.addAll(cartDetailsResponse.getData());
                            setCartAdapter();
                            managData(cartDetailsResponse);
                        } else {
                            Toast.makeText(this, "No Data Available !", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (response instanceof RemoveCartResponse) {
                    RemoveCartResponse removeCartResponse = (RemoveCartResponse) response;
                    if (removeCartResponse.isStatus()) {
                        callCartDetailsApi();
                        Toast.makeText(this, removeCartResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response instanceof AddToCartResponse) {
//                    new Utility().hideDialog();
                    AddToCartResponse cartResponse = (AddToCartResponse) response;
                    if (cartResponse.isData()) {
                        callCartDetailsApi();
                        Toast.makeText(this, cartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                new Utility().hideDialog();
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
            new Utility().hideDialog();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onApiFailure(String message) {
        new Utility().hideDialog();
        Log.d(TAG, "onApiFailure: " + message);
    }


    private void managData(CartDetailsResponse cartDetailsResponse) {

        int totalCart = cartDetailsResponse.getData().size();
        binding.itemNo.setText("Cart SubTotal(" + totalCart + " item" + ")");
        double totatPrice = 0.0;
        for (int i = 0; i < cartDetailsList.size(); i++) {
            int qty = Integer.valueOf(cartDetailsList.get(i).getOrderitem_qty());
            double amount = Double.valueOf(cartDetailsList.get(i).getOrderitem_price());
            double discount = Double.valueOf(cartDetailsList.get(i).getOrderitem_discount_price());

            if (discount > 0) {
                totatPrice = totatPrice + (discount * qty);
            } else {
                totatPrice = totatPrice + (amount * qty);
            }
        }
        binding.totalCartTv.setText("$ " + totatPrice);
    }


}
