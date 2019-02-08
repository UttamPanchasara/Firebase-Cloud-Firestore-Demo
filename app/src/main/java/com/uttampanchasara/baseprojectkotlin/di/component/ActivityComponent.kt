package com.uttampanchasara.baseprojectkotlin.di.component

import com.uttampanchasara.baseprojectkotlin.di.PerActivity
import com.uttampanchasara.baseprojectkotlin.di.module.ActivityModule
import com.uttampanchasara.baseprojectkotlin.ui.dashboard.DashboardActivity
import com.uttampanchasara.baseprojectkotlin.ui.signin.SignInActivity
import com.uttampanchasara.baseprojectkotlin.ui.signin.SignInFragment
import com.uttampanchasara.baseprojectkotlin.ui.signin.SignUpFragment
import com.uttampanchasara.baseprojectkotlin.ui.splash.SplashActivity
import dagger.Component

/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: SignInActivity)

    fun inject(activity: SplashActivity)

    fun inject(activity: DashboardActivity)

    fun inject(signUpFragment: SignUpFragment)
    fun inject(signInFragment: SignInFragment)
}