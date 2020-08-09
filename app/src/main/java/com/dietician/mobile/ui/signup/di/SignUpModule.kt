package com.dietician.mobile.ui.signup.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.presentation.viewmodels.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignUpModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindViewModel(viewModel: SignUpViewModel): ViewModel

}