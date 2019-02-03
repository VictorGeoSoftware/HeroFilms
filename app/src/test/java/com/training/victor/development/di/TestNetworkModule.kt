package com.training.victor.development.di

import com.training.victor.development.di.modules.NetworkModule
import com.training.victor.development.network.ThorFilmsRepository
import org.mockito.Mockito
import retrofit2.Retrofit

class TestNetworkModule: NetworkModule() {
    override fun provideProfileRetrofit(retrofit: Retrofit): ThorFilmsRepository {
        return super.provideProfileRetrofit(retrofit)
//        return Mockito.mock(ThorFilmsRepository::class.java)
    }
}