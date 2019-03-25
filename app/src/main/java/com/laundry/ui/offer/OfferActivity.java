package com.laundry.ui.offer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityOfferBinding;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.MyCart.vo.CartDetailsResponse;
import com.laundry.ui.MyCart.vo.TextResponse;
import com.laundry.ui.help.HelpAdapter;
import com.laundry.ui.offer.vo.OfferResponse;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface, OfferrAdapter.OfferItemClickListener {

    private ActivityOfferBinding binding;
    private OfferrAdapter offerrAdapter;
    private ArrayList<OfferResponse.DataEntity> offerList = new ArrayList<>();
    private String offerKye, from_date, to_date, disscountPrice, offerCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_offer);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            offerKye = extras.getString("offer");

//            longitute = extras.getDouble("longitute");
//            latitute = extras.getDouble("latitute");
//            addTitle=extras.getString("Title");
//            serviseList=getIntent().getSerializableExtra("arraylist");
        }
        init();
        callOfferListApi();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.offerRv.setLayoutManager(linearLayoutManager);
        binding.offerBackIv.setOnClickListener(this);

//        setOfferAdapter();
    }

    private void setOfferAdapter() {
        offerrAdapter = new OfferrAdapter(this, offerList, this, offerKye);
        binding.offerRv.setAdapter(offerrAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.offer_back_iv:
                onBackPressed();
                break;
        }

    }


    private void callOfferListApi() {
        new Utility().showProgressDialog(this);
        Call<OfferResponse> call = APIClient.getInstance().getApiInterface().getOfferList();
        Log.e("text", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {

        new Utility().hideDialog();
        if (response != null) {
            try {
                if (response instanceof OfferResponse) {
                    OfferResponse offerResponse = (OfferResponse) response;
                    if (offerResponse.isStatus()) {
                        if (offerList.size() > 0) {
                            offerList.clear();
                        }
                        if (offerResponse.getData().size() > 0) {
                            offerList.addAll(offerResponse.getData());
                            setOfferAdapter();

                        } else {
                            Toast.makeText(this, "No Data Available !", Toast.LENGTH_SHORT).show();
                        }
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

    }

    @Override
    public void onOfferClick(int pos) {

        offerCode = offerList.get(pos).getDiscount_coupon_val();
        from_date = offerList.get(pos).getDiscount_from();
        to_date = offerList.get(pos).getDiscount_to();
        disscountPrice = offerList.get(pos).getDiscount_dis_value();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fromDate = dateFormat.parse(from_date);
            Date toDate = dateFormat.parse(to_date);
        } catch (ParseException e) {


        }


        DateTime dateTime1 = new DateTime(from_date);
        DateTime dateTime2 = new DateTime(/*"2019-03-09"*/new java.util.Date());
        DateTime dateTime3 = new DateTime(to_date);

        boolean is1After2 = dateTime1.isAfter(dateTime2);
        boolean is2Before3 = dateTime2.isBefore(dateTime3);

        boolean is2Between1And3 = ((dateTime2.isAfter(dateTime1)) && (dateTime2.isBefore(dateTime3)));


        if (is2Between1And3) {
            Intent intent = new Intent(OfferActivity.this, MyCartActivity.class);
            intent.putExtra("offerCode", offerCode);
            intent.putExtra("disscountPrice", disscountPrice);
            startActivity(intent);
            Toast.makeText(this, "Offer Applied Successfully...", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(OfferActivity.this, MyCartActivity.class);
            intent.putExtra("offerCode", offerCode);
//            intent.putExtra("disscountPrice", disscountPrice);
            startActivity(intent);
            Toast.makeText(this, "Sorry! this Offer is not Valid for you.", Toast.LENGTH_SHORT).show();

        }
    }


}
