package com.dietician.mobile.ui.login.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.presentation.viewmodels.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindViewModel(viewModel: LoginViewModel): ViewModel

}