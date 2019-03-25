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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;
import com.laundry.R;
import com.laundry.ui.MyPayment.PaymentMethodActivity;
import com.laundry.ui.Pick_up.PickupActivity;
import com.laundry.ui.Thanku.ThankuActivity;
import com.laundry.ui.manageAddress.vo.ManageAddressResponse;
import com.laundry.ui.offer.OfferrAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder> {


    boolean flag = true;
    private String addressId;
    private String manageKey;
    private Context context;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    double latitute, longitute;
    private ArrayList<ManageAddressResponse.DataEntity> addressList;

    private LinearLayout main_layout;
    private OnBtnClickListener onBtnClickListener;

    ManageAddressAdapter(Context context, ArrayList<ManageAddressResponse.DataEntity> addressList, OnBtnClickListener onBtnClickListener, String manageKey) {
        this.context = context;
        this.onBtnClickListener = onBtnClickListener;
        this.addressList = addressList;
        this.manageKey = manageKey;
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

//        if (addressList.get(i).getUseraddress_status().equals("1")) {
//            viewHolder.statusSwitch.setChecked(true);
//        } else {
//            viewHolder.statusSwitch.setChecked(false);
//        }
        if (manageKey.equals("manage")) {
            viewHolder.checkBox.setVisibility(View.GONE);
        } else if (manageKey.equals("pickup") || manageKey.equals("drop")) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    addressId = addressList.get(i).getUseraddress_id();
                    String lat = addressList.get(i).getAddress_lat();
                    String lng = addressList.get(i).getAddress_long();
                    onBtnClickListener.onBtnClick(i,"check",addressId,lat,lng,status);

//                    String address = addressList.get(i).getUseraddress_address();
//                    latitute = Double.valueOf(addressList.get(i).getAddress_lat());
//                    longitute = Double.valueOf(addressList.get(i).getAddress_long());
//                    if (manageKey.equals("pickup")) {
//
//                        Intent intent = new Intent(context, PickupActivity.class);
//                        intent.putExtra("address", address);
//                        intent.putExtra("pickup", "pickup");
//                        intent.putExtra("longitute", longitute);
//                        intent.putExtra("latitute", latitute);
//                        context.startActivity(intent);
//                    } else if (manageKey.equals("drop")) {
////                        Intent intent = new Intent(context, PickupActivity.class);
//                        Intent intent = new Intent();
//                        intent.putExtra("address", address);
//                        intent.putExtra("pickup", "drop");
//                        intent.putExtra("longitute", longitute);
//                        intent.putExtra("latitute", latitute);
//                        context.startActivity(intent);
////                        context.set(RESULT_OK, intent);
////                       context.startActivityForResult(intent, 1);
//                    }

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
        if (addressList.size() != 0) {
            return addressList.size();
        } else {
            return 0;
        }

    }

    private String status;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView editBtn, deleteBtn, titleTv, addressTv;
        //        Switch statusSwitch;
        CheckBox checkBox;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            editBtn = itemView.findViewById(R.id.edit_btn_tv);
            deleteBtn = itemView.findViewById(R.id.delete_btn_tv);
            titleTv = itemView.findViewById(R.id.address_tittle_tv);
            addressTv = itemView.findViewById(R.id.address_tv);
            checkBox = itemView.findViewById(R.id.checkbox);
//            statusSwitch = itemView.findViewById(R.id.address_status_switch);
            main_layout = itemView.findViewById(R.id.main_layout);

//
//            if (checkBox.isChecked()) {
//                Intent intent = new Intent(context, PickupActivity.class);
//                context.startActivity(intent);
//            }
//
////            statusSwitch.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    if (statusSwitch.isChecked()) {
//                        status = "1";
//                    } else {
//                        status = "0";
//                    }
//                    addressId = addressList.get(getAdapterPosition()).getUseraddress_id();
//
//                    onBtnClickListener.onBtnClick(getAdapterPosition(), "Status", addressId, status);
//
//                }
//            });


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
                    String lat = addressList.get(getAdapterPosition()).getAddress_lat();
                    String lng = addressList.get(getAdapterPosition()).getAddress_long();
                    onBtnClickListener.onBtnClick(getAdapterPosition(), "EDIT", addressId, status, lat, lng);
                }
            });

        }
    }

    interface OnBtnClickListener {
        void onBtnClick(int Pos, String type, String addressId, String Status, String lat, String lng);


    }

    private void showdialog(final int position) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_delete_dialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView btncross = (ImageView) dialog.findViewById(R.id.close_img);
        TextView cancel_btn = dialog.findViewById(R.id.cancel_btn);
        TextView playnowbtn = dialog.findViewById(R.id.playnowbtn);
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

                String lat = addressList.get(position).getAddress_lat();
                String lng = addressList.get(position).getAddress_long();

                onBtnClickListener.onBtnClick(position, "Delete", addressId, status, lat, lng);
                dialog.dismiss();
//                main_layout.removeAllViews();
//                notifyItemRemoved(position);


//                Intent i = new Intent(PaymentMethodActivity.this, ThankuActivity.class);
//                startActivity(i);
            }
        });


    }


}
