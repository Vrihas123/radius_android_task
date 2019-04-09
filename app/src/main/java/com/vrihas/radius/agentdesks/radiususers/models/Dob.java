package com.vrihas.radius.agentdesks.radiususers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dob implements Serializable {

    @SerializedName("age")
    private String age;

    public String getAge() {
        return age;
    }
}
