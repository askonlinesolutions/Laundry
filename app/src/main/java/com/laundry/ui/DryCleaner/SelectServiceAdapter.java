package com.laundry.ui.DryCleaner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laundry.R;

class SelectServiceAdapter extends RecyclerView.Adapter<SelectServiceAdapter.Viewholder>  {



    Context context;


    public SelectServiceAdapter(Context context) {
        this.context =context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_dry,viewGroup,false);
        Viewholder vh =new Viewholder(view);
        return new Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull SelectServiceAdapter.Viewholder viewholder, int i) {

    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView text_wash;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            text_wash =itemView.findViewById(R.id.text_wash);
        }
    }
}
