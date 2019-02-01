package com.laundry.ui.Services;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.Fragment.WashAndIronFragment;
import com.laundry.ui.MyCart.MyCartActivity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements ServicesAdapter.ServicesAdapterInterface {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView login_title;
    RecyclerView serviceRecycler;
    ImageView img_my_cart;
    ArrayList<String> name = new ArrayList<>(Arrays.asList("Schuder", "MEN", "Woman", "Child", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder ", "schuder "));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        serviceRecycler = findViewById(R.id.serviceRecycler);
        login_title = findViewById(R.id.login_title);
        img_my_cart = findViewById(R.id.img_my_cart);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mycartScreen();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        setHorizontalRecycler();
        backpress();
    }

    private void mycartScreen() {

        img_my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ServicesActivity.this, MyCartActivity.class);
                startActivity(i);
            }
        });
    }


    private void backpress() {
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setHorizontalRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,
                false);
        serviceRecycler.setLayoutManager(linearLayoutManager);
        ServicesAdapter servicesAdapter = new ServicesAdapter(this, this/*this*/, name);
        serviceRecycler.setAdapter(servicesAdapter);

    }

    private void setupTabIcons() {
//        int[] tabIcons = {
//                R.drawable.home,
//                R.drawable.category, R.drawable.search, R.drawable.two, R.drawable.two,
//
//        };
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
//        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WashAndIronFragment(), "Wash&Fold");
        adapter.addFragment(new /*WashAndFoldFragment*/WashAndIronFragment(), "Wash&Iron");
        adapter.addFragment(new/* PremiumLaundryFragment*/WashAndIronFragment(), "Premium Laundry");
        adapter.addFragment(new /*DryCleanerFragment*/WashAndIronFragment(), "Dey Cleaner");

        // adapter.addFragment(new FifthFragment(), "Bakets");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void getDetails(int pos) {
//        if (pos>=0){
//            for (int i = 0; i<=5;i++){
//                name.set(i,"schuder ");
//            }
//
//            name.set(pos, "1");
//        }
//        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


}
