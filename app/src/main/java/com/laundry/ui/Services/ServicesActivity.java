package com.laundry.ui.Services;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityServicesBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.Fragment.MaunAdapter;
import com.laundry.ui.Fragment.WashAndIronFragment;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.Pick_up.PickupActivity;


import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements CategoryLisAdapter.CategoryListInterface,
        View.OnClickListener, CategoryItemAdapter.CategoryItemClickLictner, ServicesAdapter.ServicesAdapterInterface {

    private ActivityServicesBinding binding;
    private ServicesAdapter servicesAdapter;
    private CategoryItemAdapter categoryItemAdapter;
    private CategoryLisAdapter categoryLisAdapter;
    //    private TabLayout tabLayout;
//    private ViewPager viewPager;
    private int pos;
    private ArrayList<ServiceResponse.DataEntity> serviseList = new ArrayList<>();
    private List<ServiceResponse.DataEntity.CategoryEntity> categoryList = new ArrayList<>();
    private List<ServiceResponse.DataEntity.CategoryEntity.ItemsEntity> categoryItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_services);

        getPosition();
        init();
    }

    private void init() {

        binding.loginTitle.setOnClickListener(this);
        binding.imgMyCart.setOnClickListener(this);
        binding.schedulePickupTv.setOnClickListener(this);
        setHorizontalRecycler();

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

//        for (int i = 0; i < serviseList.size(); i++) {
        if (pos!=0 && serviseList.get(pos).getCategory()!=null)
        {
            categoryList.addAll(serviseList.get(pos).getCategory());
        }

//        }
////        viewPager.setCurrentItem(pos);
//        for (int i = 0; i < categoryList.size(); i++) {
//        if (pos!=0 && categoryList.get(pos).getItems()!=null)
//        {
//            categoryItemsList.addAll(categoryList.get(pos).getItems());
//
//        }
//        }
//

    }

    private void setHorizontalRecycler() {

        setUpServiceAdapter();

        setUpCategoryListAdapter();
//
        setUpCategoryItemListAdapter();


    }

    private void setUpCategoryItemListAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        binding.categoryItemRv.setLayoutManager(layoutManager);
        categoryItemAdapter = new CategoryItemAdapter(this, categoryItemsList, this);
        binding.categoryItemRv.setAdapter(categoryItemAdapter);
    }

    private void setUpCategoryListAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                false);

        binding.categoryRv.setLayoutManager(linearLayoutManager);
        categoryLisAdapter = new CategoryLisAdapter(this, this/*this*/, categoryList);
        binding.categoryRv.setAdapter(categoryLisAdapter);

    }

    private void setUpServiceAdapter() {

        LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                false);
        binding.servicesRv.setLayoutManager(linearManager);
        servicesAdapter = new ServicesAdapter(this, this, pos, serviseList);
        binding.servicesRv.setAdapter(servicesAdapter);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_my_cart:
                Intent i = new Intent(ServicesActivity.this, MyCartActivity.class);
                startActivity(i);
                break;

            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.schedule_pickup_tv:
                Intent intent = new Intent(ServicesActivity.this, PickupActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onCategryItemClick(int adapterPosition) {

    }

    @Override
    public void onCategoryClicked(int pos) {

        if (categoryItemsList.size() != 0) {
            categoryItemsList.clear();
            categoryItemsList.addAll(categoryList.get(pos).getItems());
//            setUpCategoryItemListAdapter();
            categoryItemAdapter.notifyDataSetChanged();

        } else {
        categoryItemsList.addAll(categoryList.get(pos).getItems());
//            setUpCategoryItemListAdapter();
        categoryItemAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void onServicesClicked(int pos, String serviceId) {
        if (categoryList.size() != 0) {
            categoryList.clear();
            categoryList.addAll(serviseList.get(pos).getCategory());
//            setUpCategoryListAdapter();
            categoryLisAdapter.notifyDataSetChanged();
        } else {
        categoryList.addAll(serviseList.get(pos).getCategory());
//        setUpCategoryListAdapter();

        categoryLisAdapter.notifyDataSetChanged();
        }

    }




   /* private void setupViewPager(final ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(serviseList.size()*//*tabTitles.length*//*);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        LayoutInflater inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < serviseList.size(); i++) {

            WashAndIronFragment fView = new WashAndIronFragment();
            View view = fView.getView();
            adapter.addFrag(fView, serviseList.get(i).getName()*//*"TAB " + i*//*);

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

    }*/


}

