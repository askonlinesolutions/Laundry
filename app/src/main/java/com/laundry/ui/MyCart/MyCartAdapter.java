package com.laundry.ui.MyCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.ui.MyCart.vo.CartDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.Viewholder> {
    private Context context;
    private ArrayList<CartDetailsResponse.DataEntity> cartDetailsList;
    private OnCartClickListener onCartClickListener;
    private int start;

    MyCartAdapter(Context context, ArrayList<CartDetailsResponse.DataEntity> cartDetailsList, OnCartClickListener onCartClickListener) {
        this.context = context;
        this.cartDetailsList = cartDetailsList;
        this.onCartClickListener = onCartClickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_cart_menu, viewGroup, false);
        MyCartAdapter.Viewholder vh = new MyCartAdapter.Viewholder(view);
        return new MyCartAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.Viewholder viewholder, int i) {

        viewholder.itemName.setText(cartDetailsList.get(i).getOrderitem_name());
        viewholder.integer_number.setText(cartDetailsList.get(i).getOrderitem_qty());
        viewholder.priceTv.setText("$ " + cartDetailsList.get(i).getOrderitem_price());

        if (!cartDetailsList.get(i).getOrderitem_discount_price().equals("0") && cartDetailsList.get(i).getOrderitem_discount_price() != null) {
            viewholder.discountTv.setVisibility(View.VISIBLE);
            viewholder.line.setVisibility(View.VISIBLE);
            viewholder.up_line.setVisibility(View.VISIBLE);
            viewholder.discountTv.setText("$" + cartDetailsList.get(i).getOrderitem_discount_price() + " Discount");
        } else {
            viewholder.discountTv.setVisibility(View.INVISIBLE);
            viewholder.line.setVisibility(View.INVISIBLE);
            viewholder.up_line.setVisibility(View.INVISIBLE);
        }

        if (cartDetailsList.get(i).getOrderitem_image() != null) {
            Picasso.with(context).
                    load(Constant.IMAGE_BASE_URL + cartDetailsList.get(i).getOrderitem_image()) // URL or file
                    .into(viewholder.itemImage);
        }

        viewholder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start = Integer.valueOf(cartDetailsList.get(i).getOrderitem_qty());

                if (start > 0) {
                    start = start - 1;
                    viewholder.integer_number.setText("" + start);
                    String item_name = cartDetailsList.get(i).getOrderitem_name();
                    String item_image = cartDetailsList.get(i).getOrderitem_image();
                    String item_price = cartDetailsList.get(i).getOrderitem_price();
                    int item_qnty = Integer.valueOf(viewholder.integer_number.getText().toString());/*categoryItemsList.get(i).getSelected_qnty();*/
                    String item_id = cartDetailsList.get(i).getOrderitem_item_id();
                    String discount_price = cartDetailsList.get(i).getOrderitem_discount_price();
                    String service_id = cartDetailsList.get(i).getOrderitem_service_id();
                    String cat_id = cartDetailsList.get(i).getOrderitem_cat_id();
                    String order_time = cartDetailsList.get(i).getOrderitem_time();

                    onCartClickListener.itemDetails(i, item_name, item_image, item_price, item_qnty, item_id, discount_price, service_id, cat_id,order_time);

                }
            }
        });
        viewholder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start = Integer.valueOf(cartDetailsList.get(i).getOrderitem_qty());

                start = start + 1;
                viewholder.integer_number.setText("" + start);
                String item_name = cartDetailsList.get(i).getOrderitem_name();
                String item_image = cartDetailsList.get(i).getOrderitem_image();
                String item_price = cartDetailsList.get(i).getOrderitem_price();
                int item_qnty = Integer.valueOf(viewholder.integer_number.getText().toString());/*categoryItemsList.get(i).getSelected_qnty();*/
                String item_id = cartDetailsList.get(i).getOrderitem_item_id();
                String discount_price = cartDetailsList.get(i).getOrderitem_discount_price();
                String service_id = cartDetailsList.get(i).getOrderitem_service_id();
                String cat_id = cartDetailsList.get(i).getOrderitem_cat_id();
                String order_time = cartDetailsList.get(i).getOrderitem_time();

                onCartClickListener.itemDetails(i, item_name, item_image, item_price, item_qnty, item_id, discount_price, service_id, cat_id,order_time);

            }
        });


    }


    @Override
    public int getItemCount() {
        if (cartDetailsList.size() > 0) {
            return cartDetailsList.size();
        } else {
            return 0;
        }

    }

    class Viewholder extends RecyclerView.ViewHolder {
        View up_line;
        RelativeLayout menu;
        TextView minus, plus, integer_number, itemName, priceTv, discountTv, line;
        ImageView img_dlt, itemImage;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            img_dlt = itemView.findViewById(R.id.img_dlt);
            menu = itemView.findViewById(R.id.menu_list);
            itemName = itemView.findViewById(R.id.item_name);
            itemImage = itemView.findViewById(R.id.item_image);
            priceTv = itemView.findViewById(R.id.item_prise_tv);
            discountTv = itemView.findViewById(R.id.item_discount_tv);
            line = itemView.findViewById(R.id.line);
            up_line = itemView.findViewById(R.id.up_line);
            integer_number = itemView.findViewById(R.id.integer_number);

            img_dlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String service_id = cartDetailsList.get(getAdapterPosition()).getOrderitem_service_id();
                    String cat_id = cartDetailsList.get(getAdapterPosition()).getOrderitem_cat_id();
                    String item_id = cartDetailsList.get(getAdapterPosition()).getOrderitem_item_id();
                    String quantity = cartDetailsList.get(getAdapterPosition()).getOrderitem_qty();
                    String item_price = cartDetailsList.get(getAdapterPosition()).getOrderitem_price();
                    String discount_price = cartDetailsList.get(getAdapterPosition()).getOrderitem_discount_price();

                    onCartClickListener.removeItem(getAdapterPosition(), service_id, cat_id, item_id, quantity, item_price, discount_price);

                }

            });


        }
    }

    interface OnCartClickListener {

        void itemDetails(int pos, String item_name, String item_image, String item_price, int item_qnty, String item_id, String discount_price, String service_id, String cat_id,String order_time);

        void removeItem(int pos, String service_id, String cat_id, String item_id, String quantity, String item_price, String discount_price);
    }
}
