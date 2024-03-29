package com.wan.circlepointmove

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.wan.movecirclepoint.CirclePoint
import com.wan.movecirclepoint.setupCirclePoint

class MainActivity : AppCompatActivity() {

    private lateinit var mViewpager: ViewPager
    private lateinit var vp2: ViewPager2

    private lateinit var mCirclepoint: CirclePoint
    private lateinit var mCirclepoint2: CirclePoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        mViewpager.adapter = MyViewPagerAdapter()
        mViewpager.setupCirclePoint(mCirclepoint)


        //设置viewpager2的小圆点指示器
        val textList = (0..5).map { "第 $it 页" }
        vp2.adapter = Vp2Adapter(textList)
        //因为vp2如果采用懒加载方式,无法获取正确页面数量,所以需要传递有个数量参数
        vp2.setupCirclePoint(mCirclepoint2,textList.size)

    }

    private fun initView() {
        mViewpager = findViewById<View>(R.id.viewpager) as ViewPager
        mCirclepoint = findViewById<View>(R.id.circlepoint) as CirclePoint

        vp2 = findViewById<ViewPager2>(R.id.vp2)
        mCirclepoint2 = findViewById<CirclePoint>(R.id.circlepoint2)

    }
}


class Vp2Adapter(val textList: List<String>) : RecyclerView.Adapter<Vp2Adapter.Vp2ViewHolder>() {

    class Vp2ViewHolder(v: View, val textView: TextView) : RecyclerView.ViewHolder(v) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vp2ViewHolder {

        val ll = LinearLayout(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        val tv = TextView(parent.context)
        ll.addView(tv)
        return Vp2ViewHolder(ll, tv)
    }

    override fun getItemCount(): Int {
        return textList.size
    }

    override fun onBindViewHolder(holder: Vp2ViewHolder, position: Int) {
        holder.textView.text = textList[position]
    }
}