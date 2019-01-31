package com.laundry.ui.myOrderDetails;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityMyOrderDetailsBinding;
import com.laundry.ui.help.HelpActivity;

public class MyOrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMyOrderDetailsBinding binding;
    private boolean isTrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order_details);

        init();
    }

    private void init() {

        binding.viewDetailsTv.setOnClickListener(this);
        binding.backIv.setOnClickListener(this);
        binding.helpTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_details_tv:
                if (isTrue) {

                    binding.detaileLayout.setVisibility(View.VISIBLE);
                    binding.helpTv.setVisibility(View.VISIBLE);
                    binding.viewDetailsTv.setBackground(getResources().getDrawable(R.drawable.sky_blue_hollow_background));
                    binding.viewDetailsTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    isTrue = false;


                } else {

                    binding.detaileLayout.setVisibility(View.GONE);
                    binding.helpTv.setVisibility(View.INVISIBLE);
                    binding.viewDetailsTv.setBackground(getResources().getDrawable(R.drawable.background_button_sky));
                    binding.viewDetailsTv.setTextColor(getResources().getColor(R.color.white_color));
                    isTrue = true;
                }
                break;
            case R.id.back_iv:
                onBackPressed();
                break;

            case R.id.help_tv:
                Intent intent = new Intent(MyOrderDetailsActivity.this, HelpActivity.class);
                startActivity(intent);

                break;
        }
    }
}
