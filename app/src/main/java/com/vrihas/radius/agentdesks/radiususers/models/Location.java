package com.vrihas.radius.agentdesks.radiususers.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location implements Serializable {

    @SerializedName("street")
    private String street;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
