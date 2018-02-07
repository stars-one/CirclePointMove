package com.wan.movecirclepoint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by xen on 2018/2/7 0007.
 */

public class CirclePoint extends LinearLayout {
    private float size;
    private int point_unselected_color,point_selected_color,unselected_drawble_color,selected_drawble_color;
    private Drawable unsselected_drawble,selected_drawble;
    private RelativeLayout centerLayout;
    private LinearLayout left,center,right,pictureLayout;
    private ViewGroup.LayoutParams LinearLayout;

    private int count;
    private ImageView whitePoint;
    public CirclePoint(Context context) {
        super(context);
    }

    public CirclePoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(HORIZONTAL);//设置此Linearlayout为水平放置
        initLayout(context);
        init(context,attrs);
    }

    public void initLayout(Context context){
        centerLayout = new RelativeLayout(context);
        pictureLayout = new LinearLayout(context);
        whitePoint = new ImageView(context);
        whitePoint.setImageResource(R.drawable.shape_point_selected);
    }
    public CirclePoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initImage(int count,Context context){

        // 设置白点的布局参数
        int pointSize = (int)size;
        //生成一个正方形的RelativeLayout，将ImageView设置为一个固定大小正方形（动态改变ImageView的宽高）
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(pointSize, pointSize);
        whitePoint.setLayoutParams(params1);

        for (int i = 0; i < count; i++) {
            // 设置底部小圆点(灰色)
            ImageView point = new ImageView(context);
            point.setImageResource(R.drawable.shape_point_normal);
            // 设置灰色点的布局参数
            LayoutParams params2 = new LayoutParams(pointSize, pointSize);
            if (i > 0) {
                params2.leftMargin = (int)size;
            }
            point.setLayoutParams(params2);
            pictureLayout.addView(point);

        }
    }
    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CirclePoint);

        size = typedArray.getDimension(R.styleable.CirclePoint_size,6);

        point_selected_color = typedArray.getColor(R.styleable.CirclePoint_point_selected_color,0);
        point_unselected_color = typedArray.getColor(R.styleable.CirclePoint_point_unselected_color,0);
        unselected_drawble_color = typedArray.getColor(R.styleable.CirclePoint_unselected_drawble_color,0);
        selected_drawble_color = typedArray.getColor(R.styleable.CirclePoint_selected_drawble_color,0);

        selected_drawble = typedArray.getDrawable(R.styleable.CirclePoint_selected_drawble);
        unsselected_drawble = typedArray.getDrawable(R.styleable.CirclePoint_unselected_drawble);
        count = typedArray.getInteger(R.styleable.CirclePoint_count,3);
        typedArray.recycle();

        left= new LinearLayout(context);//创建一个linearlayout
        //创建一个width为0，height为match_parent,weight为1的LayoutParm（Linearlayout）
        LayoutParams layoutParams =new LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1);
        //将宽高和权重属性赋值给linearlayout
        left.setLayoutParams(layoutParams);

        centerLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        centerLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        pictureLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pictureLayout.setOrientation(HORIZONTAL);

        initImage(count,context);//初始化圆点的图片
        centerLayout.addView(pictureLayout);
        centerLayout.addView(whitePoint);

        center= new LinearLayout(context);
        center.setLayoutParams(layoutParams);
        center.addView(centerLayout);//添加RelativeLayout


        right= new LinearLayout(context);
        left.setLayoutParams(layoutParams);
        //把三个linearlayout添加到当前容器
        addView(left);
        addView(center);
        addView(right);
    }

}
