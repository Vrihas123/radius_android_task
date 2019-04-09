package com.vrihas.radius.agentdesks.radiususers.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture implements Serializable {

    @SerializedName("large")
    private String large;

    @SerializedName("medium")
    private String medium;


    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }
}
