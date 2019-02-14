package com.laundry.ui.DryCleaner;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;

import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.Contact.ContactActivity;
import com.laundry.ui.DryCleaner.vo.LogoutResponse;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.FAQ.FAQActivity;

import com.laundry.ui.FAQ.vo.FaqResponse;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.LoginScreen.vo.SignUpResponse;
import com.laundry.ui.MyPayment.PaymentMethodActivity;
import com.laundry.ui.Thanku.ThankuActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.Services.ServicesActivity;
import com.laundry.ui.myOrder.MyOrderActivity;
import com.laundry.ui.profile.ProfileActivity;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;

import static android.provider.Contacts.SettingsColumns.KEY;
import static com.laundry.Utils.Utility.isNetworkConnected;

//importcom.laundry.CustomPagerAdapter;

public class DryCleanerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClickLisner, OnResponseInterface {

    SpringDotsIndicator dotsIndicator;
    RecyclerView press_image;
    TextView cancel_btn, playnowbtn;
    private static String TAG = DryCleanerActivity.class.getName();
    private ArrayList<ServiceResponse.DataEntity> serviseList = new ArrayList<>();
    private ArrayList<ServiceResponse.DataEntity.CategoryEntity> categoryList = new ArrayList<>();
    private ArrayList<ServiceResponse.DataEntity.CategoryEntity.ItemsEntity> categoryItemsList = new ArrayList<>();

    private boolean isVisible = true;
    private boolean isShow = true;
    ImageView btncross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_cleaner);


        inIt();


        if (isNetworkConnected(this)) {
            callServicesApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

//        goServices();

    }


    private void inIt() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        press_image = findViewById(R.id.press_image);
        setSupportActionBar(toolbar);

        dotsIndicator = (SpringDotsIndicator) findViewById(R.id.dots_indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        dotsIndicator.setViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        press_image.setLayoutManager(new GridLayoutManager(this, 2));
//        setAdapter();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dry_cleaner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_order) {
            Intent intent = new Intent(DryCleanerActivity.this, MyOrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.my_profile) {
            Intent intent = new Intent(DryCleanerActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.contact) {
            Intent intent = new Intent(DryCleanerActivity.this, ContactActivity.class);
            startActivity(intent);


        } else if (id == R.id.faq) {

            Intent intent = new Intent(DryCleanerActivity.this, FAQActivity.class);
            startActivity(intent);

        } else if (id == R.id.change_pwd) {
            Intent intent = new Intent(DryCleanerActivity.this, ChangePaawordActivity.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            logout_dialog();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void logout_dialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_logout_dialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btncross = (ImageView) dialog.findViewById(R.id.close_img);
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        playnowbtn = dialog.findViewById(R.id.playnowbtn);
        btncross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        playnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callapilogout();
                Intent intent = new Intent(DryCleanerActivity.this, MainActivity.class);
                startActivity(intent);
            }


        });

    }

    private void callapilogout() {

        new Utility().showProgressDialog(this);
        Call<LogoutResponse> call = APIClient.getInstance().getApiInterface().getlogout();
        Log.e("MylogoutUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(call);




    }

    private void setAdapter() {
        SelectServiceAdapter selectServiceAdapter = new SelectServiceAdapter(this, serviseList, this);
        press_image.setAdapter(selectServiceAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(DryCleanerActivity.this, ServicesActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", (Serializable) serviseList);
        i.putExtra("BUNDLE", args);
        i.putExtra("pos", position);

        startActivity(i);
        setupWindowAnimations();
    }


    private void callServicesApi() {

        new Utility().showProgressDialog(this);
        Call<ServiceResponse> call = APIClient.getInstance().getApiInterface().getServices();
        new ResponseListner(this).getResponse(call);

    }


    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof ServiceResponse) {
                    ServiceResponse serviceResponse = (ServiceResponse) response;
                    new Utility().hideDialog();
                    if (serviceResponse.isStatus()) {
                        serviseList.clear();
                        if (serviceResponse.getData() != null /*&& messageDataList.size() != 0*/) {
                            serviseList.addAll(serviceResponse.getData());

                            setAdapter();
                        }
                    }else   if (response instanceof LogoutResponse) {
                        LogoutResponse logoutResponse = (LogoutResponse) response;
                        new Utility().hideDialog();
//                        if (logoutResponse.isStatus()) {
//                            serviseList.clear();
//                            if (LogoutResponse.getData() != null /*&& messageDataList.size() != 0*/) {
//                                serviseList.addAll(serviceResponse.getData());
//
//                                setAdapter();

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

    private void setupWindowAnimations() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
