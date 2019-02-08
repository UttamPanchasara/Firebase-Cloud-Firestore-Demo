package com.uttampanchasara.baseprojectkotlin.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.uttampanchasara.baseprojectkotlin.di.ApplicationContext;
import com.uttampanchasara.baseprojectkotlin.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_WEB_API_KEY = "PREF_WEB_API_KEY";
    private static final String PREF_CURR_USER_NAME = "PREF_CURR_USER_NAME";
    private static final String PREF_CURR_USER_EMAIL = "PREF_CURR_USER_EMAIL";
    private static final String PREF_IS_SIGNED_IN = "PREF_IS_SIGNED_IN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public Boolean isSignedIn() {
        return mPrefs.getBoolean(PREF_IS_SIGNED_IN, false);
    }

    @Override
    public void setIsSignedIn(boolean isSignedIn) {
        mPrefs.edit().putBoolean(PREF_IS_SIGNED_IN, isSignedIn).apply();
    }

    @Override
    public String getWebAPIKey() {
        return mPrefs.getString(PREF_WEB_API_KEY, "");
    }

    @Override
    public void setWebAPIKey(String key) {
        mPrefs.edit().putString(PREF_WEB_API_KEY, key).apply();
    }

    @Override
    public void setUserEmail(String userEmail) {
        mPrefs.edit().putString(PREF_CURR_USER_EMAIL, userEmail).apply();
    }

    @Override
    public String getUserEmail() {
        return mPrefs.getString(PREF_CURR_USER_EMAIL, "");
    }

    @Override
    public void setUserName(String userName) {
        mPrefs.edit().putString(PREF_CURR_USER_NAME, userName).apply();
    }

    @Override
    public String getUserName() {
        return mPrefs.getString(PREF_CURR_USER_NAME, "");
    }
}