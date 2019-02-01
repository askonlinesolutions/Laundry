package com.laundry.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laundry.R;
import com.laundry.ui.LoginScreen.MainActivity;
import com.laundry.ui.Pick_up.PickupActivity;
import com.laundry.ui.forgotPassword.ForgotPasswordActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class WashAndIronFragment extends Fragment {
    RecyclerView rv_cart;
    Button schudle_btn;
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
        schudle_btn = getView().findViewById(R.id.schedule_pickup_tv);
        doLogin();
        init();
    }


    private void init() {
        rv_cart = getView().findViewById(R.id.cloth_name);
//        rv_cart.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv_cart.setAdapter(new MenuAdapter(getContext(), mAllMenuList, this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        rv_cart.setLayoutManager(linearLayoutManager);
        MaunAdapter maunAdapter = new MaunAdapter(getContext(), this);
        rv_cart.setAdapter(maunAdapter);


    }

    private void doLogin() {
        schudle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PickupActivity.class);
                startActivity(i);

            }
        });

    }


}
