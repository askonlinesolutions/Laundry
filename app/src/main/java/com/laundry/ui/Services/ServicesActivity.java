package com.laundry.ui.Services;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.databinding.ActivityServicesBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.Fragment.WashAndIronFragment;
import com.laundry.ui.MyCart.MyCartActivity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements ServicesAdapter.ServicesAdapterInterface, View.OnClickListener {

    private ActivityServicesBinding binding;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int pos;
    private ArrayList<ServiceResponse.DataEntity> serviseList = new ArrayList<>();

//    private String tabTitles[] = {"Product", "MRP", "Receipt", "Woman", "Child"};
    ArrayList<String> name = new ArrayList<>(Arrays.asList("Schuder", "MEN", "Woman", "Child", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder "));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_services);

        getPosition();
        init();
    }

    private void init() {

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        binding.loginTitle.setOnClickListener(this);
        binding.imgMyCart.setOnClickListener(this);
        setHorizontalRecycler();

    }


    private void setupViewPager(final ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(serviseList.size()/*tabTitles.length*/);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < serviseList.size(); i++) {

            WashAndIronFragment fView = new WashAndIronFragment();
            View view = fView.getView();
            adapter.addFrag(fView, serviseList.get(i).getService_name()/*"TAB " + i*/);

        }
        viewPager.setAdapter(adapter);
        if (pos != 0) {
            viewPager.setCurrentItem(pos);
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        if (pos != 0) {
//            viewPager.setCurrentItem(pos);
//        }

    }


    private void getPosition() {
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


//        viewPager.setCurrentItem(pos);


    }

    private void setHorizontalRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                false);
        binding.serviceRecycler.setLayoutManager(linearLayoutManager);
        ServicesAdapter servicesAdapter = new ServicesAdapter(this, this/*this*/, name);
        binding.serviceRecycler.setAdapter(servicesAdapter);

    }


    @Override
    public void getDetails(int pos) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_cart_iv:
                Intent i = new Intent(ServicesActivity.this, MyCartActivity.class);
                startActivity(i);
                break;

            case R.id.login_title:
                onBackPressed();
                break;
        }
    }
}

