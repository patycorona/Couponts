package com.example.couponts.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.data.repository.CouponRepository
import com.example.couponts.data.repository.CouponRepositoryImpl
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CouponUseCaseTest {

    private lateinit var useCase: CouponUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var couponRepository: CouponRepository

    //request
    private val current_code = "Hola02"
    private val current_code_Error = ""

    //result
    private  var couponEntity = CouponEntity()
    private  var couponEntityError = CouponEntity()

    @Before
    suspend fun setup() {
        MockitoAnnotations.initMocks(this)
        setUpRxSchedulers()
        initObjectMock()
        initControllers()
        initializeViewModel()
    }

    private fun initObjectMock() {
        couponEntity = CouponEntity(
            0, current_code, "Segundo cupón", false
        )
        couponEntityError = CouponEntity(
            0, current_code_Error, "Error es la primera prueba", true
        )
    }
    private fun setUpRxSchedulers() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    private suspend fun initControllers() {
        whenever(couponRepository.getCouponByCode(current_code)).thenReturn(
            couponEntity
        )
        whenever(couponRepository.getCouponByCode(current_code_Error)).thenReturn(
            couponEntityError
        )
    }

    private fun initializeViewModel() {
        useCase = CouponUseCase(
            couponRepository
        )
    }


    @Test
    suspend fun `When call getCouponByCode return objectCoupon response `() {
        initializeViewModel()
        useCase.getCouponByCode(current_code)
        Assert.assertEquals(couponEntity.code, current_code)
        Assert.assertEquals(couponEntity.description, "Segundo cupón")
    }

    @Test
    suspend fun `When call getCouponByCode return error response `() {
        initializeViewModel()
        useCase.getCouponByCode(current_code_Error)
        //verify(dao, times(1)).getCouponByCode(code)
        Assert.assertEquals(couponEntityError.code,current_code_Error)
        Assert.assertEquals(couponEntityError.description, "Error es la primera prueba")
    }

}