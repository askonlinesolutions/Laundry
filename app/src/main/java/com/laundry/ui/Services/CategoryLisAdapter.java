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
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class CategoryLisAdapter extends RecyclerView.Adapter<CategoryLisAdapter.Viewholder> {

    private List<ServiceResponse.DataEntity.CategoryEntity> categoryList;
    private Context context;
    String category_id;
    private int rowindex = 0;
    private CategoryListInterface categoryListInterface;


    CategoryLisAdapter(Context context, CategoryListInterface categoryListInterface, List<ServiceResponse.DataEntity.CategoryEntity> categoryList /*ArrayList<ServiceResponse.DataEntity> serviseList*/) {

        this.context = context;

        this.categoryListInterface = categoryListInterface;
        this.categoryList = categoryList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_button, viewGroup, false);
        Viewholder vh = new Viewholder(view);
        return new Viewholder(view) {
        };


    }

    @Override
    public void onBindViewHolder(@NonNull CategoryLisAdapter.Viewholder viewholder, final int i) {


        viewholder.textservices.setText(categoryList.get(i).getCategory_name());

        if (categoryList.get(i).getCategory_image() != null) {
            Picasso.with(context).
                    load(Constant.IMAGE_BASE_URL + categoryList.get(i).getCategory_image()) // URL or file
                    .into(viewholder.img_shirt);

        } else if (categoryList.get(i).getCategory_image().equals("")) {
            viewholder.img_shirt.setImageResource(R.drawable.ic_tshirt);
        }

        if (rowindex == 0) {
            category_id = categoryList.get(i).getCategory_id();
            categoryListInterface.onCategoryClicked(rowindex, category_id);
            viewholder.textservices.setTextColor(context.getResources().getColor(R.color.white_color));
            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.circlur_skybackground));
        } else {
            viewholder.textservices.setTextColor(context.getResources().getColor(R.color.sky_blue));
            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.hollow_sky_blue_background));
//            viewholder.img_shirt.setImageResource(R.drawable.ic_shirt);
        }

//        categoryListInterface.onCategoryClicked(i);
        viewholder.services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowindex = i;
                notifyDataSetChanged();
//                categoryListInterface.onCategoryClicked(i);
            }
        });
        if (rowindex == i) {
            category_id = categoryList.get(i).getCategory_id();
            categoryListInterface.onCategoryClicked(i, category_id);
            viewholder.textservices.setTextColor(context.getResources().getColor(R.color.white_color));
            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.circlur_skybackground));
//            viewholder.img_shirt.setImageResource(R.drawable.ic_tshirt);
        } else {
            viewholder.textservices.setTextColor(context.getResources().getColor(R.color.sky_blue));
            viewholder.services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.hollow_sky_blue_background));
//            viewholder.img_shirt.setImageResource(R.drawable.ic_shirt);
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


        if (categoryList.size() != 0) {

            return categoryList.size();
        } else {
            return 0;
        }


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
            services = itemView.findViewById(R.id.services);
            img_shirt = itemView.findViewById(R.id.img_shirt);
            textservices = itemView.findViewById(R.id.textservices);
//            services.setOnClickListener(this);

            textservices.setTextColor(context.getResources().getColor(R.color.sky_blue));
            services.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.hollow_sky_blue_background));

//            categoryListInterface.onCategoryClicked(getAdapterPosition());


        }

        @Override
        public void onClick(View v) {

//            servicesAdapterInterface.getDetails(getAdapterPosition());

        }
    }

    public interface CategoryListInterface {
        void onCategoryClicked(int pos, String category_id);


    }
}
