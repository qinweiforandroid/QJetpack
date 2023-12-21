package com.qw.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import com.qw.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mLottieStateView.setImageResource(R.drawable.selector_praise)
        binding.mLottieStateView.seLottieRawRes(Pair(R.raw.state_good, R.raw.state_bad))
        val praise = PraiseVO(ObservableInt(0), ObservableInt(100), ObservableInt(1))
        binding.mLottieStateView.setOnClickListener {
            praise.toggle()
        }
        binding.praise = praise
        binding.mLottieStateView.setOnSelectChangedListener(object :
            LottieStateView.OnSelectChangedListener {
            override fun onSelectChanged(isSelected: Boolean) {
                praise.onSelectChanged(isSelected)
            }
        })
    }
}

@BindingAdapter(value = ["lottieStateSelected"])
fun lottieStateSelected(lottieStateView: LottieStateView, selected: Int) {
    lottieStateView.isSelected = selected == 1
}

@BindingAdapter(value = ["switchLottieStateSelected"])
fun switchLottieStateSelected(lottieStateView: LottieStateView, state: Int) {
    when (state) {
        LottieStateView.ACTION_SELECTED -> {
            lottieStateView.switchSelected(true)
        }

        LottieStateView.ACTION_UN_SELECTED -> {
            lottieStateView.switchSelected(false)
        }
    }
}