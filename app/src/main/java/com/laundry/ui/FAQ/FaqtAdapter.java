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
import com.laundry.ui.FAQ.vo.FaqResponse;
import com.laundry.ui.myOrder.vo.MyOrderResponse;

import java.util.ArrayList;


class FaqtAdapter extends RecyclerView.Adapter<FaqtAdapter.Viewholder> {

    private ArrayList<FaqResponse.DataEntity> faqList;


    Context context;
    private boolean isVisible = true;
    private boolean isShow = true;


    public FaqtAdapter(Context context, ArrayList<FaqResponse.DataEntity> faqList) {

        this.faqList = faqList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.faq_list, viewGroup, false);
        FaqtAdapter.Viewholder vh = new FaqtAdapter.Viewholder(view);
        return new FaqtAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull final FaqtAdapter.Viewholder viewholder, int i) {
//        viewholder.tvExpand.setText(faqList.get(i).);


        viewholder.question_tv.setText(faqList.get(i).getFaq_man_question());
        viewholder.tvExpand.setText(faqList.get(i).getFaq_man_answer());

        viewholder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    viewholder.tvExpand.setVisibility(View.VISIBLE);
                    viewholder.arrow.setImageResource(R.drawable.ic_up_arrow_black);
                    isVisible = false;
                } else {
                    viewholder.tvExpand.setVisibility(View.GONE);
                    viewholder.arrow.setImageResource(R.drawable.ic_arrow_down_black);
                    isVisible = true;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return faqList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView tvExpand, question_tv;
        ImageView arrow;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvExpand = itemView.findViewById(R.id.tv_Expnd);
            arrow = itemView.findViewById(R.id.arrow_iv);
            question_tv = itemView.findViewById(R.id.question_tv);


        }
    }

    interface FaqAdapterInterface {
        void click(int position, String orderId);
    }

}
