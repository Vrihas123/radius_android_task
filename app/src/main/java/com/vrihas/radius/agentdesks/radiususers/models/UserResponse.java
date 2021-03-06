package com.vrihas.radius.agentdesks.radiususers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse implements Serializable {

    @SerializedName("results")
    private List<UserData> userDataList;

    public List<UserData> getUserDataList() {
        return userDataList;
    }


}
