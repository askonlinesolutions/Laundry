package com.laundry.ui.FAQ;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.laundry.R;
import com.laundry.Utils.Utility;
import com.laundry.WebServices.APIClient;
import com.laundry.WebServices.OnResponseInterface;
import com.laundry.WebServices.ResponseListner;
import com.laundry.ui.FAQ.vo.FaqResponse;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.myOrder.vo.MyOrderResponse;

import java.util.ArrayList;

import retrofit2.Call;


public class FAQActivity extends AppCompatActivity implements OnResponseInterface {
    RecyclerView faq;
    LinearLayout login_title;
    private ArrayList<FaqResponse.DataEntity> faqList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);


        faq = findViewById(R.id.FAQ_rv);
        login_title = findViewById(R.id.login_title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        faq.setLayoutManager(linearLayoutManager);
        back_btn();

        callfaqapi();
    }

    private void callfaqapi() {
        new Utility().showProgressDialog(this);
        Call<FaqResponse> call = APIClient.getInstance().getApiInterface().getfaq();
        Log.e("MyOrderUrl", call.request().url().toString());
        new ResponseListner(this).getResponse(call);

    }

    private void back_btn() {
        login_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void faq_rv() {

        FaqtAdapter faqAdapter = new FaqtAdapter(this, faqList);
        faq.setAdapter(faqAdapter);
    }

    @Override
    public void onApiResponse(Object response) {
        if (response != null) {
            new Utility().hideDialog();
            try {
                if (response instanceof FaqResponse) {
                    FaqResponse faqResponse = (FaqResponse) response;
                    if (faqResponse.isStatus()) {
                        faqList.clear();
                        if (faqResponse.data != null) {
                            faqList.addAll(faqResponse.data);
                            faq_rv();
                        }
                    }

                }

            } catch (Exception e) {
                Log.d("TAG", "onApiResponse: " + e.getMessage());
            }
        } else {
            new Utility().hideDialog();
        }
    }

    @Override
    public void onApiFailure(String message) {
        new Utility().hideDialog();
        Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
    }
}
