package com.laundry.ui.offer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityOfferBinding;
import com.laundry.ui.help.HelpAdapter;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityOfferBinding binding;
    private OfferrAdapter offerrAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_offer);

        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.offerRv.setLayoutManager(linearLayoutManager);
        offerrAdapter = new OfferrAdapter(this);
        binding.offerRv.setAdapter(offerrAdapter);

        binding.offerBackIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.offer_back_iv:
                onBackPressed();
                break;
        }

    }
}
