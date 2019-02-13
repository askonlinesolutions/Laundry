package com.laundry.ui.manageAddress;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.databinding.ActivityManageAddressBinding;
import com.laundry.ui.currentLocation.CurrentLocationMapActivity;
import com.laundry.ui.AddNewAddress.AddNewAddressActivity;
import com.laundry.ui.DryCleaner.DryCleanerActivity;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.offer.OfferrAdapter;
import com.laundry.ui.profile.ProfileActivity;

public class ManageAddressActivity extends AppCompatActivity implements View.OnClickListener, ManageAddressAdapter.OnBtnClickListener {

    private ActivityManageAddressBinding binding;
    private ManageAddressAdapter addressAdapter;
    TextView add_new_address_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_address);
        dologin();
        init();
    }


    private void init() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.manageAddressRv.setLayoutManager(linearLayoutManager);
        addressAdapter = new ManageAddressAdapter(this, this);
        binding.manageAddressRv.setAdapter(addressAdapter);
        binding.manageBackTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.manage_back_tv:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBtnClick(int Pos, String type) {
        if (type.equals("EDIT")) {
//            startActivity(new Intent(ManageAddressActivity.this, CurrentLocationMapActivity.class));
            Intent i = new Intent(ManageAddressActivity.this, AddNewAddressActivity.class);
            i.putExtra("EDIT", type);
            startActivity(i);
            this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        } else {
            Toast.makeText(getApplicationContext(), type, Toast.LENGTH_SHORT).show();
        }
    }

    private void dologin() {
        binding.addNewAddressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageAddressActivity.this, AddNewAddressActivity.class);
                startActivity(i);
            }
        });
    }
}
