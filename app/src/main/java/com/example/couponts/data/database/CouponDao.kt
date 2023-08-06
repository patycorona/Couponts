package com.example.couponts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.couponts.data.database.entities.CouponEntity


@Dao
interface CouponDao {

    @Query( "SELECT * FROM CouponEntity WHERE code = :code")
    suspend fun getCouponByCode(code:String): CouponEntity?

    @Insert
    suspend fun addCoupon(couponEntity: CouponEntity):Long
}