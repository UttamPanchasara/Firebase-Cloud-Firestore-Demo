package com.uttampanchasara.baseprojectkotlin.data.network

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.uttampanchasara.baseprojectkotlin.data.network.model.SignUpResponse
import io.reactivex.Single
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject
constructor(private val mApiHeader: ApiHeader) : ApiHelper {

    override fun doSignUp(key: String, body: HashMap<String, Any>): Single<SignUpResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.FIREBASE_SIGN_UP)
                .addQueryParameter(ApiEndPoint.API_KEYS.KEY, key)
                .addBodyParameter(body)
                .build()
                .getObjectSingle(SignUpResponse::class.java)
    }
}