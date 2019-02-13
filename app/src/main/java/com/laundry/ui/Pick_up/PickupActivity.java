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
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.laundry.R;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.databinding.ActivityPickupBinding;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.currentLocation.CurrentLocationMapActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PickupActivity extends AppCompatActivity implements OnItemClickLisner, View.OnClickListener {

    ActivityPickupBinding binding;
    //    RecyclerView rv;
    ImageView back_iv;
    double latitute, longitute;
    String address;
    List<Address> listAddresses;
    private int mYear, mDay, mMonth, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pickup);
        getPosition();
        init();

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

        binding.pickupLocationEt.setText(address);

    }


//    private void setupRecyclerview() {
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        rv.setLayoutManager(linearLayoutManager);
//        PickDropAdapter pickDropAdapter = new PickDropAdapter(this,/* listAddresses,*/ this);
//        rv.setAdapter(pickDropAdapter);
//
//    }

    private void getPosition() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            longitute = extras.getDouble("longitute");
            latitute = extras.getDouble("latitute");

        }


        Geocoder geocoder = new Geocoder(getApplicationContext(),
                Locale.getDefault());
        try {
            /* List<Address>*/
            listAddresses = geocoder.getFromLocation(latitute,
                    longitute, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
// Here we are finding , whatever we want our marker to show when
//                clicked
                String state = listAddresses.get(0).getAdminArea();
                String country = listAddresses.get(0).getCountryName();
                String subLocality = listAddresses.get(0).getSubLocality();
                String landmark = listAddresses.get(0).getAdminArea();
                address = listAddresses.get(0).getAddressLine(0);
                String city = listAddresses.get(0).getFeatureName();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(PickupActivity.this, CurrentLocationMapActivity.class));
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
                Intent intent = new Intent(PickupActivity.this, MyCartActivity.class);
                startActivity(intent);
                break;

            case R.id.pickup_current_location:
                Intent picIntent = new Intent(PickupActivity.this, CurrentLocationMapActivity.class);
                startActivity(picIntent);
                break;


            case R.id.drop_current_location:
                Intent dropIntent = new Intent(PickupActivity.this, CurrentLocationMapActivity.class);
                startActivity(dropIntent);
                break;

            case R.id.pickup_date_picker:
                selectPickupDate();
                break;

            case R.id.pickup_time_picker:
                selectPickupTime();
                break;

            case R.id.drop_date_picker:
                selectDropDate();
                break;

            case R.id.drop_time_picker:
                selectDropTime();
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

                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }
        }, mYear, mMonth, mDay);
        //mDatePicker.setTitle("Select date");
        mDatePicker.show();

    }


}