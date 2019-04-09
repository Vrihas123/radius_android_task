package com.vrihas.radius.agentdesks.radiususers.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.vrihas.radius.agentdesks.radiususers.R;
import com.vrihas.radius.agentdesks.radiususers.fragments.UserDetailsFragment;
import com.vrihas.radius.agentdesks.radiususers.models.UserData;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private Context context;
    private LayoutInflater layoutInflater;
    private List<UserData> userDataList = new ArrayList<>();

    public UserAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        final UserData data = userDataList.get(i);
        holder.txtName.setText(data.getName().getTitle() + " " + data.getName().getFirst() + " " + data.getName().getLast());
        holder.txtPhone.setText(data.getPhone());
        Glide.with(context).load(data.getPicture().getLarge())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.indicatorView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.indicatorView.setVisibility(View.GONE);
                        return false;
                    }
                }).apply(RequestOptions.circleCropTransform())
                .into(holder.imgUser);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity) (context);
                FragmentManager fm = activity.getSupportFragmentManager();
                UserDetailsFragment userDetailsFragment = UserDetailsFragment.newInstance();
                userDetailsFragment.setUserData(data);
                userDetailsFragment.show(fm, "User Details Fragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView txtName, txtPhone;
        AVLoadingIndicatorView indicatorView;
        Button btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            indicatorView = itemView.findViewById(R.id.indicator);
            btnMore = itemView.findViewById(R.id.btn_more);
        }
    }
}
