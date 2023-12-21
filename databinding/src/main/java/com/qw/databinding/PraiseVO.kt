package com.qw.databinding

import androidx.databinding.ObservableInt

data class PraiseVO(
    val isPraised: ObservableInt,
    val num: ObservableInt,
    val action: ObservableInt,
) {
    private fun hasPraised(): Boolean {
        return isPraised.get() == 1
    }

    fun toggle() {
        if (hasPraised()) {
            action.set(LottieStateView.ACTION_UN_SELECTED)
        } else {
            action.set(LottieStateView.ACTION_SELECTED)
        }
    }

    fun onSelectChanged(selected: Boolean) {
        if (selected) {
            num.set(num.get() + 1)
            isPraised.set(1)
        } else {
            num.set(num.get() - 1)
            isPraised.set(0)
        }
    }
}