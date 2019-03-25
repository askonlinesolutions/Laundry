package com.laundry.ui.offer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.ui.MyCart.MyCartActivity;
import com.laundry.ui.Pick_up.PickupActivity;
import com.laundry.ui.help.HelpAdapter;
import com.laundry.ui.offer.vo.OfferResponse;

import java.util.ArrayList;

public class OfferrAdapter extends RecyclerView.Adapter<OfferrAdapter.ViewHolder> {

    private Context context;
    private ArrayList<OfferResponse.DataEntity> offerList;
    OfferItemClickListener offerItemClickListener;
    String offerKye;

    OfferrAdapter(Context context, ArrayList<OfferResponse.DataEntity> offerList, OfferItemClickListener offerItemClickListener, String offerKye) {
        this.context = context;
        this.offerList = offerList;
        this.offerItemClickListener = offerItemClickListener;
        this.offerKye = offerKye;
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
        viewHolder.offerTitle.setText(offerList.get(i).getDiscount_coupon());
        viewHolder.offerCode.setText(offerList.get(i).getDiscount_coupon_val());

        viewHolder.validFrom.setText(offerList.get(i).getDiscount_from());

        viewHolder.validTo.setText(offerList.get(i).getDiscount_to());

        if (offerKye.equals("offer")) {
            viewHolder.offerCheckBox.setVisibility(View.GONE);
        } else if (offerKye.equals("cart")) {
            viewHolder.offerCheckBox.setVisibility(View.VISIBLE);
        }
        viewHolder.offerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    offerItemClickListener.onOfferClick(i);
//            for (int i = 0; i < addressList.size(); i++) {
//                fonts.get(i).setSelected(false);
//            }
//            fonts.get(position).setSelected(isChecked);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        if (offerList.size() > 0) {
            return offerList.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView offerTitle, offerCode, validFrom, validTo, msg;
        CheckBox offerCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            offerTitle = itemView.findViewById(R.id.offer_title_tv);
            offerCode = itemView.findViewById(R.id.offer_code_tv);

            validFrom = itemView.findViewById(R.id.offer_from_tv);
            validTo = itemView.findViewById(R.id.offer_to_tv);
            msg = itemView.findViewById(R.id.msg_tv);
            offerCheckBox = itemView.findViewById(R.id.offer_checkbox);

        }
    }

    interface OfferItemClickListener {
        void onOfferClick(int pos);
    }

}
