package com.vrihas.radius.agentdesks.radiususers.fragments;


import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.vrihas.radius.agentdesks.radiususers.R;
import com.vrihas.radius.agentdesks.radiususers.models.UserData;
import com.wang.avi.AVLoadingIndicatorView;

public class UserDetailsFragment extends DialogFragment {

    private UserData userData;
    private TextView txtName, txtPhone, txtCell, txtStreet, txtCity, txtState, txtAge, txtGender, txtEmail;
    private ImageView imgUser, imgDismiss;
    private AVLoadingIndicatorView indicatorView;

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    public static UserDetailsFragment newInstance() {
        UserDetailsFragment fragment = new UserDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null && d.getWindow() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        txtName = view.findViewById(R.id.txt_detail_name);
        txtPhone = view.findViewById(R.id.txt_detail_phone);
        txtCell = view.findViewById(R.id.txt_detail_cell);
        txtStreet = view.findViewById(R.id.txt_detail_street);
        txtState = view.findViewById(R.id.txt_detail_state);
        txtCity = view.findViewById(R.id.txt_detail_city);
        txtAge = view.findViewById(R.id.txt_detail_age);
        txtGender = view.findViewById(R.id.txt_detail_gender);
        txtEmail = view.findViewById(R.id.txt_detail_email);
        imgUser = view.findViewById(R.id.img_detail_user);
        indicatorView = view.findViewById(R.id.indicatorDetail);
        imgDismiss = view.findViewById(R.id.img_arrow_dismiss);

        txtName.setText(userData.getName().getTitle() + " " + userData.getName().getFirst() + " " + userData.getName().getLast());
        txtEmail.setText(userData.getEmail());
        txtGender.setText(userData.getGender());
        txtAge.setText("Age : " + userData.getDob().getAge());
        txtPhone.setText(userData.getPhone());
        txtCell.setText(userData.getCell());
        txtCity.setText(userData.getLocation().getCity());
        txtStreet.setText(userData.getLocation().getStreet());
        txtState.setText(userData.getLocation().getState());

        Glide.with(getContext()).load(userData.getPicture().getLarge())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        indicatorView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        indicatorView.setVisibility(View.GONE);
                        return false;
                    }
                }).apply(RequestOptions.circleCropTransform())
                .into(imgUser);

        imgDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsFragment.this.dismiss();
            }
        });

        return view;
    }


}
