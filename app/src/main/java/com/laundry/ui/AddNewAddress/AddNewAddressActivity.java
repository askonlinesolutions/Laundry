package com.laundry.ui.AddNewAddress;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityAddNewAddressBinding;
import com.laundry.ui.AddNewAddress.vo.AddAddressResponse;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.changePassword.vo.ChangePwdResponse;
import com.laundry.ui.currentLocation.AddAddressActivity;
import com.laundry.ui.currentLocation.CurrentLocationMapActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class AddNewAddressActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {
    private ActivityAddNewAddressBinding binding;
    String editKey, address, state, city, subLocality, landmark, userId, zipCode, addTitle;
    List<Address> listAddresses;
    double latitute, longitute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_address);
        getPosition();
        init();
    }

    private void init() {

        if (editKey != null && editKey.equals("EDIT")) {
            binding.loginTitle.setText("Edit Address");
        } else {
            binding.loginTitle.setText("Add new Address");
        }

        binding.loginTitle.setOnClickListener(this);
        binding.currentLocation.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);

        setAddress();

    }

    private void setAddress() {

        Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        try {
            /* List<Address>*/
            listAddresses = geocoder.getFromLocation(latitute,
                    longitute, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
// Here we are finding , whatever we want our marker to show when
                binding.addressTitleEt.setText(listAddresses.get(0).getAdminArea());
                binding.addressEt.setText(listAddresses.get(0).getAddressLine(0));
                binding.landmarketEt.setText(listAddresses.get(0).getSubAdminArea());
                binding.stateEt.setText(listAddresses.get(0).getAdminArea());
                binding.pinCodeEt.setText(listAddresses.get(0).getPostalCode());
                binding.cityTownEt.setText(listAddresses.get(0).getLocality());
                binding.countryEt.setText(listAddresses.get(0).getCountryName());
//                binding.addressLonEt.setText(Double.toString(longitute));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }






    }


    private void getPosition() {

        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editKey = extras.getString("EDIT");

            longitute = extras.getDouble("longitute");
            latitute = extras.getDouble("latitute");

//            serviseList=getIntent().getSerializableExtra("arraylist");
        }

//        if (longitute != 0 && latitute != 0) {
//            setCurrentLocation(longitute, latitute);
//        }
////        if (extras != null) {
//            longitute = extras.getDouble("longitute");
//            latitute = extras.getDouble("latitute");
//
//        }


    }

    private void setCurrentLocation(double longitute, double latitute) {

      /*  Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        try {
            *//* List<Address>*//*
            listAddresses = geocoder.getFromLocation(latitute,
                    longitute, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
// Here we are finding , whatever we want our marker to show when
//                clicked
//                state = listAddresses.get(0).getAdminArea();
//                country = listAddresses.get(0).getCountryName();
//                subLocality = listAddresses.get(0).getSubLocality();
//                landmark = listAddresses.get(0).getAdminArea();
//                address = listAddresses.get(0).getAddressLine(0);
//                String city = listAddresses.get(0).getFeatureName();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.current_location:
                Intent intent = new Intent(AddNewAddressActivity.this,AddAddressActivity/* CurrentLocationMapActivity*/.class);
                intent.putExtra("NewAddress", "NewAddress");

                startActivity(intent);
                break;

            case R.id.save_btn:

                if (isNetworkConnected(this)) {
                    callAddNewAddressApi();
                } else {
                    Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    private void callAddNewAddressApi() {


        addTitle = binding.addressTitleEt.getText().toString().trim();
        address = binding.addressEt.getText().toString().trim();
        landmark = binding.landmarketEt.getText().toString().trim();
        state = binding.stateEt.getText().toString().trim();
        zipCode = binding.pinCodeEt.getText().toString().trim();
        city = binding.cityTownEt.getText().toString().trim();
//        binding.addressLatEt.getText().toString().trim();
//        binding.addressLonEt.getText().toString().trim();


        new Utility().showProgressDialog(this);
        Call<AddAddressResponse> call = APIClient.getInstance().getApiInterface().addAddress(userId, zipCode, addTitle, state, address, city, landmark, latitute, longitute);
        new ResponseListner(this).getResponse(call);


    }

    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof AddAddressResponse) {
                    AddAddressResponse addressResponse = (AddAddressResponse) response;
                    new Utility().hideDialog();
                    if (addressResponse.isStatus()) {

                        Toast.makeText(this, addressResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(AddAddressResponse.this, DryCleanerActivity.class);
//                        startActivity(i);


                    } else {
                        Toast.makeText(this, addressResponse.getMsg(), Toast.LENGTH_SHORT).show();
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

    }
}
