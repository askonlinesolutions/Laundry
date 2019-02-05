package com.laundry.ui.Fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laundry.R;

class MaunAdapter extends RecyclerView.Adapter<MaunAdapter.Viewholder> {

    private Context context;
    private Interface_AdapterMenu click;

    MaunAdapter(Context context, Interface_AdapterMenu click) {

        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menulist, viewGroup, false);
        Viewholder vh = new Viewholder(view);
        return new Viewholder(view) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull MaunAdapter.Viewholder viewholder, int i) {

    }


    @Override
    public int getItemCount() {
        return 6;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView minus, plus;
        TextView integer_number;
        int start = 1;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_menu);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            integer_number = itemView.findViewById(R.id.integer_number);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    click.click_AdapterMenu(getAdapterPosition());
//                }
//            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (start > 1) {
                        start = start - 1;
                        integer_number.setText("" + start);
                        // displaytext(start);
                    }
                }
            });
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    start = start + 1;
                    integer_number.setText("" + start);
//                    displaytext(start);
                    click.click_AdapterMenu(getAdapterPosition());
                }

            });

        }
    }

    private void displaytext(int start) {
//         integer_number = itemView.findViewById(R.id.integer_number);
//        integer_number.setText(""  + start);
    }

    interface Interface_AdapterMenu {
        void click_AdapterMenu(int adapterPosition);

    }
}
