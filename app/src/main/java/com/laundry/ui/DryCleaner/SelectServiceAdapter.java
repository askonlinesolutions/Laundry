package com.laundry.ui.DryCleaner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.Services.ServicesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class SelectServiceAdapter extends RecyclerView.Adapter<SelectServiceAdapter.Viewholder> {

    private ArrayList<ServiceResponse.DataEntity.CategoryEntity> categoryList = new ArrayList<>();

    private OnItemClickLisner onItemClickLisner;
    private ArrayList<ServiceResponse.DataEntity> serviseList;
    private Context context;


    SelectServiceAdapter(Context context, ArrayList<ServiceResponse.DataEntity> serviseList, OnItemClickLisner onItemClickLisner) {
        this.context = context;
        this.onItemClickLisner = onItemClickLisner;
        this.serviseList = serviseList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_dry, viewGroup, false);
        Viewholder vh = new Viewholder(view);
        return new Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull SelectServiceAdapter.Viewholder holder, int i) {

        if (serviseList.size() != 0) {
            holder.text_wash.setText(serviseList.get(i).getName());

            if (serviseList.get(i).getImage() != null) {
                Picasso.with(context).
                        load(Constant.IMAGE_BASE_URL + serviseList.get(i).getImage()) // URL or file
                        .into(holder.serviceIv);

            }

        }

    }


    @Override
    public int getItemCount() {
        if (serviseList.size() != 0) {
            return serviseList.size();
        } else {
            return 0;
        }


    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView text_wash;
        RecyclerView press_image;
        ImageView serviceIv;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            text_wash = itemView.findViewById(R.id.text_wash);
            serviceIv = itemView.findViewById(R.id.img_wash);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int listSize = serviseList.size();
                    onItemClickLisner.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
