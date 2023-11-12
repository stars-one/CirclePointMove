package com.wan.movecirclepoint

import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

fun ViewPager.setupCirclePoint(circlePoint: CirclePoint) {
    val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            circlePoint.setonPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {}
        override fun onPageScrollStateChanged(state: Int) {}
    }
    addOnPageChangeListener(listener)
}

fun ViewPager2.setupCirclePoint(circlePoint: CirclePoint) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            circlePoint.setonPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    })
}