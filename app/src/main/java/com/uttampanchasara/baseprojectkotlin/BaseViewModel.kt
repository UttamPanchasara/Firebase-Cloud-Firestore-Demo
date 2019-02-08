package com.uttampanchasara.baseprojectkotlin

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.data.network.model.ApiError
import com.uttampanchasara.baseprojectkotlin.data.network.model.ErrorResponse
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView
import com.uttampanchasara.baseprojectkotlin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.Exception
import javax.net.ssl.HttpsURLConnection

/**
 * Created by Uttam Panchasara on 11-28-2018.
 */
open class BaseViewModel(val mDataManager: DataManager,
                         val mSchedulerProvider: SchedulerProvider,
                         val mCompositeDisposable: CompositeDisposable) : ViewModel() {

    companion object {
        const val TAG = "BaseViewModel"
    }

    var view: BaseView? = null

    open fun isViewAttached(): Boolean {
        return view != null
    }

    open fun onAttachView(view: BaseView) {
        this.view = view
    }

    fun onDetachView() {
        mCompositeDisposable.dispose()
        view = null
    }

    open fun getBaseView(): BaseView? {
        return view
    }

    fun handleApiError(error: ANError) {

        if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.CONNECTION_ERROR) {
            getBaseView()?.onError(R.string.connection_error)
            return
        }

        if (error.errorCode == AppConstants.API_STATUS_CODE_LOCAL_ERROR && error.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {
            getBaseView()?.onError(R.string.api_retry_error)
            return
        }

        if (error.errorBody == null) {
            getBaseView()?.onError(R.string.api_default_error)
            return
        }

        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        try {
            val apiError: ErrorResponse = gson.fromJson<Any>(error.errorBody, ErrorResponse::class.java) as ErrorResponse
            if (apiError.error == null) {
                getBaseView()?.onError(R.string.api_default_error)
                return
            }

            when (error.errorCode) {
                403 -> {
                    getBaseView()?.onError(apiError.error.message)
                }
                400 -> {
                    getBaseView()?.onError(apiError.error.message)
                }
            }
        } catch (e: Exception) {
            try {
                val apiError: ApiError = gson.fromJson<Any>(error.errorBody, ApiError::class.java) as ApiError

                if (apiError.message == null) {
                    getBaseView()?.onError(R.string.api_default_error)
                    return
                }

                when (error.errorCode) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {

                        //getBaseView().openActivityOnTokenExpire()
                        getBaseView()?.onError(apiError.message)
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> {
                        getBaseView()?.onError(apiError.message)
                    }
                    else -> {
                        getBaseView()?.onError(apiError.message)
                    }
                }
            } catch (e: JsonSyntaxException) {
                Log.e(TAG, "handleApiError", e)
                getBaseView()?.onError(R.string.api_default_error)
            } catch (e: NullPointerException) {
                Log.e(TAG, "handleApiError", e)
                getBaseView()?.onError(R.string.api_default_error)
            }
        }
    }
}