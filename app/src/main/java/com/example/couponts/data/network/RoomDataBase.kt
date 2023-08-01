package com.example.couponts.data.network

import android.database.sqlite.SQLiteConstraintException
import com.example.couponts.AndroidApplication
import com.example.couponts.data.database.CouponDao
import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.model.response.ResultResponse
import com.example.couponts.domain.model.Constants.ERROR_EXIST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDataBase @Inject constructor() {

    private val dao: CouponDao by lazy {
        AndroidApplication.database.couponDao()
    }

    //private val roomDataBase = RoomDataBase()


    suspend fun getCouponByCode(code:String): CouponEntity? = dao.getCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity) = withContext(Dispatchers.IO){
        //var resultResponse = ResultResponse()
        try {
            dao.addCoupon(couponEntity)
             //resultResponse = ResultResponse("0",true)
        } catch (e: Exception) {
          //  resultResponse = ResultResponse("-1",false)
            (e as? SQLiteConstraintException)?.let { throw Exception(ERROR_EXIST) }
            throw Exception(e.message ?: ERROR_EXIST)
        }
        //return@withContext resultResponse
    }
}