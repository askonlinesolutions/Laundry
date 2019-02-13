package com.laundry.ui.Contact;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityContactBinding;

import com.laundry.R;
import com.laundry.ui.Contact.vo.ContactUsResponse;
import com.laundry.ui.settings.vo.SettingResponse;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;

public class ContactActivity extends AppCompatActivity implements OnResponseInterface {
    LinearLayout login_title;
    ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact);


        login_title = findViewById(R.id.login_title);
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (isNetworkConnected(this)) {
            callContactUsApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }


    }

    private void callContactUsApi() {

        new Utility().showProgressDialog(this);
        Call<ContactUsResponse> call = APIClient.getInstance().getApiInterface().getContacts();
        new ResponseListner(this).getResponse(call);

    }

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof ContactUsResponse) {
                    ContactUsResponse contactUsResponse = (ContactUsResponse) response;
                    new Utility().hideDialog();
                    if (contactUsResponse.isStatus()) {

                        binding.contactTxt.setText("+91-" + contactUsResponse.getData().get(0).getContact_phone());

                        binding.locationTxt.setText(contactUsResponse.getData().get(0).getContact_address());
//                        Intent i = new Intent(ChangePaawordActivity.this, DryCleanerActivity.class);
//                        startActivity(i);


                    }
                }

            } catch (Exception e) {
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
            new Utility().hideDialog();
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onApiFailure(String message) {

    }
}
