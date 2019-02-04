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

class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.Viewholder> {
    private Context context;

    MyCartAdapter(Context context) {
        this.context = context;
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

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout menu;
        TextView minus, plus, integer_number;
        int start = 1;
        ImageView img_dlt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            img_dlt = itemView.findViewById(R.id.img_dlt);
            menu = itemView.findViewById(R.id.menu_list);
            integer_number = itemView.findViewById(R.id.integer_number);
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (start > 1) {
                        start = start - 1;
                        integer_number.setText("" + start);
                        // displaytext(start);
                    }
                }
            });
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    start = start + 1;
                    integer_number.setText("" + start);
//                    displaytext(start);

                }
            });
            img_dlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(1);
//                    MyCartAdapter.removeItem(getAdapterPosition());
                }

                private void removeItem(int position) {
                    ;
                    menu.removeAllViews();
//                    menu.removeAllViewsInLayout(position);
                    // to perform recycler view delete animations
                    // NOTE: don't call notifyDataSetChanged()
                    notifyItemRemoved(position);
                }
            });

        }
    }
}
