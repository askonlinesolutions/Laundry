package com.laundry.ui.DryCleaner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.Services.ServicesActivity;

class SelectServiceAdapter extends RecyclerView.Adapter<SelectServiceAdapter.Viewholder>  {

    private OnItemClickLisner onItemClickLisner;

    private Context context;


    SelectServiceAdapter(Context context,OnItemClickLisner onItemClickLisner) {
        this.context =context;
        this.onItemClickLisner=onItemClickLisner;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_dry,viewGroup,false);
        Viewholder vh =new Viewholder(view);
        return new Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull SelectServiceAdapter.Viewholder viewholder, int i) {

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView text_wash;
        RecyclerView press_image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            text_wash =itemView.findViewById(R.id.text_wash);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLisner.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
