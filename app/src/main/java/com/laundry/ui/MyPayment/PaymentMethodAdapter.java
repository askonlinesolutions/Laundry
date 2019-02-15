package com.laundry.ui.MyPayment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.profile.vo.ProfileResponse;

import java.util.ArrayList;


class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.Viewholder> {

    Context context;
    ArrayList<ProfileResponse.Payment_cardEntity> paymentList;

    public PaymentMethodAdapter(Context context, ArrayList<ProfileResponse.Payment_cardEntity> paymentList) {
        this.paymentList = paymentList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment_menu, viewGroup, false);
        PaymentMethodAdapter.Viewholder vh = new PaymentMethodAdapter.Viewholder(view);
        return new PaymentMethodAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodAdapter.Viewholder viewholder, int i) {

        viewholder.cardNoTV.setText(paymentList.get(i).getUsercard_card_no());


    }


    @Override
    public int getItemCount() {
        if (paymentList.size() != 0) {
            return paymentList.size();
        } else {
            return 0;
        }

    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView activity_delete_btn, cardNoTV;
        ImageView cardIv;
        LinearLayout payment;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            activity_delete_btn = itemView.findViewById(R.id.activity_delete_btn);
            payment = itemView.findViewById(R.id.payment);
            cardNoTV = itemView.findViewById(R.id.card_no);
            cardIv = itemView.findViewById(R.id.card_image);
            activity_delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(0);
                }

                private void removeItem(int i) {
                    payment.removeAllViews();
                    notifyItemRemoved(i);

                }
            });
        }
    }
}
