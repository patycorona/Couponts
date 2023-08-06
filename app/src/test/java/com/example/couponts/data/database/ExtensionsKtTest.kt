package com.example.couponts.data.database

import com.example.couponts.R
import com.example.couponts.data.database.entities.CouponEntity
import com.example.couponts.domain.model.Constants.ERROR_EXIST
import com.example.couponts.domain.model.Constants.ERROR_LENGTH
import com.example.couponts.ui.component.getMsgErrorByCode
import com.example.couponts.ui.component.validateTextCode
import org.junit.Assert.*
import org.junit.Test

class ExtensionsKtTest{
    @Test
    fun validateTextCodeSuccessTest(){
        val code = "welcome"
        assertTrue(validateTextCode(code))
    }

    @Test
    fun validateTextCodeEmptyFailTest(){
        val code = ""
        assertFalse(validateTextCode(code))
    }

    @Test
    fun validateTextCodeMinLengthTest(){
        val code = "HOLA"
        assertFalse(validateTextCode(code))
    }

    @Test
    fun validateTextCodeMaxLengthTest(){
        val code = "ESTE ES EL CURSO DE ANDROID"
        val code2 = "HOLA WORLD"
        assertFalse(validateTextCode(code))
        assertTrue(validateTextCode(code2))
    }

    @Test
    fun getMsgErrorByCodeNullTest(){
        val errorCode = null
        val expectedValue = R.string.error_unknow
        val result = getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar un null",expectedValue, result)
    }

    @Test
    fun getMsgErrorByCodeExistTest(){
        val errorCode = ERROR_EXIST
        val expectedValue = R.string.error_unique_code
        val result = getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar un cupón existente",expectedValue, result)
    }

    @Test
    fun getMsgErrorByCodeLengthTest(){
        val errorCode = ERROR_LENGTH
        val expectedValue = R.string.error_invalid_length
        val result = getMsgErrorByCode(errorCode)
        assertEquals("Error al evalua esta prueba",expectedValue, result)
        assertNotEquals("El recurso no existe",-1,result)
    }


    @Test
    fun checkNotNullCuponTest(){
        val cupon  = CouponEntity(code = "ANDROID", description = "cursos $99.9 USD")
        assertNotNull("El cupón no debeíra ser nulo", cupon)
    }

    @Test
    fun checkGroupsSuccessTest(){
        val aNames = arrayOf("Path", "Mario", "Yuli")//valor actual
        val bNames = arrayOf("Path", "Mario", "Yuli")//Valor esperado
        assertArrayEquals("Los arreglos deberían coincidir, revise sus elementos", bNames, aNames )
    }

    @Test
    fun checkNullCuponTest(){
        val cupon  = null
        assertNull("El cupón debeíra ser nulo", cupon)
    }

    @Test
    fun checkGroupsFailTest(){
        val aNames = arrayOf("Pathy", "Mario", "Yuli")//valor actual
        val bNames = arrayOf("Path", "Maria", "Yuli")//Valor esperado
        assertNotEquals("Los arreglos coinciden", bNames, aNames )
    }

}