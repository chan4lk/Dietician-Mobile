package com.dietician.mobile.di

import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.UserData
import com.dietician.data.repository.RemoteDataSource
import com.dietician.mobile.AuthInterceptor
import com.dietician.mobile.BuildConfig
import com.dietician.remote.api.AuthApi
import com.dietician.remote.api.PlanApi
import com.dietician.remote.api.ProfileApi
import com.dietician.remote.mapper.Mapper
import com.dietician.remote.mapper.ProfileMapper
import com.dietician.remote.mapper.ResponseMapper
import com.dietician.remote.mapper.UserMapper
import com.dietician.remote.model.PlanWrapper
import com.dietician.remote.model.Profile
import com.dietician.remote.model.User
import com.dietician.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun providePlanMapper(): Mapper<PlanData, PlanWrapper> = ResponseMapper()

    @Provides
    fun provideUserMapper(): Mapper<UserData, User> = UserMapper()


    @Provides
    fun provideProfileMapper(): Mapper<ProfileData, Profile> = ProfileMapper()

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    fun providePlanApi(retrofit: Retrofit): PlanApi =
        retrofit.create(PlanApi::class.java)

    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)

    @Provides
    fun provideRetrofit(interceptor: AuthInterceptor): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(logger)
                    .build()
            )
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

}