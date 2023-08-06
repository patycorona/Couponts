package com.example.couponts.platform.di.module

import android.content.Context
import androidx.room.Room
import com.example.couponts.data.database.CouponDao
import com.example.couponts.data.database.CouponDatabase
import com.example.couponts.domain.model.Constants.DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    fun providerDao(couponDatabase: CouponDatabase):CouponDao{
       return couponDatabase.couponDao()
    }

    @Provides
    fun providerRoom(@ApplicationContext appContext: Context): CouponDatabase {
        return Room.databaseBuilder(
            appContext ,
            CouponDatabase::class.java,
            DATABASE
        ).build()
    }
}
