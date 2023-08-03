package com.example.couponts.domain.usecase

import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.repository.CouponRepository
import javax.inject.Inject

class CouponUseCase @Inject constructor(
    var couponRepository:CouponRepository){

    suspend fun getCouponByCode(code:String): CouponEntity? = couponRepository.getCouponByCode(code)

    suspend fun saveCoupon(couponEntity : CouponEntity) = couponRepository.setCoupon(couponEntity)

}