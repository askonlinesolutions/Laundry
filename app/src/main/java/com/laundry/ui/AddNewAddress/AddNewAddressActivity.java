package com.laundry.ui.AddNewAddress;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.databinding.ActivityAddNewAddressBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;

import java.util.ArrayList;

public class AddNewAddressActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAddNewAddressBinding binding;
    String editKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_address);
        getPosition();
        init();
    }

    private void init() {

        if (editKey != null && editKey.equals("EDIT")) {
            binding.loginTitle.setText("Edit Address");
        } else {
            binding.loginTitle.setText("Add new Address");
        }

        binding.loginTitle.setOnClickListener(this);

        binding.mobileNoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (!s.toString().contains("+91 ")) {
                    binding.mobileNoEt.setText("+91 " + s.toString());
                    Selection.setSelection(binding.mobileNoEt.getText(), binding.mobileNoEt.getText().length());
                }

            }
        });

    }


    private void getPosition() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editKey = extras.getString("EDIT");
//            serviseList=getIntent().getSerializableExtra("arraylist");
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;
        }

    }
}
