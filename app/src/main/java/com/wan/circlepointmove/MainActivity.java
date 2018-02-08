package com.wan.circlepointmove;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wan.movecirclepoint.CirclePoint;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewpager;
    private CirclePoint mCirclepoint;
    private Button mButton;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mViewpager.setAdapter(new MyViewPagerAdapter());

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCirclepoint.setonPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mCirclepoint = (CirclePoint) findViewById(R.id.circlepoint);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                break;
            default:break;
        }
    }
}
