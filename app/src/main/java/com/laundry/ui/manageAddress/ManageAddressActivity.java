package com.laundry.ui.manageAddress;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.laundry.databinding.ActivityManageAddressBinding;
import com.laundry.ui.Pick_up.PickupActivity;
import com.laundry.ui.currentLocation.AddAddressActivity;
import com.laundry.ui.currentLocation.CurrentLocationMapActivity;
import com.laundry.ui.AddNewAddress.AddNewAddressActivity;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.manageAddress.vo.DeleteAddressResponse;
import com.laundry.ui.manageAddress.vo.ManageAddressResponse;
import com.laundry.ui.manageAddress.vo.UpdateAddressStatus;
import com.laundry.ui.myOrder.vo.MyOrderResponse;
import com.laundry.ui.offer.OfferrAdapter;
import com.laundry.ui.profile.ProfileActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class ManageAddressActivity extends AppCompatActivity implements View.OnClickListener, ManageAddressAdapter.OnBtnClickListener, OnResponseInterface {

    private ActivityManageAddressBinding binding;
    private ManageAddressAdapter addressAdapter;
    String userId, manageKey;
    private static String TAG = ManageAddressActivity.class.getName();
    private ArrayList<ManageAddressResponse.DataEntity> addressList = new ArrayList<>();
    List<Address> listAddresses;
    double latitute, longitute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_address);

        getUserId();
        init();
    }

    private void getUserId() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            manageKey = extras.getString("manage");

//            longitute = extras.getDouble("longitute");
//            latitute = extras.getDouble("latitute");
//            addTitle=extras.getString("Title");
//            serviseList=getIntent().getSerializableExtra("arraylist");
        }

    }

    private void init() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.manageAddressRv.setLayoutManager(linearLayoutManager);
        binding.manageBackTv.setOnClickListener(this);
        binding.addNewAddressTv.setOnClickListener(this);

        if (isNetworkConnected(this)) {
            callManageAddressApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

        setAdapter();
//        setAddress();


    }

    private void setAdapter() {
        addressAdapter = new ManageAddressAdapter(this, addressList, this, manageKey);
        binding.manageAddressRv.setAdapter(addressAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manage_back_tv:
                onBackPressed();
                break;

            case R.id.add_new_address_tv:
//                Intent i = new Intent(ManageAddressActivity.this, AddNewAddressActivity.class);
//                startActivity(i);
                Intent intent = new Intent(ManageAddressActivity.this, AddAddressActivity/* CurrentLocationMapActivity*/.class);
                intent.putExtra("NewAddress", "NewAddress");

                startActivity(intent);

                break;
        }
    }

    @Override
    public void onBtnClick(int Pos, String type, String addressId, String status, String lat, String lng) {
        switch (type) {
            case "EDIT":
//            startActivity(new Intent(ManageAddressActivity.this, CurrentLocationMapActivity.class));
                Intent i = new Intent(ManageAddressActivity.this, AddAddressActivity.class);
                i.putExtra("NewAddress", "NewAddress");
                i.putExtra("edit", "edit");
                i.putExtra("lat", lat);
                i.putExtra("lng", lng);

                startActivity(i);
                this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;
            case "Delete":

                if (isNetworkConnected(this)) {
                    callDeleteAddressApi(addressId);
                } else {
                    Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
                }

                break;
            case "check":

                String address = addressList.get(Pos).getUseraddress_address();
                latitute = Double.valueOf(addressList.get(Pos).getAddress_lat());
                longitute = Double.valueOf(addressList.get(Pos).getAddress_long());
                if (manageKey.equals("pickup")) {

//                    Intent intent = new Intent(ManageAddressActivity.this, PickupActivity.class);
                    Intent intent = new Intent();
                    intent.putExtra("address", address);
                    intent.putExtra("pickup", "pickup");
                    intent.putExtra("longitute", longitute);
                    intent.putExtra("latitute", latitute);
//                    startActivity(intent);
                    setResult(RESULT_OK, intent);
//                    startActivity(intent);
                } else if (manageKey.equals("drop")) {
//                    Intent intent = new Intent(ManageAddressActivity.this, PickupActivity.class);
                    Intent intent = new Intent();
                    intent.putExtra("address", address);
                    intent.putExtra("pickup", "drop");
                    intent.putExtra("longitute", longitute);
                    intent.putExtra("latitute", latitute);
//                    startActivity(intent);
                    setResult(RESULT_OK, intent);
//                    startActivity(intent);
//                       context.startActivityForResult(intent, 1);
                }
                break;


       /* else if (type.equals("Status")) {
            if (isNetworkConnected(this)) {
                callUpdateAddressStatus(addressId, status);
            } else {
                Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
            }
        }*/
            default:
                Toast.makeText(this, type, Toast.LENGTH_SHORT).show();

                break;
        }


    }

    private void callUpdateAddressStatus(String addressId, String status) {

        new Utility().showProgressDialog(this);
        Call<UpdateAddressStatus> call = APIClient.getInstance().getApiInterface().updateAddressStatus(userId/*"14"*/, addressId, status);
        new ResponseListner(this).getResponse(call);

    }

    private void callDeleteAddressApi(String addressId) {
        new Utility().showProgressDialog(this);
        Call<DeleteAddressResponse> call = APIClient.getInstance().getApiInterface().deleteAddress(userId/*"14"*/, addressId);
        new ResponseListner(this).getResponse(call);

    }


    private void callManageAddressApi() {

        new Utility().showProgressDialog(this);
        Call<ManageAddressResponse> call = APIClient.getInstance().getApiInterface().getAddressList(userId/*"14"*/);
        Log.e("MyOrderUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {
        new Utility().hideDialog();
        if (response != null) {
            try {
                if (response instanceof ManageAddressResponse) {
                    ManageAddressResponse manageAddressResponse = (ManageAddressResponse) response;
                    new Utility().hideDialog();
                    if (manageAddressResponse.isStatus()) {

                        addressList.clear();
                        if (manageAddressResponse.getData() != null /*&& messageDataList.size() != 0*/) {
                            addressList.addAll(manageAddressResponse.getData());

                            setAdapter();
                        }
                    }
                } else if (response instanceof DeleteAddressResponse) {
                    DeleteAddressResponse deleteAddressResponse = (DeleteAddressResponse) response;
                    new Utility().hideDialog();
                    if (deleteAddressResponse.isStatus()) {
                        callManageAddressApi();
                        Toast.makeText(this, deleteAddressResponse.getMsg(), Toast.LENGTH_SHORT).show();

                    }
                } else if (response instanceof UpdateAddressStatus) {
                    UpdateAddressStatus updateAddressStatus = (UpdateAddressStatus) response;
                    new Utility().hideDialog();
                    if (updateAddressStatus.isStatus()) {
                        Toast.makeText(this, updateAddressStatus.getMsg(), Toast.LENGTH_SHORT).show();
                        callManageAddressApi();
                    } else {

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
