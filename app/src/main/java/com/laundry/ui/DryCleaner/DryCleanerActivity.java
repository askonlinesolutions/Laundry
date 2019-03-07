package com.laundry.ui.DryCleaner;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;

import com.laundry.Utils.Constant;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.SharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.Chat.ChatActivity;
import com.laundry.ui.Contact.ContactActivity;
import com.laundry.ui.DryCleaner.vo.BannerResponse;
import com.laundry.ui.DryCleaner.vo.LogoutResponse;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.FAQ.FAQActivity;

import com.laundry.ui.FAQ.vo.FaqResponse;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.LoginScreen.vo.SignUpResponse;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.MyPayment.PaymentMethodActivity;
import com.laundry.ui.Services.vo.CartCountResponse;
import com.laundry.ui.Thanku.ThankuActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.Services.ServicesActivity;
import com.laundry.ui.myOrder.MyOrderActivity;
import com.laundry.ui.profile.ProfileActivity;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;

import static android.provider.Contacts.SettingsColumns.KEY;
import static com.laundry.Utils.Utility.isNetworkConnected;


public class DryCleanerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClickLisner, OnResponseInterface {

    SpringDotsIndicator dotsIndicator;
    RecyclerView press_image;
    LinearLayout userLogin, guestUser;
    ViewPager viewPager;
    String user_id, user_image, phone_no, userName;
    String editKey;
    View view;
    int count;
    ImageView userImageIv, img_my_cart;
    TextView cancel_btn, playnowbtn, userNameTv, userPhonNoTv, cartCountTv;
    private static String TAG = DryCleanerActivity.class.getName();
    private ArrayList<ServiceResponse.DataEntity> serviseList = new ArrayList<>();
    private ArrayList<BannerResponse.DataEntity> bannerList = new ArrayList<>();
    private ArrayList<ServiceResponse.DataEntity.CategoryEntity> categoryList = new ArrayList<>();
    private ArrayList<ServiceResponse.DataEntity.CategoryEntity.ItemsEntity> categoryItemsList = new ArrayList<>();

    private boolean isVisible = true;
    private boolean isShow = true;
    ImageView btncross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_cleaner);

        getUser_Id();
        inIt();
        getBanner();
        if (isNetworkConnected(this)) {
            callCartCountApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

        if (isNetworkConnected(this)) {
            callServicesApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

    }


    private void getUser_Id() {
        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        user_id = mySharedPreference.getUserId();
        user_image = mySharedPreference.getUserImage();
        phone_no = mySharedPreference.getPhoneNubmber();
        userName = mySharedPreference.getUserName();
        Log.e("MyUserId", user_id);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editKey = extras.getString("Login");
        }

    }


    //    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void inIt() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        press_image = findViewById(R.id.press_image);
        cartCountTv = findViewById(R.id.cart_count_tv);
        img_my_cart = findViewById(R.id.img_my_cart);

        setSupportActionBar(toolbar);

        dotsIndicator = (SpringDotsIndicator) findViewById(R.id.dots_indicator);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        MenuItem logout = menu.findItem(R.id.logout);

        if (editKey.equals("Skip")) {
            logout.setTitle("Login");

        } else if (editKey.equals("Login")) {
            logout.setTitle("Logout");
        }

        navigationView.setNavigationItemSelectedListener(this);

        view = navigationView.getHeaderView(0);
        userImageIv = view.findViewById(R.id.user_image_iv);
        userNameTv = view.findViewById(R.id.user_name_tv);
        userPhonNoTv = view.findViewById(R.id.user_phon_no_tv);
        guestUser = view.findViewById(R.id.guest_user);
        userLogin = view.findViewById(R.id.user_login);

        if (editKey.equals("Skip")) {
            guestUser.setVisibility(View.VISIBLE);
            userLogin.setVisibility(View.GONE);
        } else if (editKey.equals("Login")) {
            guestUser.setVisibility(View.GONE);
            userLogin.setVisibility(View.VISIBLE);
        }

        if (!userName.equals("") && !user_image.equals("") && !phone_no.equals("")) {
            userNameTv.setText(userName);
            userPhonNoTv.setText(phone_no);

            Picasso.with(this).
                    load(Constant.IMAGE_BASE_URL + user_image) // URL or file
                    .into(userImageIv);
        } else {
            userImageIv.setImageResource(R.drawable.userplaceholder);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        press_image.setLayoutManager(new GridLayoutManager(this, 2));
//        setAdapter();
        img_my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DryCleanerActivity.this, MyCartActivity.class);
                startActivity(i);
            }
        });

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

    MenuItem logout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dry_cleaner, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_order) {
            if (editKey.equals("Skip")) {
                Toast.makeText(this, "Please Login first.", Toast.LENGTH_SHORT).show();

            } else if (editKey.equals("Login")) {
                Intent intent = new Intent(DryCleanerActivity.this, MyOrderActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.my_profile) {

            if (editKey.equals("Skip")) {
                Toast.makeText(this, "Please Login first.", Toast.LENGTH_SHORT).show();
            } else if (editKey.equals("Login")) {

                Intent intent = new Intent(DryCleanerActivity.this, ProfileActivity.class);
                startActivity(intent);
            }


        } else if (id == R.id.contact) {
            Intent intent = new Intent(DryCleanerActivity.this, ContactActivity.class);
            startActivity(intent);


        } else if (id == R.id.faq) {

            Intent intent = new Intent(DryCleanerActivity.this, FAQActivity.class);
            startActivity(intent);

        }
       /* else if (id == R.id.chat) {

            Intent intent = new Intent(DryCleanerActivity.this, ChatActivity.class);
            startActivity(intent);

        }*/
        else if (id == R.id.change_pwd) {

            if (editKey.equals("Skip")) {

                Toast.makeText(this, "Please Login first.", Toast.LENGTH_SHORT).show();
            } else if (editKey.equals("Login")) {

                Intent intent = new Intent(DryCleanerActivity.this, ChangePaawordActivity.class);
                startActivity(intent);
            }


        } else if (id == R.id.logout) {

            if (editKey.equals("Skip")) {
                Intent intent = new Intent(DryCleanerActivity.this, MainActivity.class);
                startActivity(intent);

            } else if (editKey.equals("Login")) {
                logout_dialog();
            }


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
//                Intent intent = new Intent(DryCleanerActivity.this, MainActivity.class);
//                startActivity(intent);
                callapilogout();

            }


        });

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

    private void callCartCountApi() {
//        new Utility().showProgressDialog(this);
        Call<CartCountResponse> call = APIClient.getInstance().getApiInterface().getCartCount(user_id);
        new ResponseListner(this).getResponse(call);

    }


    private void getBanner() {
//        new Utility().showProgressDialog(this);
        Call<BannerResponse> call = APIClient.getInstance().getApiInterface().getbanner();
        new ResponseListner(this).getResponse(call);
    }

    private void callServicesApi() {
        new Utility().showProgressDialog(this);
        Call<ServiceResponse> call = APIClient.getInstance().getApiInterface().getServices();
        new ResponseListner(this).getResponse(call);

    }

    private void callapilogout() {

        new Utility().showProgressDialog(this);
        Call<LogoutResponse> call = APIClient.getInstance().getApiInterface().getlogout(user_id);
        Log.e("MyLogout", call.request().url().toString());

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
                    }
                } else if (response instanceof LogoutResponse) {
                    LogoutResponse logoutResponse = (LogoutResponse) response;
                    new Utility().hideDialog();
                    if (logoutResponse.isStatus()) {

                        SharedPreference sharedPreference = SharedPreference.getInstance(getApplicationContext());
                        sharedPreference.putString("IsLogin", "0");
//                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DryCleanerActivity.this, MainActivity.class);
                        startActivity(intent);
//                        finish();

//                         MySharedPreference mySharedPreference = new MySharedPreference(getApplicationContext());
//                         mySharedPreference.saveUserId("");

                        //  intent.putExtra("login", true);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    }

                } else if (response instanceof BannerResponse) {
                    BannerResponse bannerResponse = (BannerResponse) response;
                    new Utility().hideDialog();
                    if (bannerResponse.isStatus()) {

//                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        bannerList.clear();
                        if (bannerResponse.getData() != null /*&& messageDataList.size() != 0*/) {
                            bannerList.addAll(bannerResponse.getData());
                            viewPager.setAdapter(new CustomPagerAdapter(this, bannerList));
                            dotsIndicator.setViewPager(viewPager);
                        }
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
                        cartCountTv.setText(String.valueOf(count/*cartCountResponse.getCart_count()*/));
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

    private void setupWindowAnimations() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


}
