package com.laundry.ui.offer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.help.HelpAdapter;

public class OfferrAdapter extends RecyclerView.Adapter<OfferrAdapter.ViewHolder> {


    boolean flag = true;
    private Context context;

    OfferrAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public OfferrAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_list_items, viewGroup, false);
        OfferrAdapter.ViewHolder vh = new OfferrAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final OfferrAdapter.ViewHolder viewHolder, int i) {
//        viewHolder.textView.setText(name.get(i).toString());


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
//            arrow = itemView.findViewById(R.id.arrow_iv);
//            tvExpand = itemView.findViewById(R.id.tvExpand);
        }
    }


}
