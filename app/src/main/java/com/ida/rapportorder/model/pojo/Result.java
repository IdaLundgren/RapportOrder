package com.ida.rapportorder.model.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public Result() {
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
