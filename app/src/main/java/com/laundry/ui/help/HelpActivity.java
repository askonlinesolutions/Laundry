package com.laundry.ui.help;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.laundry.R;
import com.laundry.databinding.ActivityHelpBinding;
import com.laundry.ui.myOrder.MyOrderAdapter;

public class HelpActivity extends AppCompatActivity {

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
    }
}
