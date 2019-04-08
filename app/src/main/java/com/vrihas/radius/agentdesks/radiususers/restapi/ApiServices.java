package com.vrihas.radius.agentdesks.radiususers.restapi;

import com.vrihas.radius.agentdesks.radiususers.models.UserResponse;
import com.vrihas.radius.agentdesks.radiususers.utils.AppConstants;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiServices {

    @GET(AppConstants.REQUEST_USER_LIST)
    Call<UserResponse> getEventsResponse();

}
