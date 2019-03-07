package com.laundry.ui.myOrderDetails;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityMyOrderDetailsBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.help.HelpActivity;
import com.laundry.ui.myOrder.MyOrderAdapter;
import com.laundry.ui.myOrderDetails.vo.OrderDetailsResponse;
import com.laundry.ui.settings.vo.SettingResponse;

import java.util.ArrayList;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class MyOrderDetailsActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {
    private ActivityMyOrderDetailsBinding binding;
    private boolean isTrue = true;
    String orderId, userId;
    private static String TAG = MyOrderDetailsActivity.class.getName();
    ArrayList<OrderDetailsResponse.DataEntity.ItemsEntity> orderItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order_details);
        getOrderId();
        init();
    }


    private void getOrderId() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderId = extras.getString("orderId");
//            serviseList=getIntent().getSerializableExtra("arraylist");
        }
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);

    }


    private void init() {

        binding.viewDetailsTv.setOnClickListener(this);
        binding.backIv.setOnClickListener(this);
        binding.helpTv.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.orderItemRv.setLayoutManager(linearLayoutManager);

        if (isNetworkConnected(this)) {
            callOrderDetailsApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_details_tv:
                if (isTrue) {

                    binding.detaileLayout.setVisibility(View.VISIBLE);
                    binding.helpTv.setVisibility(View.VISIBLE);
                    binding.viewDetailsTv.setBackground(getResources().getDrawable(R.drawable.background_button_sky));
                    binding.viewDetailsTv.setTextColor(getResources().getColor(R.color.white_color));
                    isTrue = false;


                } else {

                    binding.detaileLayout.setVisibility(View.GONE);
                    binding.helpTv.setVisibility(View.INVISIBLE);
                    binding.viewDetailsTv.setBackground(getResources().getDrawable(R.drawable.sky_blue_hollow_background));
                    binding.viewDetailsTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    isTrue = true;
                }
                break;
            case R.id.back_iv:
                onBackPressed();
                break;

            case R.id.help_tv:
                Intent intent = new Intent(MyOrderDetailsActivity.this, HelpActivity.class);
                startActivity(intent);

                break;
        }
    }

    private void setAdapter() {
        OrderDetailsAdapter myOrderAdapter = new OrderDetailsAdapter(this, orderItemList);
        binding.orderItemRv.setAdapter(myOrderAdapter);

    }

    private void callOrderDetailsApi() {
        new Utility().showProgressDialog(this);
        Call<OrderDetailsResponse> call = APIClient.getInstance().getApiInterface().getOrderDetails(orderId/*"18"*/,/*"14"*/userId);
        new ResponseListner(this).getResponse(call);

    }

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof OrderDetailsResponse) {
                    OrderDetailsResponse orderDetailsResponse = (OrderDetailsResponse) response;
                    new Utility().hideDialog();
                    if (orderDetailsResponse.isStatus()) {
                        if (orderDetailsResponse.getData() != null) {

                            binding.orderNoTv.setText(orderDetailsResponse.getData().getOrderdetail_order_no());
                            binding.orderItemTv.setText("Items " + orderDetailsResponse.getData().getItemcount() + ",");
                            binding.orderAmountTv.setText("Ammount $" + orderDetailsResponse.getData().getOrderdetail_tax_amount());

                            binding.pickupAddress.setText(orderDetailsResponse.getData().getOrderdetail_pick_address());
                            binding.pickupDateTv.setText(orderDetailsResponse.getData().getOrderdetail_pickup());
                            binding.pickupTimeTv.setText(orderDetailsResponse.getData().getOrderdetail_pickup_time());
                            binding.deliveryAddress.setText(orderDetailsResponse.getData().getOrderdetail_drop_address());
                            binding.deliveryDate.setText(orderDetailsResponse.getData().getOrderdetail_drop());
                            binding.deliveryTime.setText(orderDetailsResponse.getData().getOrderdetail_drop_time());

                            binding.totalItemPriseTv.setText(orderDetailsResponse.getData().getItemcount());


//binding.                            Toast.makeText(this, orderDetailsResponse.getData().getOrderdetail_id(), Toast.LENGTH_SHORT).show();
                            if (orderDetailsResponse.getData() != null && orderDetailsResponse.getData().getItems().size() != 0) {
                                orderItemList.addAll(orderDetailsResponse.getData().getItems());

                                setAdapter();
                            }
                        }


                    }
                }

            } catch (Exception e) {
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
}
