package com.uttampanchasara.baseprojectkotlin.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseFragment
import com.uttampanchasara.baseprojectkotlin.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class SignInFragment : BaseFragment(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    @Inject
    lateinit var mDataManager: DataManager

    private var mActivity: SignInActivity? = null
    private var mFirebaseInstant: FirebaseFirestore? = null

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    companion object {
        const val TAG = "SignInFragment"
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        getActivityComponent()?.inject(this)
        mActivity = activity as SignInActivity

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        // Access a Cloud Firestore instance from your Activity
        mFirebaseInstant = FirebaseFirestore.getInstance()

        bindViews()
    }

    private fun bindViews() {
        btnLogin.setOnClickListener(this)
        txtSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> doLogin()
            R.id.txtSignUp -> openSignUpFragment()
        }
    }

    private fun openSignUpFragment() {
        mActivity?.let {
            it.loadFragment(false) {
                replace(R.id.container, SignUpFragment())
            }
        }
    }

    private fun doLogin() {

        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()

        when {
            email.isEmpty() -> {
                onError("Enter non empty Email")
            }
            else -> {
                showLoading()
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            hideLoading()
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success")
                                val user = auth.currentUser
                                user?.let {
                                    mDataManager.userName = user.email
                                    mDataManager.setIsSignedIn(true)

                                    navigateToDashboard()
                                }
                            } else {
                                hideLoading()
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                onError("Authentication failed.")
                            }
                        }
            }
        }
    }

    private fun navigateToDashboard() {
        mActivity?.let {
            startActivity(Intent(it, DashboardActivity::class.java))
            it.finish()
        }
    }
}