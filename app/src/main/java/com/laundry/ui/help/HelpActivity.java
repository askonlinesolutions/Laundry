package com.laundry.ui.help;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.laundry.R;
import com.laundry.databinding.ActivityHelpBinding;
import com.laundry.ui.myOrder.MyOrderAdapter;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityHelpBinding binding;
    HelpAdapter helpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help);
        init();

    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.helpRv.setLayoutManager(linearLayoutManager);
        helpAdapter = new HelpAdapter(this);
        binding.helpRv.setAdapter(helpAdapter);
        binding.backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back_iv:
                onBackPressed();
                break;
        }
    }
}
