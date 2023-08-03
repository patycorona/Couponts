package com.example.couponts.data.repository

import com.example.couponts.data.database.entities.CouponEntity

interface CouponRepository {

  suspend fun getCouponByCode(code:String):CouponEntity?

  suspend fun setCoupon(couponEntity : CouponEntity):Long

}