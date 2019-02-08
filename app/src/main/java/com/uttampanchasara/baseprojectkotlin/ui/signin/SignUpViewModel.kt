package com.uttampanchasara.baseprojectkotlin.ui.signin

import android.util.Log
import com.androidnetworking.error.ANError
import com.uttampanchasara.baseprojectkotlin.BaseViewModel
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.data.network.model.SignUpResponse
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseView
import com.uttampanchasara.baseprojectkotlin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class SignUpViewModel
@Inject constructor(mDataManager: DataManager,
                    mSchedulerProvider: SchedulerProvider,
                    mCompositeDisposable: CompositeDisposable) : BaseViewModel(mDataManager, mSchedulerProvider, mCompositeDisposable) {

    companion object {
        const val TAG = "SignUpViewModel"
    }

    var mView: SignUpView? = null

    override fun onAttachView(view: BaseView) {
        super.onAttachView(view)
        mView = view as SignUpView
    }

    fun validateForm(name: String, email: String, password: String, rePassword: String) {
        if (name.isEmpty()) {
            mView?.onError("Enter Name")
            return
        }

        if (email.isEmpty()) {
            mView?.onError("Enter email")
            return
        }
        if (password.isEmpty()) {
            mView?.onError("Enter password")
            return
        }
        if (rePassword.isEmpty()) {
            mView?.onError("Enter re-password")
            return
        }

        if (password != rePassword) {
            mView?.onError("Passwords must be same")
            return
        }

        mView?.onFormValidated()
    }
}