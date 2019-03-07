package com.laundry.ui.MyPayment;

import android.app.Dialog;
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

import com.google.maps.internal.ApiResponse;
import com.laundry.R;
import com.laundry.Utils.MySharedPreference;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.databinding.ActivityPaymentMethodBinding;
import com.laundry.ui.MyPayment.vo.AddPaymentCardResponse;
import com.laundry.ui.MyPayment.vo.PaymentDeleteResponse;
import com.laundry.ui.profile.vo.ProfileResponse;

import java.util.ArrayList;

import retrofit2.Call;

import static com.laundry.Utils.Utility.isNetworkConnected;


public class PaymentMethodActivity extends AppCompatActivity implements View.OnClickListener, OnResponseInterface, PaymentMethodAdapter.OnBtnClickListener {
    ActivityPaymentMethodBinding binding;

    TextView login_title, add_new_card, cancel_btn, playnowbtn;
    EditText cardNoTv, cardTypeTv, cardTrans;
    RecyclerView pament_recycler;
    ImageView btncross;
    String userId, cardNo, cardType, cardTran, usercard_id;
    ArrayList<ProfileResponse.Payment_cardEntity> paymentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_method);
        getPaymentList();

        inIt();

    }


    private void getPaymentList() {
        /*Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        if (args != null) {
            paymentList = (ArrayList<ProfileResponse.Payment_cardEntity>) args.getSerializable("ARRAYLIST");

        }*/

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

        if (isNetworkConnected(this)) {
            callGetProfileApi();
        } else {
            Toast.makeText(this, "Please Connect Network", Toast.LENGTH_SHORT).show();
        }

//        setPaymentAdapter();
    }


    private void setPaymentAdapter() {

        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, paymentList, this);
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

        cardNoTv = dialog.findViewById(R.id.card_number_tv);
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
                dialog.dismiss();

            }

        });

    }

    private void callGetProfileApi() {

        new Utility().showProgressDialog(this);
        Call<ProfileResponse> call = APIClient.getInstance().getApiInterface().getProgileDetails(userId);
        new ResponseListner(this).getResponse(call);

    }


    private void callAddPaymentCardApi() {
        cardType = cardTypeTv.getText().toString().trim();
        cardNo = cardNoTv.getText().toString();
        cardTran = cardTrans.getText().toString().trim();
        new Utility().showProgressDialog(this);
        Call<AddPaymentCardResponse> call = APIClient.getInstance().getApiInterface().addPaymentCard(cardType, cardNo, cardTran, userId);
        new ResponseListner(this).getResponse(call);

    }


    private void callDeleteapi(String usercard_id) {
        new Utility().showProgressDialog(this);
        Call<PaymentDeleteResponse> call = APIClient.getInstance().getApiInterface().getpaymentdelete(usercard_id);
        Log.e("MyOrderUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }

    @Override
    public void onApiResponse(Object response) {

        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof AddPaymentCardResponse) {
                    AddPaymentCardResponse apiResponse = (AddPaymentCardResponse) response;
                    new Utility().hideDialog();
                    if (apiResponse.isStatus()) {
                        callGetProfileApi();
                        Toast.makeText(this, apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response instanceof ProfileResponse) {
                    ProfileResponse profileResponse = (ProfileResponse) response;
                    new Utility().hideDialog();
                    if (profileResponse.isStatus()) {
                        paymentList.clear();
                        if (profileResponse.getPayment_card().size() != 0) {

                            paymentList.addAll(profileResponse.getPayment_card());
                            setPaymentAdapter();
                        }

                    }
                } else if (response instanceof PaymentDeleteResponse) {
                    PaymentDeleteResponse paymentDeleteResponse = (PaymentDeleteResponse) response;
                    new Utility().hideDialog();
                    if (paymentDeleteResponse.isStatus()) {
                        Toast.makeText(this, paymentDeleteResponse.getMsg(), Toast.LENGTH_SHORT).show();

                    } else {

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
        new Utility().hideDialog();
        Log.d("TAG", "onApiFailure: " + message);
    }

    @Override
    public void onBtnClick(int Pos, String usercard_id) {

        callDeleteapi(usercard_id);

    }


}
