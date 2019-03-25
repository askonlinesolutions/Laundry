package com.laundry.ui.Pick_up;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.GPSTracker;
import com.laundry.Utils.MySharedPreference;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.databinding.ActivityPickupBinding;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.MyPayment.PaymentActivity;
import com.laundry.ui.Services.vo.AddToCartResponse;
import com.laundry.ui.Services.vo.CartCountResponse;
import com.laundry.ui.currentLocation.AddAddressActivity;
import com.laundry.ui.currentLocation.CurrentLocationMapActivity;
import com.laundry.ui.manageAddress.ManageAddressActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;


public class PickupActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {

    ActivityPickupBinding binding;
    //    RecyclerView rv;
    ImageView back_iv;
    double latitute, longitute, itemTime;
    String address;
    String manageAddress, key, user_id;
    List<Address> listAddresses;
    int count;
    PickupDropModel pickupDropModel;
    private int mYear, mDay, mMonth, mHour, mMinute;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pickup);
        pickupDropModel = new PickupDropModel();
        getPosition();
        init();
        callCartCountApi();
        getGpsLocation();
    }


    private void getGpsLocation() {

        gps = new GPSTracker(this);
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitute = gps.getLatitude();
            longitute = gps.getLongitude();
            // \n is for new line
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


    }


    private void init() {

        back_iv = findViewById(R.id.back_iv);
//        rv = findViewById(R.id.rv_pickup);
//        setupRecyclerview();
        back_iv.setOnClickListener(this);
        binding.myCartIv.setOnClickListener(this);
        binding.confirmTv.setOnClickListener(this);
        binding.pickupDatePicker.setOnClickListener(this);
        binding.pickupTimePicker.setOnClickListener(this);
        binding.dropDatePicker.setOnClickListener(this);
        binding.dropTimePicker.setOnClickListener(this);
        binding.pickupCurrentLocation.setOnClickListener(this);
        binding.dropCurrentLocation.setOnClickListener(this);
        binding.pickupLocationEt.setOnClickListener(this);
        binding.dropLocationEt.setOnClickListener(this);
//        binding.pickupLocationEt.setText(address);
//
        binding.pickupLocationEt.setText(pickupDropModel.getPickAddredd());
        binding.dropLocationEt.setText(pickupDropModel.getDropAddredd());
//

            binding.dropOrderTime.setText(String.valueOf(itemTime)+" Hours");

//        if (manageAddress != null && manageAddress != "") {
//            binding.pickupLocationEt.setText(manageAddress);
//            binding.dropLocationEt.setText(manageAddress);
//        }

    /*    if (key != null && key.equals("pickup")) {
            if (manageAddress != null && manageAddress != "") {
//                binding.pickupLocationEt.setText(manageAddress);
                pickupDropModel.setPickAddredd(manageAddress);
                pickupDropModel.setPickLat(String.valueOf(latitute));
                pickupDropModel.setPickLong(String.valueOf(longitute));
                binding.pickupLocationEt.setText(pickupDropModel.getPickAddredd());
                binding.dropLocationEt.setText(pickupDropModel.getDropAddredd());
            }

        } else if (key != null && key.equals("drop")) {
            if (manageAddress != null && manageAddress != "") {
//                binding.dropLocationEt.setText(manageAddress);
                pickupDropModel.setDropAddredd(manageAddress);
                pickupDropModel.setDropLat(String.valueOf(latitute));
                pickupDropModel.setDropLong(String.valueOf(longitute));
                binding.dropLocationEt.setText(pickupDropModel.getDropAddredd());
                binding.pickupLocationEt.setText(pickupDropModel.getPickAddredd());
            }
        }*/

    }

    private void setupLocation() {

        Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(latitute, longitute, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
// Here we are finding , whatever we want our marker to show when
//                clicked
                String state = listAddresses.get(0).getAdminArea();
                String country = listAddresses.get(0).getCountryName();
                String subLocality = listAddresses.get(0).getSubLocality();
                String landmark = listAddresses.get(0).getAdminArea();
                address = listAddresses.get(0).getAddressLine(0);
                String city = listAddresses.get(0).getLocality();
//                locationEt.setText(city);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getPosition() {

        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        user_id = mySharedPreference.getUserId();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
//            longitute = extras.getDouble("longitute");
//            latitute = extras.getDouble("latitute");
//            manageAddress = extras.getString("address");
//            key = extras.getString("pickup");
            itemTime = extras.getDouble("itemTime");
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1 && data != null) {
//                 longitute = data.getDoubleExtra("longitute");
//                Bundle extras = getIntent().getExtras();

                longitute = data.getDoubleExtra("longitute", 0);
                latitute = data.getDoubleExtra("latitute", 0);
                manageAddress = data.getStringExtra("address");
                key = data.getStringExtra("pickup");

                pickupDropModel.setPickAddredd(manageAddress);
                pickupDropModel.setPickLat(String.valueOf(latitute));
                pickupDropModel.setPickLong(String.valueOf(longitute));
                binding.pickupLocationEt.setText(pickupDropModel.getPickAddredd());
                binding.dropLocationEt.setText(pickupDropModel.getDropAddredd());
//                Log.i(TAG, "onActivityResult: message >>" + strMessage);
            } else if (requestCode == 2 && data != null) {
//                String strMessage = data.getStringExtra("keyName");
                longitute = data.getDoubleExtra("longitute", 0);
                latitute = data.getDoubleExtra("latitute", 0);
                manageAddress = data.getStringExtra("address");
                key = data.getStringExtra("pickup");

                pickupDropModel.setDropAddredd(manageAddress);
                pickupDropModel.setDropLat(String.valueOf(latitute));
                pickupDropModel.setDropLong(String.valueOf(longitute));
                binding.dropLocationEt.setText(pickupDropModel.getDropAddredd());
                binding.pickupLocationEt.setText(pickupDropModel.getPickAddredd());
            } else {
                getGpsLocation();
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                onBackPressed();
                break;

            case R.id.my_cart_iv:
                Intent i = new Intent(PickupActivity.this, MyCartActivity.class);
                startActivity(i);
                break;
            case R.id.confirm_tv:
                if (binding.pickupLocationEt.length() == 0 ||
                        binding.pickupDateTv.length() == 0 ||
                        binding.pickupTimeTv.length() == 0 ||
                        binding.dropLocationEt.length() == 0 ||
                        binding.dropDateTv.length() == 0 ||
                        binding.dropTimeTv.length() == 0) {

                    Toast.makeText(this, "Please enter pickup drop !", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(PickupActivity.this, PaymentActivity.class);
                    startActivity(intent);
//                    Intent intent = new Intent(PickupActivity.this, MyCartActivity.class);
//                    startActivity(intent);
//                    finish();
                }
                break;

            case R.id.pickup_current_location:
//                Intent picIntent = new Intent(PickupActivity.this, AddAddressActivity.class);
//                startActivity(picIntent);
                setupLocation();
                pickupDropModel.setPickAddredd(address);
                pickupDropModel.setPickLat(String.valueOf(latitute));
                pickupDropModel.setPickLong(String.valueOf(longitute));
                binding.pickupLocationEt.setText(pickupDropModel.getPickAddredd());
                break;

            case R.id.pickup_location_et:
                Intent locationIntent = new Intent(PickupActivity.this, ManageAddressActivity.class);
                locationIntent.putExtra("manage", "pickup");
                startActivityForResult(locationIntent, 1);
//                startActivity(locationIntent);
                break;

            case R.id.drop_location_et:
                Intent locationDropIntent = new Intent(PickupActivity.this, ManageAddressActivity.class);
                locationDropIntent.putExtra("manage", "drop");
                startActivityForResult(locationDropIntent, 2);
//                startActivity(locationDropIntent);
                break;


            case R.id.drop_current_location:
//                Intent dropIntent = new Intent(PickupActivity.this, AddAddressActivity.class);
//                startActivity(dropIntent);
                setupLocation();
                pickupDropModel.setDropAddredd(address);
                pickupDropModel.setDropLat(String.valueOf(latitute));
                pickupDropModel.setDropLong(String.valueOf(longitute));
                binding.dropLocationEt.setText(pickupDropModel.getDropAddredd());
                break;

            case R.id.pickup_date_picker:
                selectPickupDate();
                binding.pickupDateTv.setText(pickupDropModel.getPickDate());

                break;

            case R.id.pickup_time_picker:
                selectPickupTime();
                binding.pickupTimeTv.setText(pickupDropModel.getPickTime());

                break;

            case R.id.drop_date_picker:
                selectDropDate();
                binding.dropDateTv.setText(pickupDropModel.getDropDate());

                break;

            case R.id.drop_time_picker:
                selectDropTime();
                binding.dropTimeTv.setText(pickupDropModel.getDropTime());
                break;


        }
    }

    private void selectDropTime() {


        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.dropTimeTv.setText(hourOfDay + ":" + minute);
                        pickupDropModel.setDropTime(hourOfDay + ":" + minute);

                    }


                }, mHour, mMinute, false);
        timePickerDialog.show();


    }

    private void selectDropDate() {

        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, selectedyear);
                myCalendar.set(Calendar.MONTH, selectedmonth);
                myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                String myFormat = "dd/MM/yy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                binding.dropDateTv.setText(sdf.format(myCalendar.getTime()));
                pickupDropModel.setDropDate(sdf.format(myCalendar.getTime()));
                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }
        }, mYear, mMonth, mDay);
        //mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }

    private void selectPickupTime() {

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.pickupTimeTv.setText(hourOfDay + ":" + minute);
                        pickupDropModel.setPickTime(hourOfDay + ":" + minute);
                    }


                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void selectPickupDate() {


        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, selectedyear);
                myCalendar.set(Calendar.MONTH, selectedmonth);
                myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                String myFormat = "dd/MM/yy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                binding.pickupDateTv.setText(sdf.format(myCalendar.getTime()));
                pickupDropModel.setPickDate(sdf.format(myCalendar.getTime()));
                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }
        }, mYear, mMonth, mDay);
        //mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }

    private void callCartCountApi() {
//        new Utility().showProgressDialog(this);
        Call<CartCountResponse> call = APIClient.getInstance().getApiInterface().getCartCount(user_id);
        new ResponseListner(this).getResponse(call);
    }

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            try {
                if (response instanceof CartCountResponse) {
                    CartCountResponse cartCountResponse = (CartCountResponse) response;
                    if (cartCountResponse.isStatus()) {
                        if (count > 0) {
                            count = 0;
                        }
                        count = cartCountResponse.getCart_count();
                        binding.cartCountTv.setText(String.valueOf(count));
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

    }
}