package com.example.couponts.data.repository

import com.example.couponts.data.database.CouponDao
import com.example.couponts.data.database.entities.CouponEntity
import javax.inject.Inject

class CouponRepositoryImpl @Inject constructor(var dao: CouponDao):CouponRepository{

    override suspend fun getCouponByCode(code:String): CouponEntity? = dao.getCouponByCode(code)

    override suspend fun setCoupon(couponEntity : CouponEntity) = dao.addCoupon(couponEntity)

}