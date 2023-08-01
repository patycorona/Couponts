package com.example.couponts.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.couponts.R
import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.database.getMsgErrorByCode
import com.example.couponts.domain.usecase.CouponUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CouponViewModel @Inject constructor(
    var couponUseCase:CouponUseCase
): ViewModel() {

    val coupon = MutableLiveData(CouponEntity())

    private val hideKeyboard = MutableLiveData<Boolean>()
    fun hideKeyboard() = hideKeyboard

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackBarMsg() = snackbarMsg

    fun getCouponByCode(){
        coupon.value?.code?.let{ code ->
            viewModelScope.launch {
                hideKeyboard.value = true
                coupon.value=  couponUseCase.getCouponByCode(code) ?:
                CouponEntity(code = code, isActive = false)
            }
        }
    }

    fun saveCoupont(){
        coupon.value?.let { coupontEntity ->
            viewModelScope.launch {
                hideKeyboard.value = true
                try {
                    coupontEntity.isActive = true
                    couponUseCase.saveCoupon(coupontEntity)
                    getCouponByCode()
                    snackbarMsg.value = R.string.msg_save_success
                } catch (e: Exception) {
                    snackbarMsg.value = getMsgErrorByCode(e.message)
                }
            }
        }
    }
}

