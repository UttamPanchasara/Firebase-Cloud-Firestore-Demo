package com.uttampanchasara.baseprojectkotlin.ui.dashboard

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.data.DataManager
import com.uttampanchasara.baseprojectkotlin.di.component.ActivityComponent
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseActivity
import com.uttampanchasara.baseprojectkotlin.ui.signin.SignInFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.lang.Exception
import javax.inject.Inject

class DashboardActivity : BaseActivity(), DashboardView {
    override fun getLayout(): Int {
        return R.layout.activity_dashboard
    }

    @Inject
    lateinit var mDataManager: DataManager

    @Inject
    lateinit var mViewModel: DashboardViewModel
    private var mFirebaseInstant: FirebaseFirestore? = null

    private var mAdapter: UsersListAdapter? = null

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }

    override fun setUp() {
        // Access a Cloud Firestore instance from your Activity
        mFirebaseInstant = FirebaseFirestore.getInstance()

        if (viewFlipper != null) {
            viewFlipper.displayedChild = 0
        }

        mAdapter = UsersListAdapter(this)
        rvUsers.adapter = mAdapter
        rvUsers.layoutManager = LinearLayoutManager(this)

        getUsers()
    }

    private fun getUsers() {
        mFirebaseInstant?.let {

            val docRef = it.collection("users")
            docRef.get()
                    .addOnSuccessListener { documents ->
                        if (documents != null && !documents.isEmpty) {
                            mAdapter?.setUsers(documents.documents)
                            /*for (document in documents) {
                                try {
                                    Log.d(SignInFragment.TAG, "DocumentSnapshot data: " + document.data)
                                    val name = document.getString("name")
                                    onError("User found $name")
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }*/

                            if (viewFlipper != null) {
                                viewFlipper.displayedChild = 1
                            }
                        } else {
                            Log.d(SignInFragment.TAG, "No such document")
                            if (viewFlipper != null) {
                                viewFlipper.displayedChild = 2
                            }
                        }
                    }
        }
    }
}