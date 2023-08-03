package com.example.couponts.data.repository

import android.content.Context
import androidx.room.Room
import com.example.couponts.data.database.CouponDatabase
import com.example.couponts.data.network.RoomDataBase
import com.example.couponts.domain.model.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object RoomModule {

    lateinit var database: CouponDatabase

    @Provides
    fun provideRoom(): CouponDatabase{

        database = Room.databaseBuilder(
            this as Context , CouponDatabase::class.java,
            Constants.DATABASE
        ).build()
        return database
    }
}