package com.laundry.ui.FAQ;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.laundry.R;
import com.laundry.ui.MyCart.MyCartActivity;


public class FAQActivity extends AppCompatActivity {
    RecyclerView faq;
    LinearLayout login_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        faq=findViewById(R.id.FAQ_rv);
        login_title=findViewById(R.id.login_title);
        back_btn();
        faq_rv();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        faq.setLayoutManager(linearLayoutManager);
        FaqtAdapter faqAdapter = new FaqtAdapter(this);
        faq.setAdapter(faqAdapter);
    }
}
