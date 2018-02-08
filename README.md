# CirclePointMove
A simple ViewPager indicator.What can it do?Well,it can let the circle point move together with the move of viewpager move.
一个简单的Viewpager指示器，它能实现什么？它能使圆点跟随ViewPager移动
***
[中文文档](http://www.cnblogs.com/kexing/p/8433289.html)
## Picture 
![]( https://github.com/Stars-One/CirclePointMove/raw/master/app/picture/b.gif)

## Usage 
  1. Add the JitPack repository to your build file
  ```	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```  
  2. Add the dependency
  ```		dependencies {
	        compile 'com.github.Stars-One:CirclePointMove:v1.1'
	}

```  
  3.Add CirclePointMove to layout xml file
  ```xml
	<com.wan.movecirclepoint.CirclePoint
       android:id="@+id/circlepoint"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       app:count="6"/>
```  
*Tip:rememebr edit the count*</br>
 4.Initialize CirclePointMove throgth findviewbyid method
  ```java
	mCirclepoint = (CirclePoint) findViewById(R.id.circlepoint);
```  
  5.Add an OnPageerListener for the ViewPager which you want to show</br>
  6.Use the setonPageScrolled of CirclePointMove in the onPageScrolled method of ViewPager
  ```java
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
```  
## Other

property|description|default
| -| - |-:
|size|the size of showing circle point|8dp|
|point_unselected_color|the color of unselected circle point|0|
|point_selected_color|the color of selected circle|0|
|cout|the item of your viewpager|3|
||the item of your viewpager|3|
