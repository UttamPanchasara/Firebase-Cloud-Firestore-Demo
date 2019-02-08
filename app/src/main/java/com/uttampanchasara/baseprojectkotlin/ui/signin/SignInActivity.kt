package com.uttampanchasara.baseprojectkotlin.ui.signin

import com.uttampanchasara.baseprojectkotlin.R
import com.uttampanchasara.baseprojectkotlin.di.component.ActivityComponent
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseActivity
import javax.inject.Inject

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/13/2018
 */
class SignInActivity : BaseActivity(), SignInView {

    @field:Inject
    lateinit var mViewModel: SignInViewModel


    override fun getLayout(): Int {
        return R.layout.activity_sign_in
    }

    override fun injectComponents(mActivityComponent: ActivityComponent) {
        mActivityComponent.inject(this)
    }

    override fun setUp() {
        mViewModel.onAttachView(this)

        loadFragment(false) {
            replace(R.id.container, SignInFragment())
        }
    }
}