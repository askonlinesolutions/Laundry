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
import com.laundry.ui.MyCart.vo.TextResponse;
import com.laundry.ui.MyPayment.PaymentActivity;
import com.laundry.ui.Pick_up.PickupActivity;
import com.laundry.ui.Services.ServicesActivity;
import com.laundry.ui.Services.vo.AddToCartResponse;
import com.laundry.ui.offer.OfferActivity;
import com.laundry.ui.profile.ProfileActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class MyCartActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface, MyCartAdapter.OnCartClickListener {

    private RecyclerView mycart_menu;
    private ActivityMyCartBinding binding;
    private static String TAG = MyCartActivity.class.getName();
    private TextView activity_text_discount, text_discount, text_money;
    private boolean isVisible = true, flag = true;
    double totatAmount = 0.0, disscount, dissPricePer, total = 0.0, servicTex = 0.0, maxItemTIme;
    LinearLayoutManager linearLayoutManager;
    private String user_id, offerCode, disscountPrice;
    private ArrayList<CartDetailsResponse.DataEntity> cartDetailsList = new ArrayList<>();
    private ArrayList<TextResponse.DataEntity> textList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart);

        getData();
        init();
    }

    private void getData() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        user_id = mySharedPreference.getUserId();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            offerCode = extras.getString("offerCode");
            disscountPrice = extras.getString("disscountPrice");

            if (disscountPrice != null) {
                disscount = Double.parseDouble(disscountPrice);
            }
        }
    }

    private void init() {

        mycart_menu = findViewById(R.id.mycart_menu);
        text_money = findViewById(R.id.text_money);
        text_discount = findViewById(R.id.text_discount);
        activity_text_discount = findViewById(R.id.activity_text_discount);

        binding.activityCartBtnProced.setOnClickListener(this);
        binding.activityCartBtnApply.setOnClickListener(this);
        binding.loginTitle.setOnClickListener(this);
        binding.servicTextLayout.setOnClickListener(this);
        binding.cartOfferCodeEt.setOnClickListener(this);

        if (offerCode != null) {
            binding.cartOfferCodeEt.setText(offerCode);
        }

        binding.servicTextRv.setVisibility(View.GONE);
        if (isNetworkConnected(this)) {
            callCartDetailsApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

        if (isNetworkConnected(this)) {

//            callTextListApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }
    }


    private void setCartAdapter() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mycart_menu.setLayoutManager(linearLayoutManager);
        MyCartAdapter myCartAdapter = new MyCartAdapter(this, cartDetailsList, this);
        mycart_menu.setAdapter(myCartAdapter);
    }

    private void setServiceTextAdapter() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.servicTextRv.setLayoutManager(linearLayoutManager);
        ServiceTextAdapter myCartAdapter = new ServiceTextAdapter(this, textList);
        binding.servicTextRv.setAdapter(myCartAdapter);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.activity_cart_btn_proced:
                Intent intent = new Intent(MyCartActivity.this, PickupActivity.class);
                intent.putExtra("itemTime", maxItemTIme);
                startActivity(intent);
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
            case R.id.cart_offer_code_et:
                Intent intentOffer = new Intent(MyCartActivity.this, OfferActivity.class);
                intentOffer.putExtra("offer", "cart");
                startActivity(intentOffer);
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


            case R.id.servic_text_layout:
                if (flag) {
                    binding.servicTextRv.setVisibility(View.GONE);
                    binding.arrow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_black, 0);
                    flag = false;
                } else {
                    binding.servicTextRv.setVisibility(View.VISIBLE);
                    binding.arrow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up_arrow_black, 0);

                    flag = true;
                }
                break;


            case R.id.activity_cart_btn_apply:

                calulateDisscount();

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
    public void itemDetails(int pos, String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price, String service_id, String cat_id, String order_time) {

        if (isNetworkConnected(this)) {
            callAddToCartApi(item_name, item_image, item_price, item_qnty, item_id, discount_price, service_id, cat_id, order_time);
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }
    }

    private void callTextListApi() {
        Call<TextResponse> call = APIClient.getInstance().getApiInterface().getTextList();
        Log.e("text", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }

    private void callAddToCartApi(String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price, String service_id, String cat_id, String order_time) {
        Call<AddToCartResponse> call = APIClient.getInstance().getApiInterface().addTocard(user_id, service_id, cat_id, item_name, item_id, item_qnty, item_price, item_image, discount_price, order_time);
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
                            callTextListApi();
                        } else {
                            Toast.makeText(this, "No Data Available !", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (response instanceof RemoveCartResponse) {
                    RemoveCartResponse removeCartResponse = (RemoveCartResponse) response;
                    if (removeCartResponse.isStatus()) {
                        callCartDetailsApi();
//                        callTextListApi();
                        Toast.makeText(this, removeCartResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response instanceof AddToCartResponse) {
//                    new Utility().hideDialog();
                    AddToCartResponse cartResponse = (AddToCartResponse) response;
                    if (cartResponse.isData()) {
                        callCartDetailsApi();
//                        callTextListApi();
                        Toast.makeText(this, cartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, cartResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else if (response instanceof TextResponse) {
                    TextResponse textResponse = (TextResponse) response;
                    if (textResponse.isStatus()) {
                        textList.clear();
                        if (textResponse.getData() != null && textResponse.getData().size() > 0) {
                            textList.addAll(textResponse.getData());
                            calculatePer(textResponse);
                        }

                    } else {

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

        if (totatAmount != 0) {
            totatAmount = 0;
        }
        int totalCart = cartDetailsResponse.getData().size();
        binding.itemNo.setText("Cart SubTotal(" + totalCart + " item" + ")");
        double totatPrice = 0.0;
        maxItemTIme = Double.valueOf(cartDetailsList.get(0).getOrderitem_time());
        for (int i = 0; i < cartDetailsList.size(); i++) {
            int qty = Integer.valueOf(cartDetailsList.get(i).getOrderitem_qty());
            double amount = Double.valueOf(cartDetailsList.get(i).getOrderitem_price());
            double discount = Double.valueOf(cartDetailsList.get(i).getOrderitem_discount_price());

            if (discount > 0) {
                totatPrice = totatPrice + (discount * qty);
            } else {
                totatPrice = totatPrice + (amount * qty);
            }

//            int itemTime=0;
            if (Double.valueOf(cartDetailsList.get(i).getOrderitem_time()) > maxItemTIme) {
                maxItemTIme = Double.valueOf(cartDetailsList.get(i).getOrderitem_time());
            }

        }
        binding.totalCartTv.setText("$ " + new DecimalFormat("##.##").format(totatPrice));
        totatAmount = totatPrice;


    }

    private void calculatePer(TextResponse textResponse) {
//        double servicTex = 0.0 /*totatPrice*//* = 0.0*/;
        servicTex = 0.0;
//        totatPrice = Double.valueOf(binding.totalCartTv.getText().toString());
        for (int i = 0; i < textList.size(); i++) {
            double per = Double.valueOf(textList.get(i).getTax_value());
            double res = (totatAmount / 100) * per;
            textList.get(i).setPerValue(String.valueOf(res));

            servicTex = servicTex + res;
        }
        binding.serviceTexTv.setText("$ " + new DecimalFormat("##.##").format(servicTex));
        setServiceTextAdapter();
//        double total;

        if (disscount != 0) {
            dissPricePer = (totatAmount / 100) * disscount;
            binding.textMoney.setText("- $ " + new DecimalFormat("##.##").format(dissPricePer));

            total = totatAmount + servicTex - dissPricePer;
            binding.totalPriceTv.setText("$ " + new DecimalFormat("##.##").format(total));
        } else {
            total = totatAmount + servicTex;
            binding.totalPriceTv.setText("$ " + new DecimalFormat("##.##").format(total));
        }

    }

    private void calulateDisscount() {

        if (disscountPrice != null) {
//                    if (isVisible) {
            activity_text_discount.setVisibility(View.VISIBLE);
            text_discount.setTextColor(getResources().getColor(R.color.dark_grey));
            text_money.setTextColor(getResources().getColor(R.color.sky_blue));
//            dissPricePer = (totatAmount / 100) * disscount;
            dissPricePer = 0;

            if (disscount != 0) {
                dissPricePer = (totatAmount / 100) * disscount;
//            binding.textMoney.setText("- $ " + new DecimalFormat("##.##").format(dissPricePer));

                total = totatAmount + servicTex - dissPricePer;
                binding.totalPriceTv.setText("$ " + new DecimalFormat("##.##").format(total));
            } else {
                total = totatAmount + servicTex;
                binding.totalPriceTv.setText("$ " + new DecimalFormat("##.##").format(total));
            }
            binding.textMoney.setText("- $ " + new DecimalFormat("##.##").format(dissPricePer));
            isVisible = true;
            Toast.makeText(this, "Offer Applied SuccsessFully..", Toast.LENGTH_SHORT).show();
        } else {
            activity_text_discount.setVisibility(View.GONE);
            binding.textMoney.setText("0");
            isVisible = false;
            Toast.makeText(this, "Sorry! This Offer is not valid for You.", Toast.LENGTH_SHORT).show();

        }
//                } else {
//
//                }

    }


}
