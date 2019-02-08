package com.uttampanchasara.baseprojectkotlin.data.prefs;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    Boolean isSignedIn();

    void setIsSignedIn(boolean isSignedIn);

    String getWebAPIKey();

    void setWebAPIKey(String key);

    void setUserEmail(String userEmail);

    String getUserEmail();

    void setUserName(String userName);

    String getUserName();

}