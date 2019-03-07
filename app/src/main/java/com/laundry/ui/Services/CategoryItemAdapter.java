package com.laundry.ui.Services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.clickListener.OnItemClickLisner;
import com.laundry.ui.DryCleaner.vo.ServiceResponse;
import com.laundry.ui.Fragment.MaunAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.Viewholder> implements OnItemClickLisner {

    private List<ServiceResponse.DataEntity.CategoryEntity.ItemsEntity> categoryItemsList;
    String item_id;
    int qty;
    private Context context;
    private int start /*= 0*/;
    private CategoryItemClickLictner categoryItemClickLictner;

    CategoryItemAdapter(Context context, List<ServiceResponse.DataEntity.CategoryEntity.ItemsEntity> categoryItemsList, CategoryItemClickLictner categoryItemClickLictner) {

        this.context = context;
        this.categoryItemClickLictner = categoryItemClickLictner;
        this.categoryItemsList = categoryItemsList;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menulist, viewGroup, false);
        CategoryItemAdapter.Viewholder vh = new CategoryItemAdapter.Viewholder(view);
        return new CategoryItemAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.Viewholder viewholder, int i) {

        viewholder.itemName.setText(categoryItemsList.get(i).getItem_name());
        viewholder.priceTv.setText("$ " + categoryItemsList.get(i).getItem_price());
        viewholder.disscountTv.setText("Discount $ " + categoryItemsList.get(i).getDiscount_price());
        if (categoryItemsList.get(i).getSelected_qnty() >= 0)
            viewholder.integer_number.setText(String.valueOf(categoryItemsList.get(i).getSelected_qnty()));

        if (categoryItemsList.get(i).getItem_image() != null) {
            Picasso.with(context).
                    load(Constant.IMAGE_BASE_URL + categoryItemsList.get(i).getItem_image()) // URL or file
                    .into(viewholder.itemImage);

        }
//        if (start != 0) {
//            start = 0;
//        }

        viewholder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start =categoryItemsList.get(i).getSelected_qnty();

                if (start > 0) {
                    start = start - 1;
                    viewholder.integer_number.setText("" + start);

//                        if (start == 1) {
//                            Toast.makeText(context, "please select atleast one item", Toast.LENGTH_SHORT).show();
////                            Toast.makeText(context,"please select atleast one item",)
//                        }

                    categoryItemsList.get(i).setSelected_qnty(start);
                    categoryItemClickLictner.onCategryItemClick(i, start);

                    String item_name = categoryItemsList.get(i).getItem_name();
                    String item_image = categoryItemsList.get(i).getItem_image();
                    String item_price = categoryItemsList.get(i).getItem_price();
                    int item_qnty = categoryItemsList.get(i).getSelected_qnty();
                    String item_id = categoryItemsList.get(i).getItem_name();
                    String discount_price = categoryItemsList.get(i).getDiscount_price();
                    // displaytext(start);

                    categoryItemClickLictner.itemDetails(i, item_name, item_image, item_price, item_qnty, item_id, discount_price);
                }
            }
        });

        viewholder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start =categoryItemsList.get(i).getSelected_qnty();
                start = start + 1;
                viewholder.integer_number.setText("" + start);
//                    displaytext(start);
                categoryItemsList.get(i).setSelected_qnty(start);
                categoryItemClickLictner.onCategryItemClick(i, start);

                String item_name = categoryItemsList.get(i).getItem_name();
                String item_image = categoryItemsList.get(i).getItem_image();
                String item_price = categoryItemsList.get(i).getItem_price();
                int item_qnty = Integer.valueOf(viewholder.integer_number.getText().toString());/*categoryItemsList.get(i).getSelected_qnty();*/
                String item_id = categoryItemsList.get(i).getItem_id();
                String discount_price = categoryItemsList.get(i).getDiscount_price();

                categoryItemClickLictner.itemDetails(i, item_name, item_image, item_price, item_qnty, item_id, discount_price);

            }

        });


    }


    @Override
    public int getItemCount() {

        if (categoryItemsList.size() != 0) {

            return categoryItemsList.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    class Viewholder extends RecyclerView.ViewHolder {
        TextView itemName, priceTv, disscountTv;
        TextView minus, plus;
        TextView integer_number;
        ImageView itemImage;


        Viewholder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.text_menu);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            itemImage = itemView.findViewById(R.id.item_iv);
            priceTv = itemView.findViewById(R.id.price_tv);
            disscountTv = itemView.findViewById(R.id.discount_tv);
            integer_number = itemView.findViewById(R.id.integer_number);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    click.click_AdapterMenu(getAdapterPosition());
//                }
//            });

//            minus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (start > 0) {
//                        start = start - 1;
//                        integer_number.setText("" + start);
//
////                        if (start == 1) {
////                            Toast.makeText(context, "please select atleast one item", Toast.LENGTH_SHORT).show();
//////                            Toast.makeText(context,"please select atleast one item",)
////                        }
//                        // displaytext(start);
//                        categoryItemsList.get(getAdapterPosition()).setSelected_qnty(start);
//                    }
//                }
//            });
//            plus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    start = start + 1;
//                    integer_number.setText("" + start);
////                    displaytext(start);
//                    categoryItemsList.get(getAdapterPosition()).setSelected_qnty(start);
//                    categoryItemClickLictner.onCategryItemClick(getAdapterPosition());
//                }
//
//            });

        }
    }

    private void displaytext(int start) {
//         integer_number = itemView.findViewById(R.id.integer_number);
//        integer_number.setText(""  + start);
    }

    interface CategoryItemClickLictner {
        void onCategryItemClick(int adapterPosition, int qnty);

        void itemDetails(int pos, String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price);

    }


}
