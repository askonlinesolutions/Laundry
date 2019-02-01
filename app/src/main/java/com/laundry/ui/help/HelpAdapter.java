package com.laundry.ui.help;

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

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ViewHolder> {

    boolean flag = true;
    private Context context;

    HelpAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public HelpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.help_list_items, viewGroup, false);
        HelpAdapter.ViewHolder vh = new HelpAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final HelpAdapter.ViewHolder viewHolder, int i) {
//        viewHolder.textView.setText(name.get(i).toString());
        viewHolder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag) {
                    viewHolder.tvExpand.setVisibility(View.VISIBLE);
                    viewHolder.arrow.setImageResource(R.drawable.ic_up_arrow_black);
                    flag = false;
                } else {
                    viewHolder.tvExpand.setVisibility(View.GONE);
                    viewHolder.arrow.setImageResource(R.drawable.ic_arrow_down_black);
                    flag = true;
                }

              /*  if (flag) {
                    viewHolder.tvExpand.setSingleLine(false);
//            flag.ivDown.setImageResource(R.drawable.ic_up_arrow);
//            holder.tvPlaceName.setVisibility(View.VISIBLE);
//            holder.location.setVisibility(View.VISIBLE);
                    flag = false;
                } else {
                    viewHolder.tvExpand.setSingleLine(true);
//            holder.ivDown.setImageResource(R.drawable.ic_down_arrow);
//            holder.tvPlaceName.setVisibility(View.GONE);
//            holder.location.setVisibility(View.GONE);
                    flag = true;
                }*/


            }
        });

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvExpand;
        ImageView arrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            arrow = itemView.findViewById(R.id.arrow_iv);
            tvExpand = itemView.findViewById(R.id.tvExpand);
        }
    }


}
