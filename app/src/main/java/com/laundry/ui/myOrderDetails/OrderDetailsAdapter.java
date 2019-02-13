package com.laundry.ui.myOrderDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.myOrder.MyOrderAdapter;
import com.laundry.ui.myOrder.vo.MyOrderResponse;
import com.laundry.ui.myOrderDetails.vo.OrderDetailsResponse;

import java.util.ArrayList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {


    Context context;
    private ArrayList<OrderDetailsResponse.DataEntity.ItemsEntity> orderItemList;


    public OrderDetailsAdapter(Context context, ArrayList<OrderDetailsResponse.DataEntity.ItemsEntity> orderItemList) {
        this.context = context;
        this.orderItemList = orderItemList;

    }

    @NonNull
    @Override
    public OrderDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_details_list_items, viewGroup, false);
        OrderDetailsAdapter.ViewHolder vh = new OrderDetailsAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder viewHolder, int i) {
//        viewHolder.textView.setText(name.get(i).toString());

        viewHolder.itemNameTv.setText(orderItemList.get(i).getOrderitem_name());
        viewHolder.itemsPriceTv.setText("$"+orderItemList.get(i).getOrderitem_price());
//        viewHolder.ammountTv.setText(orderList.get(i).getOrderdetail_totalprice());
//        viewHolder.pickupDateTv.setText(orderList.get(i).getOrderdetail_pickup());
//        viewHolder.dropDateTv.setText(orderList.get(i).getOrderdetail_drop());

    }


    @Override
    public int getItemCount() {

        if (orderItemList.size() != 0) {
            return orderItemList.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTv, itemsPriceTv;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemNameTv = itemView.findViewById(R.id.item_name_tv);
            itemsPriceTv = itemView.findViewById(R.id.item_prise_tv);


//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    orderId = orderItemList.get(getAdapterPosition()).getOrderdetail_id();
////                    messageAdapterInterface.click(getAdapterPosition(),orderId);
//
//                }
//            });
        }
    }

    interface MessageAdapterInterface {
        void click(int position, String orderId);
    }


}
