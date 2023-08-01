package com.example.couponts.ui.component

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.couponts.R

@BindingAdapter("isGone")
fun bindIsGone(view: View,isGone:Boolean){

    view.visibility = if(isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("textTitle")
fun bindTextTitle(view:TextView, isActive:Boolean){
    if(isActive){
        view.setText(R.string.txt_title)
        view.setTextColor(Color.GRAY)
    }else{
        view.setText(R.string.txt_title_create)
        view.setTextColor(Color.MAGENTA)
    }
}