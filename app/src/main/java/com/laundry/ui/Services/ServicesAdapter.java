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

import java.util.ArrayList;

class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.Viewholder>  {
        private ArrayList<String> name ;
    Context context;
    int rowindex=-1;
   ServicesAdapterInterface servicesAdapterInterface;

    /*public ServicesAdapter(Context context,ServicesAdapterInterface servicesAdapterInterface, ArrayList<String> name) {

        this.context =context;

       this.servicesAdapterInterface= servicesAdapterInterface;
        this.name =name;
    }*/

    public ServicesAdapter(Context context, ServicesAdapterInterface servicesAdapterInterface, ArrayList<String> name) {

        this.context =context;

        this.servicesAdapterInterface= servicesAdapterInterface;
        this.name =name;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_button,viewGroup,false);
        Viewholder vh =new Viewholder(view);
        return new Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.Viewholder viewholder, final int i) {
     viewholder.textservices.setText(name.get(i).toString());
//        viewholder.img_shirt.setImageResource(name.get(i).toString());
     viewholder.services.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             rowindex =i;
             notifyDataSetChanged();
         }
     });
     if (rowindex==i)
     {
         viewholder.textservices.setTextColor(context.getResources().getColor(R.color.white_color));
            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.circlur_skybackground));
            viewholder.img_shirt.setImageResource(R.drawable.ic_tshirt);
        } else {
           viewholder.textservices.setTextColor(context.getResources().getColor(R.color.sky_blue));
            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.hollow_sky_blue_background));
         viewholder.img_shirt.setImageResource(R.drawable.ic_shirt);
        }

     }

//     if (name.get(i).equals("1")) {
//           viewholder.textservices.setTextColor(context.getResources().getColor(R.color.white_color));
//            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.circlur_skybackground));
//        } else {
//           viewholder.textservices.setTextColor(context.getResources().getColor(R.color.sky_blue));
//            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.sky_blue_hollow_background));
//        }






    @Override
    public int getItemCount() {
        return name.size();
    }

//    @Override
//    public void onClick(View v) {
//
//    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout services;
        ImageView img_shirt;
        TextView textservices;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            services=itemView.findViewById(R.id.services);
            img_shirt=itemView.findViewById(R.id.img_shirt);
            textservices=itemView.findViewById(R.id.textservices);
//            services.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//            servicesAdapterInterface.getDetails(getAdapterPosition());

        }
    }

    public interface ServicesAdapterInterface {
        void getDetails(int pos) ;


    }
}
