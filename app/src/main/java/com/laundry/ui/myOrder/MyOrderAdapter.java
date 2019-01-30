package com.laundry.ui.myOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.laundry.R;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private String[] descriptionData = {"Order\n Placed", "Driver\nAssigned", "Pickup\nOn_the_way", "Picked" ,"shipped"};

    Context context;
    String conversationId, userName, userId;
    //    ArrayList image;
    MessageAdapterInterface messageAdapterInterface;

    public MyOrderAdapter(Context context, MessageAdapterInterface messageAdapterInterface) {
        this.context = context;
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



    }




    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, id_tv, nameTv, dateTv, timeTv;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.texts);

            StateProgressBar stateProgressBar = (StateProgressBar) itemView.findViewById(R.id.step_progress_bar);
            stateProgressBar.setStateDescriptionData(descriptionData);
            stateProgressBar.setStateDescriptionTypeface("fonts/RobotoSlab-Light.ttf");
//            stateProgressBar.setStateNumberTypeface("fonts/Questrial.ttf");
            stateProgressBar.setStateDescriptionTypeface("fonts/RobotoSlab-Light.ttf");



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
