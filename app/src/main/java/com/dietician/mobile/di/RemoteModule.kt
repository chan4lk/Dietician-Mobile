package com.dietician.mobile.di

import com.dietician.data.repository.RemoteDataSource
import com.dietician.mobile.AuthInterceptor
import com.dietician.mobile.BuildConfig
import com.dietician.remote.api.AuthApi
import com.dietician.remote.api.PlanApi
import com.dietician.remote.mapper.ResponseMapper
import com.dietician.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {
    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataSource


    }

    @Provides
    fun provideMapper(): ResponseMapper =
        ResponseMapper()

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    fun providePlanApi(retrofit: Retrofit): PlanApi =
        retrofit.create(PlanApi::class.java)

    @Provides
    fun provideRetrofit(interceptor: AuthInterceptor): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .baseUrl(BuildConfig.BASE_URL)
            .build()
}