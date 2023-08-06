package com.example.couponts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.couponts.data.database.entities.CouponEntity

@Database(entities = [CouponEntity::class], version = 1)
abstract class CouponDatabase :RoomDatabase() {
    abstract fun couponDao(): CouponDao
    
}