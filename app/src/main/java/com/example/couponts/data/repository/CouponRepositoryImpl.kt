package com.example.couponts.data.repository

import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.network.RoomDataBase
import javax.inject.Inject

class CouponRepositoryImpl @Inject constructor(var roomDataBase: RoomDataBase):CouponRepository{

    override suspend fun getCouponByCode(code:String): CouponEntity? = roomDataBase.getCouponByCode(code)

    override suspend fun setCoupon(couponEntity : CouponEntity) = roomDataBase.saveCoupon(couponEntity)

}