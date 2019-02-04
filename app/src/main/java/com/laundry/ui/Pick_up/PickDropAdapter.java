package com.laundry.ui.Pick_up;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.laundry.R;
import com.laundry.clickListener.OnItemClickLisner;

import java.util.Calendar;
import java.util.List;


class PickDropAdapter extends RecyclerView.Adapter<PickDropAdapter.ViewHolder> {

    Context context;
    ImageView date, time;
    static final int DATE_DIALOG_ID = 0;
    private OnItemClickLisner onItemClickLisner;
    List<Address> listAddresses;
    private int yy, mm, dd, mHour, mMinute;

    public PickDropAdapter(Context context, List<Address> listAddresses, OnItemClickLisner onItemClickLisner) {
        this.context = context;
        this.listAddresses = listAddresses;
        this.onItemClickLisner = onItemClickLisner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_pick_drop, viewGroup, false);
        PickDropAdapter.ViewHolder vh = new PickDropAdapter.ViewHolder(view);
        return new PickDropAdapter.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull PickDropAdapter.ViewHolder viewHolder, int i) {

        if (listAddresses != null) {
            viewHolder.location_et.setText(listAddresses.get(i).getAddressLine(i));
        }


    }


    @Override
    public int getItemCount() {
        return listAddresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView date, time;
        EditText location_et;
        TextView tvDisplayDate, tvDisplaytime;
        LinearLayout setCurrentLocation;
        static final int DATE_DIALOG_ID = 0;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            date = itemView.findViewById(R.id.datePicker);
            time = itemView.findViewById(R.id.timePicker);
            location_et = itemView.findViewById(R.id.location_et);
            tvDisplaytime = itemView.findViewById(R.id.tvDisplaytime);
            tvDisplayDate = itemView.findViewById(R.id.tvDisplayDate);
            setCurrentLocation = itemView.findViewById(R.id.use_current_location);

            setCurrentLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickLisner.onItemClick(getAdapterPosition());
                }
            });

            gotodate();
            gototime();
        }


        private void gotodate() {
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    yy = c.get(Calendar.YEAR);
                    mm = c.get(Calendar.MONTH);
                    dd = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                                    tvDisplayDate.setText(dd + "-" + (mm + 1) + "-" + yy);


                                }

                            }, yy, mm, dd);
                    datePickerDialog.show();

                }

            });
        }

        private void gototime() {
            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    tvDisplaytime.setText(hourOfDay + ":" + minute);
                                }


                            }, mHour, mMinute, false);
                    timePickerDialog.show();

                }
            });
        }
    }
}


