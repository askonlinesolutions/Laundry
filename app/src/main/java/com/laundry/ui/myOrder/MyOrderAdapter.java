package com.laundry.ui.myOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.myOrder.vo.MyOrderResponse;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    Context context;
    String conversationId, userName, userId;
    //    ArrayList image;
    private ArrayList<MyOrderResponse.DataEntity> orderList;
    MessageAdapterInterface messageAdapterInterface;

    public MyOrderAdapter(Context context, ArrayList<MyOrderResponse.DataEntity> orderList, MessageAdapterInterface messageAdapterInterface) {
        this.context = context;
        this.orderList = orderList;
        this.messageAdapterInterface = messageAdapterInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_list_items, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.textView.setText(name.get(i).toString());

        viewHolder.orderNoTv.setText(orderList.get(i).getOrderdetail_order_no());
        viewHolder.itemsTv.setText(orderList.get(i).getItemCount());
        viewHolder.ammountTv.setText(orderList.get(i).getOrderdetail_totalprice());
        viewHolder.pickupDateTv.setText(orderList.get(i).getOrderdetail_pickup());
        viewHolder.dropDateTv.setText(orderList.get(i).getOrderdetail_drop());

    }


    @Override
    public int getItemCount() {

        if (orderList.size() != 0) {
            return orderList.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderNoTv, itemsTv, ammountTv, pickupDateTv, dropDateTv, statusTv;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderNoTv = itemView.findViewById(R.id.order_no_tv);
            itemsTv = itemView.findViewById(R.id.items_tv);
            ammountTv = itemView.findViewById(R.id.amount_tv);
            pickupDateTv = itemView.findViewById(R.id.order_pickup_date_tv);
            dropDateTv = itemView.findViewById(R.id.order_drop_date_tv);
            statusTv = itemView.findViewById(R.id.order_statuse_tv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    messageAdapterInterface.click(getAdapterPosition());

                }
            });
        }
    }

    interface MessageAdapterInterface {
        void click(int position);
    }


}
