package com.example.couponts.platform.di.module

import com.example.couponts.data.database.CouponDao
import com.example.couponts.data.repository.CouponRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun couponCodeRepositoryProvider(couponDao: CouponDao):CouponRepositoryImpl =
        CouponRepositoryImpl(couponDao)

}