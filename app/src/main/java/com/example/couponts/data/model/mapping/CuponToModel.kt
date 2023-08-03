package com.example.couponts.data.model.mapping

import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.domain.model.CoupontResultModel

internal fun CouponEntity.toModel() = CoupontResultModel(
    id = id, code = code, description = description, isActive = isActive)