package com.uttampanchasara.baseprojectkotlin.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("idToken")
    @Expose
    private String idToken;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("expiresIn")
    @Expose
    private String expiresIn;
    @SerializedName("localId")
    @Expose
    private String localId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

}