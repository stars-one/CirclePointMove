package com.wan.movecirclepoint

import android.util.Log
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

fun ViewPager.setupCirclePoint(circlePoint: CirclePoint) {
    //读取viewpager的页面数量来设置圆点数量
    val pageCount = this.adapter?.count?:0
    if (pageCount == 0) {
        Log.e("CirclePoint", context.getString(R.string.install_failed_tip_vp) )
        return
    }
    circlePoint.setCount(pageCount)

    val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            circlePoint.setonPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {}
        override fun onPageScrollStateChanged(state: Int) {}
    }
    addOnPageChangeListener(listener)
}

fun ViewPager2.setupCirclePoint(circlePoint: CirclePoint,pageCount:Int) {
    //读取viewpager2的页面数量来设置圆点数量
    //val pageCount = this.adapter?.itemCount
    //if (pageCount == 0) {
    //    Log.e("CirclePoint", context.getString(R.string.install_failed_tip_vp2) )
    //    return
    //}
    circlePoint.setCount(pageCount)

    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            circlePoint.setonPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    })
}