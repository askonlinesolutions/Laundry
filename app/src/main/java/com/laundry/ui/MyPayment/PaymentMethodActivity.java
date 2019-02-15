package com.laundry.ui.MyPayment;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityPaymentMethodBinding;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.Thanku.ThankuActivity;
import com.laundry.ui.profile.vo.ProfileResponse;

import java.util.ArrayList;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;


public class PaymentMethodActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface {
    ActivityPaymentMethodBinding binding;

    TextView login_title, add_new_card, cancel_btn, playnowbtn;
    EditText cardNoTv, cardTypeTv, cardTrans;
    RecyclerView pament_recycler;
    ImageView btncross;
    String userId, cardNo, cardType, cardTran;
    ArrayList<ProfileResponse.Payment_cardEntity> paymentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method);
        getPaymentList();

        inIt();

    }


    private void getPaymentList() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        if (args != null) {
            paymentList = (ArrayList<ProfileResponse.Payment_cardEntity>) args.getSerializable("ARRAYLIST");

        }

        MySharedPreference mySharedPreference = MySharedPreference.getInstance(this);
        userId = mySharedPreference.getUserId();
        Log.e("MyUserId", userId);


    }

    private void inIt() {
        pament_recycler = findViewById(R.id.pament_recycler);
        login_title = findViewById(R.id.login_title);
        add_new_card = findViewById(R.id.activity_add_new_card);
        btncross = findViewById(R.id.close_img);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        pament_recycler.setLayoutManager(linearLayoutManager);
        binding.loginTitle.setOnClickListener(this);
        binding.activityAddNewCard.setOnClickListener(this);

        setPaymentAdapter();
    }


    private void setPaymentAdapter() {

        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, paymentList);
        pament_recycler.setAdapter(paymentMethodAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_title:
                onBackPressed();
                break;

            case R.id.activity_add_new_card:
                showDialogpayment();
                break;
        }
    }

    private void showDialogpayment() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_pament_dialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btncross = (ImageView) dialog.findViewById(R.id.close_img);
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        playnowbtn = dialog.findViewById(R.id.playnowbtn);

        cardNoTv = dialog.findViewById(R.id.card_no);
        cardTypeTv = dialog.findViewById(R.id.card_type_tv);
        cardTrans = dialog.findViewById(R.id.ccv);

        btncross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        playnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isNetworkConnected(getApplication())) {
                    callAddPaymentCardApi();
                } else {
                    Toast.makeText(getApplication(), "Please Connect Network", Toast.LENGTH_SHORT).show();
                }


                Intent i = new Intent(PaymentMethodActivity.this, ThankuActivity.class);
                startActivity(i);
            }

        });

    }


    private void callAddPaymentCardApi() {


        new Utility().showProgressDialog(this);
//        Call<ProfileResponse> call = APIClient.getInstance().getApiInterface().addPaymentCard(userId,cardTran,cardTran,cardNo);
//        new ResponseListner(this).getResponse(call);

    }

    @Override
    public void onApiResponse(Object response) {

    }

    @Override
    public void onApiFailure(String message) {

    }
}
