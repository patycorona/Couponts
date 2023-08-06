package com.example.couponts.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CoupontResultModel (
    var id: Long = 0,
    var code:String = "",
    var description:String = "",
    var isActive:Boolean = true
): Parcelable