package com.laundry.ui.manageAddress;

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
import com.laundry.ui.offer.OfferrAdapter;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {


    boolean flag = true;
    private Context context;
    private OnBtnClickListener onBtnClickListener;

    ManageAddressAdapter(Context context, OnBtnClickListener onBtnClickListener) {
        this.context = context;
        this.onBtnClickListener = onBtnClickListener;

    }

    @NonNull
    @Override
    public ManageAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manage_address_list_items, viewGroup, false);
        ManageAddressAdapter.ViewHolder vh = new ManageAddressAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ManageAddressAdapter.ViewHolder viewHolder, int i) {
//        viewHolder.textView.setText(name.get(i).toString());


    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView editBtn, deleteBtn;
        LinearLayout main_layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            editBtn = itemView.findViewById(R.id.edit_btn_tv);
            deleteBtn = itemView.findViewById(R.id.delete_btn_tv);
            main_layout=itemView.findViewById(R.id.main_layout);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    onBtnClickListener.onBtnClick(getAdapterPosition(), "DELETE");

                    main_layout.removeAllViews();
                    notifyItemRemoved(getAdapterPosition());
                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBtnClickListener.onBtnClick(getAdapterPosition(), "EDIT");
                }
            });

        }
    }

    interface OnBtnClickListener {
        void onBtnClick(int Pos, String type);
    }

}
