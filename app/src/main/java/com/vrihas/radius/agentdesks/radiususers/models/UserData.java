package com.vrihas.radius.agentdesks.radiususers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData  implements Serializable {

    @SerializedName("gender")
    private String gender;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private Name name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("cell")
    private String cell;

    @SerializedName("dob")
    private Dob dob;

    @SerializedName("location")
    private Location location;

    @SerializedName("picture")
    private Picture picture;

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public Dob getDob() {
        return dob;
    }

    public Location getLocation() {
        return location;
    }

    public Picture getPicture() {
        return picture;
    }
}
