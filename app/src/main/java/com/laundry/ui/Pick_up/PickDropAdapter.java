package com.laundry.ui.Pick_up;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laundry.R;


class PickDropAdapter extends RecyclerView.Adapter<PickDropAdapter.ViewHolder> {

    Context context;
    public PickDropAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_pick_drop,viewGroup,false);
        PickDropAdapter.ViewHolder vh =new PickDropAdapter.ViewHolder(view);
        return new PickDropAdapter.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull PickDropAdapter.ViewHolder viewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
