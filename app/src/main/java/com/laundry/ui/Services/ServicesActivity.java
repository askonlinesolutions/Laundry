package com.laundry.ui.Services;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.SharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityServicesBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.Pick_up.PickupActivity;
import com.laundry.ui.Services.vo.AddToCartResponse;
import com.laundry.ui.Services.vo.CartCountResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class ServicesActivity extends AppCompatActivity implements CategoryLisAdapter.CategoryListInterface,
        View.OnClickListener, CategoryItemAdapter.CategoryItemClickLictner, ServicesAdapter.ServicesAdapterInterface, OnResponseInterface {

    private ActivityServicesBinding binding;
    private ServicesAdapter servicesAdapter;
    private CategoryItemAdapter categoryItemAdapter;
    private CategoryLisAdapter categoryLisAdapter;
    private int pos;
    String serviceId, cat_id, user_id,orderTime;
    private ArrayList<ServiceResponse.DataEntity> serviseList = new ArrayList<>();
    private List<ServiceResponse.DataEntity.CategoryEntity> categoryList = new ArrayList<>();
    private List<ServiceResponse.DataEntity.CategoryEntity.ItemsEntity> categoryItemsList = new ArrayList<>();
    private static String TAG = ServicesActivity.class.getName();
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_services);

        getPosition();
        init();
    }

    private void init() {

        binding.loginTitle.setOnClickListener(this);
        binding.imgMyCart.setOnClickListener(this);
        binding.schedulePickupTv.setOnClickListener(this);
        setHorizontalRecycler();

        if (isNetworkConnected(this)) {
            callCartCountApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }


    }


    private void getPosition() {

        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        user_id = mySharedPreference.getUserId();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pos = extras.getInt("pos");
//            serviseList=getIntent().getSerializableExtra("arraylist");
        }

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        if (args != null) {
            serviseList = (ArrayList<ServiceResponse.DataEntity>) args.getSerializable("ARRAYLIST");

        }


    }

    private void setHorizontalRecycler() {

        setUpServiceAdapter();

        setUpCategoryListAdapter();
//
        setUpCategoryItemListAdapter();


    }

    private void setUpCategoryItemListAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        binding.categoryItemRv.setLayoutManager(layoutManager);
        categoryItemAdapter = new CategoryItemAdapter(this, categoryItemsList, this);
        binding.categoryItemRv.setAdapter(categoryItemAdapter);
    }

    private void setUpCategoryListAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                false);

        binding.categoryRv.setLayoutManager(linearLayoutManager);
        categoryLisAdapter = new CategoryLisAdapter(this, this/*this*/, categoryList);
        binding.categoryRv.setAdapter(categoryLisAdapter);

    }

    private void setUpServiceAdapter() {

        LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                false);
        binding.servicesRv.setLayoutManager(linearManager);
        servicesAdapter = new ServicesAdapter(this, this, pos, serviseList);
        binding.servicesRv.setAdapter(servicesAdapter);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_my_cart:
                Intent i = new Intent(ServicesActivity.this, MyCartActivity.class);
                startActivity(i);
                break;

            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.schedule_pickup_tv:


                Intent intent = new Intent(ServicesActivity.this, MyCartActivity.class);
                startActivity(intent);
//                finish();
                break;
        }
    }


    @Override
    public void onCategryItemClick(int adapterPosition, int qnty) {

        if (qnty == 0) {
            binding.schedulePickupTv.setBackgroundColor(getResources().getColor(R.color.gray));
            binding.schedulePickupTv.setEnabled(false);

        } else if (qnty > 0) {
            binding.schedulePickupTv.setBackgroundColor(getResources().getColor(R.color.sky_blue));
            binding.schedulePickupTv.setEnabled(true);
        }
//        if (isNetworkConnected(this)) {
//            callAddToCartApi();
//        } else {
//            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void itemDetails(int pos, String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price) {

        if (isNetworkConnected(this)) {
            callAddToCartApi(item_name, item_image, item_price, item_qnty, item_id, discount_price);
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onCategoryClicked(int pos, String category_id) {

        cat_id = category_id;
        if (categoryItemsList.size() != 0) {
            categoryItemsList.clear();
            categoryItemsList.addAll(categoryList.get(pos).getItems());
//            setUpCategoryItemListAdapter();
            categoryItemAdapter.notifyDataSetChanged();

        } else {
            categoryItemsList.addAll(categoryList.get(pos).getItems());
//            setUpCategoryItemListAdapter();
            categoryItemAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void onServicesClicked(int pos, String service_id,String order_Time) {
        serviceId = service_id;
        orderTime=order_Time;
        if (categoryList.size() != 0) {
            categoryList.clear();
            categoryList.addAll(serviseList.get(pos).getCategory());
//            setUpCategoryListAdapter();
            categoryLisAdapter.notifyDataSetChanged();
        } else {
            categoryList.addAll(serviseList.get(pos).getCategory());
//        setUpCategoryListAdapter();

            categoryLisAdapter.notifyDataSetChanged();
        }

    }

    private void callCartCountApi() {
//        new Utility().showProgressDialog(this);
        Call<CartCountResponse> call = APIClient.getInstance().getApiInterface().getCartCount(user_id);
        new ResponseListner(this).getResponse(call);
    }


    private void callAddToCartApi(String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price) {
        Call<AddToCartResponse> call = APIClient.getInstance().getApiInterface().addTocard(user_id, serviceId, cat_id, item_name, item_id, item_qnty, item_price, item_image, discount_price,orderTime);
        Log.e("addTocart", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {
//        new Utility().hideDialog();
        if (response != null) {
            try {
                if (response instanceof AddToCartResponse) {
//                    new Utility().hideDialog();
                    AddToCartResponse cartResponse = (AddToCartResponse) response;
                    if (cartResponse.isData()) {
//                        callCartCountApi();
                        count = cartResponse.getCount();
                        binding.cartCountTv.setText(String.valueOf(count/*cartCountResponse.getCart_count()*/));
                        Toast.makeText(this, cartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response instanceof CartCountResponse) {
//                    new Utility().hideDialog();
                    CartCountResponse cartCountResponse = (CartCountResponse) response;
                    if (cartCountResponse.isStatus()) {
//                        Toast.makeText(this, "count...24", Toast.LENGTH_SHORT).show();
                        if (count > 0) {
                            count = 0;
                        }
                        count = cartCountResponse.getCart_count();

                        binding.cartCountTv.setText(String.valueOf(count/*cartCountResponse.getCart_count()*/));
                    }
                } else {

                }


            } catch (Exception e) {
//                new Utility().hideDialog();
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
//            new Utility().hideDialog();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onApiFailure(String message) {
//        new Utility().hideDialog();
        Log.d(TAG, "onApiFailure: " + message);
    }
}

