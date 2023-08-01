package com.example.couponts.data.repository

import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.model.response.ResultResponse

interface CouponRepository {

  suspend fun getCouponByCode(code:String):CouponEntity?

  suspend fun saveCoupon(couponEntity : CouponEntity):Long

}