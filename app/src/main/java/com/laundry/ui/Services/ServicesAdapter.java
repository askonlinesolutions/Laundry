package com.laundry.ui.Services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.Viewholder> {

    private ArrayList<ServiceResponse.DataEntity> serviseList;
    private ServicesAdapterInterface servicesAdapterInterface;
    private Context context;
    private int rowindex = 0;
    int pos;
    private String servic_id;


    ServicesAdapter(Context context, ServicesAdapterInterface servicesAdapterInterface, int pos, ArrayList<ServiceResponse.DataEntity> serviseList) {

        this.context = context;
        this.pos = pos;
        this.servicesAdapterInterface = servicesAdapterInterface;
        this.serviseList = serviseList;

    }

    @NonNull
    @Override
    public ServicesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_list_items, viewGroup, false);
        ServicesAdapter.Viewholder vh = new ServicesAdapter.Viewholder(view);
        return new ServicesAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.Viewholder viewholder, final int i) {


        viewholder.textservices.setText(serviseList.get(i).getName());

        /*viewholder.item_view.setVisibility(View.VISIBLE);*/
        if (rowindex == 0) {
            servic_id = serviseList.get(i).getId();
            servicesAdapterInterface.onServicesClicked(rowindex, servic_id);
            viewholder.item_view.setVisibility(View.VISIBLE);
        } else {
            viewholder.item_view.setVisibility(View.GONE);
        }
        viewholder.services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowindex = i;
                notifyDataSetChanged();
                servic_id = serviseList.get(i).getId();
                servicesAdapterInterface.onServicesClicked(i, servic_id);

            }
        });
        if (rowindex == i) {
            viewholder.item_view.setVisibility(View.VISIBLE);
            servic_id = serviseList.get(i).getId();
            servicesAdapterInterface.onServicesClicked(i, servic_id);
        } else {
            viewholder.item_view.setVisibility(View.GONE);

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
        LinearLayout services;
        ImageView img_shirt;
        TextView textservices;
        View item_view;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            services = itemView.findViewById(R.id.services);
//            img_shirt = itemView.findViewById(R.id.img_shirt);
            textservices = itemView.findViewById(R.id.textservices);
            item_view = itemView.findViewById(R.id.item_view);
            /*item_view.setVisibility(View.VISIBLE);*/

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    servic_id = serviseList.get(getAdapterPosition()).getId();
//                    servicesAdapterInterface.onServicesClicked(getAdapterPosition(), servic_id);
//                }
//            });
        }

    }

    public interface ServicesAdapterInterface {
        void onServicesClicked(int pos, String serviceId);


    }


}
