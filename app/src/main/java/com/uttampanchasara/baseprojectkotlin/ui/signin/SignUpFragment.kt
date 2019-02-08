package com.uttampanchasara.baseprojectkotlin.ui.signin

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject

class SignUpFragment : BaseFragment(), View.OnClickListener, SignUpView {

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up
    }

    companion object {
        const val TAG = "SignUpFragment"
    }

    @Inject
    lateinit var mViewModel: SignUpViewModel

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    private var mActivity: SignInActivity? = null
    private var mFirebaseInstant: FirebaseFirestore? = null

    private var mName = ""
    private var mEmail = ""
    private var mPassword = ""

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        getActivityComponent()?.inject(this)
        mViewModel.onAttachView(this)

        mActivity = activity as SignInActivity

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        // Access a Cloud Firestore instance from your Activity
        mFirebaseInstant = FirebaseFirestore.getInstance()

        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSignUp -> {
                mName = edtName.text.toString()
                mEmail = edtEmail.text.toString()
                mPassword = edtPassword.text.toString()
                val rePassword = edtRePassword.text.toString()

                mViewModel.validateForm(mName, mEmail, mPassword, rePassword)
            }
        }
    }

    override fun onFormValidated() {
        createAccount(mEmail, mPassword)
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        showLoading()
        mActivity?.let {
            // [START create_user_with_email]
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        hideLoading()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            Log.d(TAG, "createUserWithEmail:success :" + user?.email)

                            sendEmailVerification()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            onError("Authentication failed.")
                        }
                    }
                    .addOnFailureListener {
                        hideLoading()
                        it.printStackTrace()
                        onError("Error while creating User, Try again")
                    }
            // [END create_user_with_email]
        }
    }

    // Send verification email
    private fun sendEmailVerification() {
        showLoading()
        // [START send_email_verification]
        val user = auth.currentUser
        if (mActivity != null && user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener { task ->
                        hideLoading()
                        // [START_EXCLUDE]
                        if (task.isSuccessful) {
                            Log.i(TAG, "Verification email sent to ${user.email} ")
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.exception)
                            //onError("Failed to send verification email.")
                        }

                        addToDatabase()
                        // [END_EXCLUDE]
                    }.addOnFailureListener {
                        hideLoading()
                        addToDatabase()
                    }
        }
        // [END send_email_verification]
    }

    private fun addToDatabase() {
        showLoading()
        mFirebaseInstant?.let {
            val users = it.collection("users")
            // Create a new user with a first and last name
            val user = HashMap<String, Any>()
            user["name"] = mName
            user["email"] = mEmail
            users.document(mEmail)
                    .set(user)
                    .addOnSuccessListener {
                        hideLoading()
                        Log.d(TAG, "DocumentSnapshot added")
                        openSignInFragment()
                    }
                    .addOnFailureListener { e ->
                        hideLoading()
                        Log.w(TAG, "Error adding document", e)
                    }
        }
    }

    private fun openSignInFragment() {
        mActivity?.let {
            it.loadFragment(false) {
                replace(R.id.container, SignInFragment())
            }
        }
    }
}