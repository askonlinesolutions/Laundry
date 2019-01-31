package com.laundry.ui.FAQ;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;



class FaqtAdapter extends RecyclerView.Adapter<FaqtAdapter.Viewholder> {
    Context context;
    private boolean isVisible = true;
    private boolean isShow = true;
    public FaqtAdapter(Context context) {

        this.context =context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.faq_list,viewGroup,false);
        FaqtAdapter.Viewholder vh =new FaqtAdapter.Viewholder(view);
        return new FaqtAdapter.Viewholder(view) {
        };
}

    @Override
    public void onBindViewHolder(@NonNull final FaqtAdapter.Viewholder viewholder, int i) {
        viewholder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    viewholder.tvExpand.setVisibility(View.VISIBLE);
                    viewholder.arrow.setImageResource(R.drawable.ic_up_arrow);
                    isVisible = false;
                }else
                {
                    viewholder.tvExpand.setVisibility(View.GONE);
                    viewholder.arrow.setImageResource(R.drawable.ic_down_arrow);
                    isVisible = true;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return 4;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvExpand;
        ImageView arrow;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvExpand=itemView.findViewById(R.id.tv_Expnd);
            arrow=itemView.findViewById(R.id.arrow_iv);
        }
    }
}
