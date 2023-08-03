package com.example.couponts.ui.home.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.couponts.BR
import com.example.couponts.R
import com.example.couponts.databinding.ActivityMainBinding
import com.example.couponts.ui.component.hideKeyboard
import com.example.couponts.ui.home.viewmodel.CouponViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       setUpViewModel()
       setUpObserver()
    }

    private fun setUpViewModel(){
        val coupontViewModel: CouponViewModel by viewModels()
        binding.lifecycleOwner= this
        binding.setVariable(BR.viewModel,coupontViewModel)
    }

    private fun setUpObserver(){
        binding.viewModel?.let {
            it.coupon.observe(this@MainActivity) { coupon ->
                binding.apply {
                    isActive = coupon != null && coupon.isActive
                }
            }
            it.getSnackBarMsg().observe(this@MainActivity){ msg ->
                Snackbar.make(binding.root,msg,Snackbar.LENGTH_SHORT).show()
            }
            it.hideKeyboard().observe(this@MainActivity) {isHide ->
                if(isHide)hideKeyboard(this@MainActivity, binding.root)
            }
        }
    }
}