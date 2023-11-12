package com.wan.movecirclepoint

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * Created by xen on 2018/2/7 0007.
 */
class CirclePoint : LinearLayout {
    private var size = 0f
    private var point_unselected_color = 0
    private var point_selected_color = 0
    private var selected_point: GradientDrawable? = null
    private var unselected_point: GradientDrawable? = null
    private var centerLayout: RelativeLayout? = null
    private var center: LinearLayout? = null
    private var pictureLayout: LinearLayout? = null
    private var count = 0
    private var whitePoint: ImageView? = null
    private var mPointMargin = 0

    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        orientation = VERTICAL
        handlePicture()
        initLayout(context)
        init(context, attrs)
    }

    fun initLayout(context: Context?) {
        centerLayout = RelativeLayout(context)
        pictureLayout = LinearLayout(context)
        whitePoint = ImageView(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun initImage(count: Int, context: Context?) {
        whitePoint!!.setImageDrawable(selected_point)
        // 设置白点的布局参数
        val pointSize = size.toInt()
        //生成一个正方形的RelativeLayout，将ImageView设置为一个固定大小正方形（动态改变ImageView的宽高）
        val params1 = RelativeLayout.LayoutParams(pointSize, pointSize)
        whitePoint!!.layoutParams = params1
        for (i in 0 until count) {
            // 设置底部小圆点(灰色)
            val point = ImageView(context)
            point.setImageDrawable(unselected_point)
            // 设置灰色点的布局参数
            val params2 = LayoutParams(pointSize, pointSize)
            if (i > 0) {
                params2.leftMargin = size.toInt()
            }
            point.layoutParams = params2
            pictureLayout!!.addView(point)
        }
    }

    private fun setPoint() {
        whitePoint!!.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // 此时layout布局已经显示出来了，可以获取小圆点之间的距离了
                mPointMargin = pictureLayout!!.getChildAt(1).left - pictureLayout!!.getChildAt(0).left
                // 将自己移除掉
                whitePoint!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    fun setonPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val leftMargin = (mPointMargin * (position + positionOffset)).toInt() // 页面滑动的时候，动态的获取小圆点的左边距
        val params = whitePoint!!.layoutParams as RelativeLayout.LayoutParams // 获取布局参数，然后设置布局参数
        params.leftMargin = leftMargin // 修改参数
        whitePoint!!.layoutParams = params // 重新设置布局参数
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclePoint)
        size = typedArray.getDimension(R.styleable.CirclePoint_size, 8f)
        point_selected_color = typedArray.getColor(R.styleable.CirclePoint_point_selected_color, 0)
        point_unselected_color = typedArray.getColor(R.styleable.CirclePoint_point_unselected_color, 0)
        count = typedArray.getInteger(R.styleable.CirclePoint_count, 3)
        typedArray.recycle()
        setColor()
        centerLayout!!.layoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        centerLayout!!.gravity = Gravity.CENTER_HORIZONTAL
        pictureLayout!!.layoutParams = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        pictureLayout!!.orientation = HORIZONTAL
        initImage(count, context) //初始化圆点的图片
        centerLayout!!.addView(pictureLayout)
        centerLayout!!.addView(whitePoint)
        center = LinearLayout(context)
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL
        center!!.layoutParams = layoutParams
        center!!.addView(centerLayout) //添加RelativeLayout
        addView(center)
        setPoint()
    }

    private fun handlePicture() {
        if (Build.VERSION.SDK_INT < 21) {
            handlePictureBefore()
        } else {
            handlePictureOn()
        }
    }

    private fun setColor() {
        if (point_selected_color != 0) {
            selected_point!!.setColor(point_selected_color)
        }
        if (point_unselected_color != 0) {
            unselected_point!!.setColor(point_unselected_color)
        }
    }

    @TargetApi(21)
    private fun handlePictureOn() {
        selected_point = resources.getDrawable(R.drawable.shape_point_selected, null) as GradientDrawable
        unselected_point = resources.getDrawable(R.drawable.shape_point_normal, null) as GradientDrawable
    }

    private fun handlePictureBefore() {
        selected_point = resources.getDrawable(R.drawable.shape_point_selected) as GradientDrawable
        unselected_point = resources.getDrawable(R.drawable.shape_point_normal) as GradientDrawable
    }
}