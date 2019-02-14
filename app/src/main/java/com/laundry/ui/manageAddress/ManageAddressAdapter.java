package com.laundry.ui.manageAddress;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.laundry.R;
import com.laundry.ui.MyPayment.PaymentMethodActivity;
import com.laundry.ui.Thanku.ThankuActivity;
import com.laundry.ui.manageAddress.vo.ManageAddressResponse;
import com.laundry.ui.offer.OfferrAdapter;

import java.util.ArrayList;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {


    boolean flag = true;
    TextView cancel_btn, playnowbtn;
    String addressId;
    ImageView btncross;
    private Context context;
    private ArrayList<ManageAddressResponse.DataEntity> addressList;

    LinearLayout main_layout;
    private OnBtnClickListener onBtnClickListener;

    ManageAddressAdapter(Context context, ArrayList<ManageAddressResponse.DataEntity> addressList, OnBtnClickListener onBtnClickListener) {
        this.context = context;
        this.onBtnClickListener = onBtnClickListener;
        this.addressList = addressList;
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
        viewHolder.titleTv.setText(addressList.get(i).getUseraddress_title());
        viewHolder.addressTv.setText(addressList.get(i).getUseraddress_address());

        if (addressList.get(i).getUseraddress_status().equals("1")) {
            viewHolder.statusSwitch.setChecked(true);
        } else {
            viewHolder.statusSwitch.setChecked(false);
        }

    }


    @Override
    public int getItemCount() {
        if (addressList.size() != 0) {
            return addressList.size();
        } else {
            return 0;
        }

    }

    String status;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView editBtn, deleteBtn, titleTv, addressTv;
        Switch statusSwitch;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            editBtn = itemView.findViewById(R.id.edit_btn_tv);
            deleteBtn = itemView.findViewById(R.id.delete_btn_tv);
            titleTv = itemView.findViewById(R.id.address_tittle_tv);
            addressTv = itemView.findViewById(R.id.address_tv);
            statusSwitch = itemView.findViewById(R.id.address_status_switch);
            main_layout = itemView.findViewById(R.id.main_layout);

            statusSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (statusSwitch.isChecked()) {
                        status = "1";
                    } else {
                        status = "0";
                    }
                    addressId = addressList.get(getAdapterPosition()).getUseraddress_id();

                    onBtnClickListener.onBtnClick(getAdapterPosition(), "Status", addressId, status);

                }
            });


            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    onBtnClickListener.onBtnClick(getAdapterPosition(), "DELETE");
                    showdialog(getAdapterPosition());
//                    main_layout.removeAllViews();
//                    notifyItemRemoved(getAdapterPosition());
                }


            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addressId = addressList.get(getAdapterPosition()).getUseraddress_id();

                    onBtnClickListener.onBtnClick(getAdapterPosition(), "EDIT", addressId, status);
                }
            });

        }
    }

    interface OnBtnClickListener {
        void onBtnClick(int Pos, String type, String addressId, String Status);


    }

    private void showdialog(final int position) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_delete_dialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btncross = (ImageView) dialog.findViewById(R.id.close_img);
        cancel_btn = dialog.findViewById(R.id.cancel_btn);
        playnowbtn = dialog.findViewById(R.id.playnowbtn);
        btncross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        playnowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressId = addressList.get(position).getUseraddress_id();
                onBtnClickListener.onBtnClick(position, "Delete", addressId, status);
                main_layout.removeAllViews();
                notifyItemRemoved(position);
                dialog.dismiss();


//                Intent i = new Intent(PaymentMethodActivity.this, ThankuActivity.class);
//                startActivity(i);
            }
        });


    }


}
