package com.laundry.ui.DryCleaner;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.laundry.R;

import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.Contact.ContactActivity;
import com.laundry.ui.FAQ.FAQActivity;
import com.laundry.ui.LoginScreen.ChangePaawordActivity;
import com.laundry.ui.changePassword.ChangePaawordActivity;
import com.laundry.ui.Services.ServicesActivity;
import com.laundry.ui.myOrder.MyOrderActivity;
import com.laundry.ui.profile.ProfileActivity;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

//importcom.laundry.CustomPagerAdapter;

public class DryCleanerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnItemClickLisner {
    //    LinearLayout press_image;
    SpringDotsIndicator dotsIndicator;
    RecyclerView press_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dry_cleaner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        press_image = findViewById(R.id.press_image);
        // ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setSupportActionBar(toolbar);
        inIt();
        goServices();
        category();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void inIt() {
        dotsIndicator = (SpringDotsIndicator) findViewById(R.id.dots_indicator);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        dotsIndicator.setViewPager(viewPager);
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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void goServices() {
        press_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DryCleanerActivity.this, ServicesActivity.class);
                startActivity(i);
            }
        });

    }

    //    @Override
//    public void onClick(View v) {
//
//        Intent i = new Intent(DryCleanerActivity.this, ServicesActivity.class);
//        startActivity(i);
//    }
    private void category() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        press_image.setLayoutManager(new GridLayoutManager(this, 2));
        SelectServiceAdapter selectServiceAdapter = new SelectServiceAdapter(this, this);
        press_image.setAdapter(selectServiceAdapter);


    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(DryCleanerActivity.this, ServicesActivity.class);
        startActivity(i);
    }
}
