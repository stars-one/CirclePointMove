package com.wan.movecirclepoint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
    private LinearLayout center,pictureLayout;

    private int count;
    private ImageView whitePoint;
    private int mPointMargin;
    private boolean allowCustom;
    private GradientDrawable selected_point,unselected_point,selected_picture,unselected_picture,selected,unselected;
    public CirclePoint(Context context) {
        super(context);
    }

    public CirclePoint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        handlePicture();
        initLayout(context);
        init(context,attrs);

    }

    public void initLayout(Context context){
        centerLayout = new RelativeLayout(context);
        pictureLayout = new LinearLayout(context);
        whitePoint = new ImageView(context);

    }
    public CirclePoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initImage(int count,Context context){
        whitePoint.setImageDrawable(selected);
        // 设置白点的布局参数
        int pointSize = (int)size;
        //生成一个正方形的RelativeLayout，将ImageView设置为一个固定大小正方形（动态改变ImageView的宽高）
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(pointSize, pointSize);
        whitePoint.setLayoutParams(params1);

        for (int i = 0; i < count; i++) {
            // 设置底部小圆点(灰色)
            ImageView point = new ImageView(context);
            point.setImageDrawable(unselected);
            // 设置灰色点的布局参数
            LayoutParams params2 = new LayoutParams(pointSize, pointSize);
            if (i > 0) {
                params2.leftMargin = (int)size;
            }
            point.setLayoutParams(params2);
            pictureLayout.addView(point);

        }
    }

    public void setPoint(){
        whitePoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 此时layout布局已经显示出来了，可以获取小圆点之间的距离了
                mPointMargin = pictureLayout.getChildAt(1).getLeft() - pictureLayout.getChildAt(0).getLeft();
                // 将自己移除掉
                whitePoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    public void setonPageScrolled(int position,float positionOffset, int positionOffsetPixels){
        int leftMargin = (int) (mPointMargin * (position + positionOffset)); // 页面滑动的时候，动态的获取小圆点的左边距
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) whitePoint.getLayoutParams();// 获取布局参数，然后设置布局参数
        params.leftMargin = leftMargin; // 修改参数
        whitePoint.setLayoutParams(params); // 重新设置布局参数
    }
    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CirclePoint);

        size = typedArray.getDimension(R.styleable.CirclePoint_size,8);

        point_selected_color = typedArray.getColor(R.styleable.CirclePoint_point_selected_color,0);
        point_unselected_color = typedArray.getColor(R.styleable.CirclePoint_point_unselected_color,0);
        unselected_drawble_color = typedArray.getColor(R.styleable.CirclePoint_unselected_drawble_color,0);
        selected_drawble_color = typedArray.getColor(R.styleable.CirclePoint_selected_drawble_color,0);

        selected_drawble = typedArray.getDrawable(R.styleable.CirclePoint_selected_drawble);
        unsselected_drawble = typedArray.getDrawable(R.styleable.CirclePoint_unselected_drawble);
        count = typedArray.getInteger(R.styleable.CirclePoint_count,3);
        allowCustom = typedArray.getBoolean(R.styleable.CirclePoint_Allow_custom,false);
        typedArray.recycle();

        setColor();

        centerLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        centerLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        pictureLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pictureLayout.setOrientation(HORIZONTAL);

        initImage(count,context);//初始化圆点的图片
        centerLayout.addView(pictureLayout);
        centerLayout.addView(whitePoint);

        center= new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        center.setLayoutParams(layoutParams);
        center.addView(centerLayout);//添加RelativeLayout
        addView(center);

    }
    private void handlePicture(){
        if(Build.VERSION.SDK_INT<21){
            handlePictureBefore();
        }else {
            handlePictureOn();
        }
    }
    private void setColor(){
        selected = new GradientDrawable();
        unselected = new GradientDrawable();

        if (point_selected_color!=0){
            selected_point.setColor(point_selected_color);
        }
        if(point_unselected_color!=0){
            unselected_point.setColor(point_unselected_color);
        }
        if(selected_drawble_color!=0){
            selected_picture.setColor(selected_drawble_color);
        }
        if(unselected_drawble_color!=0){
            unselected_picture.setColor(unselected_drawble_color);
        }

        if (allowCustom){
            selected = selected_picture;
            unselected = unselected_picture;
        }
        selected = selected_point;
        unselected = unselected_point;

    }
    @TargetApi(21)
    private void handlePictureOn(){
            selected_point= (GradientDrawable) getResources().getDrawable(R.drawable.shape_point_selected,null);
            unselected_point = (GradientDrawable)getResources().getDrawable(R.drawable.shape_point_normal,null);
            selected_picture = (GradientDrawable)selected_drawble;
            unselected_picture = (GradientDrawable)unsselected_drawble;

    }
    private void handlePictureBefore(){
        selected_point= (GradientDrawable) getResources().getDrawable(R.drawable.shape_point_selected);
        unselected_point = (GradientDrawable)getResources().getDrawable(R.drawable.shape_point_normal);
        selected_picture = (GradientDrawable)selected_drawble;
        unselected_picture = (GradientDrawable)unsselected_drawble;
    }
}
