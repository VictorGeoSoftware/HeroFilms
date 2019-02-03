package com.training.victor.development.di.components

import com.training.victor.development.ui.main.MainActivity
import com.training.victor.development.ui.detail.ProfileDetailActivity
import com.training.victor.development.di.modules.PresenterModule
import com.training.victor.development.di.scopes.ViewScope
import dagger.Subcomponent

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */

/*
    So, this is a Subcomponent! A component which inherit all dependecies of father component!
    I here we have to implement the custom scope!!
 */
@ViewScope
@Subcomponent(modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(target: MainActivity)
    fun inject(target: ProfileDetailActivity)
}