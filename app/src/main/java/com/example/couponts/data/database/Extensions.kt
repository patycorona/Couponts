package com.example.couponts.data.database

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.couponts.R
import com.example.couponts.domain.model.Constants.ERROR_EXIST
import com.example.couponts.domain.model.Constants.ERROR_LENGTH


fun validateTextCode(code:String):Boolean {

    return !(code.length <5 || code.length > 10)
}

fun getMsgErrorByCode(errorCode:String?):Int = when(errorCode){

    ERROR_EXIST -> R.string.error_unique_code
    ERROR_LENGTH -> R.string.error_invalid_length
    else -> R.string.error_unknow
}

fun hideKeyboard(context: Context, view: View){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}