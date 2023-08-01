package com.example.couponts.platform.di.component

import com.example.couponts.platform.di.module.RepositoryModule
import com.example.couponts.platform.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class
    ]
)

interface MainComponent {

}