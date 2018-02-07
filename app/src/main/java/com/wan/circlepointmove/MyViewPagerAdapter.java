package com.wan.circlepointmove;

import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xen on 2018/2/8 0008.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 6;//在Viewpager显示3个页面
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    //设置每一页显示的内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView tv = new TextView(container.getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);
        tv.setText("第" + position + "页");

        // 添加到ViewPager容器
        container.addView(tv);

        // 返回填充的View对象
        return tv;


    }
    // 销毁条目对象
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
