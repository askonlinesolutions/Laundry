package com.laundry.ui.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laundry.R;

import java.util.ArrayList;
import java.util.Arrays;


public class WashAndIronFragment extends Fragment {
    RecyclerView rv_cart;

    ArrayList name = new ArrayList<>(Arrays.asList("Riyadh,Sulimania Dabbab", "Riyadh,Sulimania Dabbab "));
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wash_and_fold, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        rv_cart = getView().findViewById(R.id.cloth_name);
//        rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv_cart.setAdapter(new MenuAdapter(getContext(), mAllMenuList, this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,
                false);
        rv_cart.setLayoutManager(linearLayoutManager);
        MaunAdapter maunAdapter = new MaunAdapter(getContext(),this);
        rv_cart.setAdapter(maunAdapter);


    }


}
