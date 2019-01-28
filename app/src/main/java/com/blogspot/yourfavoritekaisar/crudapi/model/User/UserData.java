package com.blogspot.yourfavoritekaisar.crudapi.model.User;

import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String lasr_name;

    @SerializedName("avatar")
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return lasr_name;
    }

    public void setLasr_name(String lasr_name) {
        this.lasr_name = lasr_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}


