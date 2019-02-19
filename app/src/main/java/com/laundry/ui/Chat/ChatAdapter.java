package com.laundry.ui.Chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laundry.R;

class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Viewholder> {

    Context context;


    public ChatAdapter( Context context) {
        this.context=context;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_list, viewGroup, false);
        ChatAdapter.Viewholder vh = new ChatAdapter.Viewholder(view);
        return new ChatAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.Viewholder viewholder, int i) {

    }



    @Override
    public int getItemCount() {
        return 3;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
