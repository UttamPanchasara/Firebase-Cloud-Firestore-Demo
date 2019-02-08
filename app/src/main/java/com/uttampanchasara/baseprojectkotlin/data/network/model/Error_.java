package com.uttampanchasara.baseprojectkotlin.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error_ {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("reason")
    @Expose
    private String reason;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}