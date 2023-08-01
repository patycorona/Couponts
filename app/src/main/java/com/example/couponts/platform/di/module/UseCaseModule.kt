package com.example.couponts.platform.di.module

import com.example.couponts.data.repository.CouponRepositoryImpl
import com.example.couponts.domain.usecase.CouponUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun couponUseCaseProvider(couponRepositoryImpl : CouponRepositoryImpl) =
        CouponUseCase(couponRepositoryImpl)
}