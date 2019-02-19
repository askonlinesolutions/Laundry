package com.laundry.ui.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;

public class ChatActivity extends AppCompatActivity {
    ImageView logintittle;
    RecyclerView chat_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        logintittle=findViewById(R.id.back_iv);
        chat_rv=findViewById(R.id.chat_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        chat_rv.setLayoutManager(linearLayoutManager);
        init();
        logintittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void init() {
        ChatAdapter chatAdapter = new ChatAdapter(this);
        chat_rv.setAdapter(chatAdapter);



    }
}
