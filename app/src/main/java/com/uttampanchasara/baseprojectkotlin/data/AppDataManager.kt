package com.uttampanchasara.baseprojectkotlin.data

import android.content.Context
import com.uttampanchasara.baseprojectkotlin.data.network.ApiHeader
import com.uttampanchasara.baseprojectkotlin.data.network.ApiHelper
import com.uttampanchasara.baseprojectkotlin.data.network.model.SignUpResponse
import com.uttampanchasara.baseprojectkotlin.data.prefs.PreferencesHelper
import com.uttampanchasara.baseprojectkotlin.di.ApplicationContext
import io.reactivex.Single
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@Singleton
class AppDataManager
@Inject internal constructor(@ApplicationContext val context: Context,
                             val dbHelper: DbHelper,
                             private val preferencesHelper: PreferencesHelper,
                             private val apiHelper: ApiHelper) : DataManager {
    override fun isSignedIn(): Boolean {
        return preferencesHelper.isSignedIn
    }

    override fun setIsSignedIn(isSignedIn: Boolean) {
        preferencesHelper.setIsSignedIn(isSignedIn)
    }

    override fun setUserEmail(userEmail: String?) {
        preferencesHelper.userEmail = userEmail
    }

    override fun getUserEmail(): String {
        return preferencesHelper.userEmail
    }

    override fun setUserName(userName: String?) {
        preferencesHelper.userName = userName
    }

    override fun getUserName(): String {
        return preferencesHelper.userName
    }

    override fun getWebAPIKey(): String {
        return preferencesHelper.webAPIKey
    }

    override fun setWebAPIKey(key: String?) {
        preferencesHelper.webAPIKey = key
    }

    override fun doSignUp(key: String, body: HashMap<String, Any>): Single<SignUpResponse> {
        return apiHelper.doSignUp(key, body)
    }

    override fun setAccessToken(accessToken: String?) {
        preferencesHelper.accessToken = accessToken
    }

    override fun getAccessToken(): String {
        return preferencesHelper.accessToken
    }
}