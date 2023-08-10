package com.example.couponts.data.Repository

import android.support.v4.media.MediaMetadataCompat.TextKey
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.couponts.data.database.CouponDao
import com.example.couponts.data.database.CouponDatabase
import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.repository.CouponRepositoryImpl
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.plugins.RxJavaPlugins.setIoSchedulerHandler
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException



class CouponRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var  dao: CouponDao

    private lateinit var couponRepositoryImpl: CouponRepositoryImpl
    private lateinit var database:CouponDatabase

     //request
    private val current_code = "Hola02"
    private val current_code_Error = ""

    //lo que me va a result
    private  var couponEntity = CouponEntity()
    private  var couponEntityError = CouponEntity()


    @Before
    suspend fun setupDatabase() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CouponDatabase::class.java
        ).allowMainThreadQueries().build()

        initObjectMock()

        dao = database.couponDao()
    }

//     fun setup() {
//        MockitoAnnotations.initMocks(this)
//        initObjectMock()
//       // initControllers()
//    }


    private fun initObjectMock() {

        couponEntity = CouponEntity(
            0, current_code, "Segundo cupón", false
        )
        couponEntityError = CouponEntity(
            0, current_code_Error, "Error es la primera prueba", true
        ) }


    private suspend fun initControllers() {

        whenever(dao.getCouponByCode(current_code)).thenReturn(
            couponEntity
        )
        whenever(dao.getCouponByCode(current_code_Error)).thenReturn(
            couponEntityError
        )
    }

    private fun initializeViewModel() {
        couponRepositoryImpl = CouponRepositoryImpl(
            dao
        )
    }


    @Test
    fun `When Call GetCouponByCode Return Object CouponResponse`() = runBlocking {

        initializeViewModel()
        initControllers()

        dao.getCouponByCode(current_code)

        Assert.assertEquals(couponEntity.code, current_code)
        Assert.assertEquals(couponEntity.description, "Segundo cupón")
    }


//    @Test
//    suspend fun `When call getCouponByCode return objectCoupon response `() {
//        initializeViewModel()
//        initControllers()
//        couponRepositoryImpl.getCouponByCode(current_code)
//        Assert.assertEquals(couponEntity.code, current_code)
//        Assert.assertEquals(couponEntity.description, "Segundo cupón")
//    }
//
//    @Test
//    suspend fun `When call getCouponByCode return error response `() {
//        initializeViewModel()
//        initControllers()
//        couponRepositoryImpl.getCouponByCode(current_code_Error)
//        //verify(dao, times(1)).getCouponByCode(code)
//        Assert.assertEquals(couponEntityError.code,current_code_Error)
//        Assert.assertEquals(couponEntityError.description, "Error es la primera prueba")
//   }

}