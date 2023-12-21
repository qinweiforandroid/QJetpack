package com.qw.databinding

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView

/**
 * 业务需求
 * 1.两张状态图
 * 2.两个切换动效资源图
 */
class LottieStateView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var onSelectChangedListener: OnSelectChangedListener? = null
    private var lottieRawRes: Pair<Int, Int>? = null
    private var lottie: LottieAnimationView
    private var img: ImageView

    companion object {
        const val ACTION_IDLE = 1
        const val ACTION_SELECTED = 2
        const val ACTION_UN_SELECTED = 3
    }

    init {
        img = ImageView(context, attrs)
        img.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(img)
        lottie = LottieAnimationView(context, attrs)
        lottie.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(lottie)
        img.visibility = View.VISIBLE
        lottie.visibility = View.INVISIBLE
    }

    fun setImageResource(res: Int) {
        img.setImageResource(res)
    }

    fun seLottieRawRes(rawRes: Pair<Int, Int>) {
        this.lottieRawRes = rawRes
    }

    override fun setSelected(selected: Boolean) {
        img.visibility = View.VISIBLE
        lottie.visibility = View.INVISIBLE
        img.isSelected = selected
    }

    fun switchSelected(selected: Boolean) {
        lottie.removeAllAnimatorListeners()
        lottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                isSelected = selected
                onSelectChangedListener?.onSelectChanged(selected)
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        if (selected) {
            lottie.setAnimation(lottieRawRes?.first ?: 0)
        } else {
            lottie.setAnimation(lottieRawRes?.second ?: 0)
        }
        lottie.frame = 0
        img.visibility = View.GONE
        lottie.visibility = View.VISIBLE
        lottie.playAnimation()
    }

    fun setOnSelectChangedListener(listener: OnSelectChangedListener) {
        this.onSelectChangedListener = listener
    }

    interface OnSelectChangedListener {
        fun onSelectChanged(isSelected: Boolean)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        img.setOnClickListener(l)
    }
}