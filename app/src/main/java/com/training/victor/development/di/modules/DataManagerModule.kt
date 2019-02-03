package com.training.victor.development.di.modules

import com.training.victor.development.data.DataManager
import com.training.victor.development.network.ThorFilmsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataManagerModule {
    @Provides
    @Singleton
    fun provideDataManager(profileRepository: ThorFilmsRepository): DataManager = DataManager(profileRepository)
}