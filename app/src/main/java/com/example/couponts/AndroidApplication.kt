package com.example.couponts

import android.app.Application
import androidx.room.Room
import com.example.couponts.data.database.CouponDatabase
import com.example.couponts.domain.model.Constants.DATABASE
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApplication : Application() {

    companion object{
       lateinit var database: CouponDatabase
    }

    override fun onCreate(){
        super.onCreate()
        database = Room.databaseBuilder(
            this, CouponDatabase::class.java,
            DATABASE
        ).build()
    }
}