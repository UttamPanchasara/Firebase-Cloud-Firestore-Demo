package com.uttampanchasara.baseprojectkotlin.data.network

import com.uttampanchasara.baseprojectkotlin.data.network.model.SignUpResponse

import java.util.HashMap

import io.reactivex.Single

interface ApiHelper {
    fun doSignUp(key: String, body: HashMap<String, Any>): Single<SignUpResponse>
}