package com.vrihas.radius.agentdesks.radiususers.activities;

import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vrihas.radius.agentdesks.radiususers.R;
import com.vrihas.radius.agentdesks.radiususers.adapters.UserAdapter;
import com.vrihas.radius.agentdesks.radiususers.models.UserResponse;
import com.vrihas.radius.agentdesks.radiususers.restapi.ApiServices;
import com.vrihas.radius.agentdesks.radiususers.restapi.AppClient;
import com.vrihas.radius.agentdesks.radiususers.utils.DialogFactory;
import com.vrihas.radius.agentdesks.radiususers.utils.NetworkUtils;
import com.vrihas.radius.agentdesks.radiususers.utils.ProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressDialog = new ProgressDialog();
        initialise();
        userAdapter = new UserAdapter(this);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUsers.setAdapter(userAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                APICall();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        APICall();
    }

    private void initialise() {
        recyclerViewUsers = findViewById(R.id.recycler_view_users);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutUsers);
    }

    private void APICall() {
        progressDialog.showDialog("Loading users. Please wait...", this);
        ApiServices services = AppClient.getInstance().createService(ApiServices.class);
        Call<UserResponse> call = services.getUserResponse();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse != null) {
                        userAdapter.setUserDataList(userResponse.getUserDataList());
                        userAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(HomeActivity.this, R.string.failure_msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.hideDialog();
                if (!NetworkUtils.isNetworkAvailable(HomeActivity.this)) {
                    DialogFactory.showDialog(DialogFactory.CONNECTION_PROBLEM_DIALOG, HomeActivity.this, clickListenerPositive, null, false, getString(R.string.network_issue_title), getString(R.string.network_issue_details), getString(R.string.retry));
                } else {
                    Toast.makeText(HomeActivity.this, getResources().getString(R.string.failure_msg), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private DialogInterface.OnClickListener clickListenerPositive = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            APICall();
        }
    };


}
