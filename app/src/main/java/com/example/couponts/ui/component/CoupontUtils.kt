package com.example.couponts.ui.component

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.couponts.R
import com.example.couponts.domain.model.Constants.ERROR_EXIST
import com.example.couponts.domain.model.Constants.ERROR_LENGTH

fun validateTextCode(code: String): Boolean {

    return !(code.length < 5 || code.length > 10)
}

fun getMsgErrorByCode(errorCode: String?): Int = when (errorCode) {

    ERROR_EXIST -> R.string.error_unique_code
    ERROR_LENGTH -> R.string.error_invalid_length
    else -> R.string.error_unknow
}

fun hideKeyboard(contex: Context, view: View) {
    val imm = contex.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {

    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("textTitle")
fun bindTextTitle(view: TextView, isActive: Boolean) {
    if (isActive) {
        view.setText(R.string.txt_title)
        view.setTextColor(Color.GRAY)
    } else {
        view.setText(R.string.txt_title_create)
        view.setTextColor(Color.MAGENTA)
    }
}