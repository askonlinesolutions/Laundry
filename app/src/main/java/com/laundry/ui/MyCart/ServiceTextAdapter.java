package com.laundry.ui.MyCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laundry.R;
import com.laundry.Utils.Constant;
import com.laundry.ui.MyCart.vo.CartDetailsResponse;
import com.laundry.ui.MyCart.vo.TextResponse;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ServiceTextAdapter extends RecyclerView.Adapter<ServiceTextAdapter.Viewholder> {

    private Context context;
    private ArrayList<TextResponse.DataEntity> textList;

    ServiceTextAdapter(Context context, ArrayList<TextResponse.DataEntity> textList) {
        this.context = context;
        this.textList = textList;

    }

    @NonNull
    @Override
    public ServiceTextAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_text_list_item, viewGroup, false);
        return new ServiceTextAdapter.Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceTextAdapter.Viewholder holder, int i) {

        holder.txtValue.setText("$ " +new DecimalFormat("##.##").format(Double.valueOf(textList.get(i).getPerValue())) );
        holder.txtName.setText(textList.get(i).getTax_name());
    }


    @Override
    public int getItemCount() {

        if (textList.size() > 0) {
            return textList.size();
        } else {
            return 0;
        }


    }

    class Viewholder extends RecyclerView.ViewHolder {

        TextView txtName, txtValue;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name_tv);
            txtValue = itemView.findViewById(R.id.txt_value_tv);

        }
    }


}
