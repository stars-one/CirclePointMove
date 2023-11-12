# CirclePointMove

A simple ViewPager indicator.What can it do?Well,it can let the circle point move together with the move of viewpager move(support ViewPaper and ViewPaper2)

一个简单的Viewpager指示器，它能实现什么？它能使圆点跟随ViewPager移动(兼容Viewpager和Viewpager2)

- [新中文文档](https://github.com/stars-one/CirclePointMove/blob/master/README_zh.md)

- [中文文档](http://www.cnblogs.com/kexing/p/8433289.html)

<img src="https://jitpack.io/v/stars-one/CirclePointMove.svg" />

## Picture 
![]( https://github.com/Stars-One/CirclePointMove/raw/master/app/picture/b.gif)

## Usage 
  
1.Add the JitPack repository to your build file

```	
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```  

2.Add the dependency

```
dependencies {
    implementation 'com.github.Stars-One:CirclePointMove:v1.1'
}
```  

3.Add CirclePointMove to layout xml file
```xml
<com.wan.movecirclepoint.CirclePoint
        android:id="@+id/circlepoint"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:point_selected_color="@color/colorAccent"
        app:point_unselected_color="@color/colorPrimary"
        app:size="8dp" />
```

4. setup the circle point in your viewpaper or viewpaper2

```kotlin
val mCirclepoint = findViewById<View>(R.id.circlepoint) as CirclePoint

//call the extend method of ViewPager or ViewPager which named 'setupCirclePoint'
 mViewpager.setupCirclePoint(mCirclepoint)

//note the second param ,it is the same as the page count of your viewpaper2
vp2.setupCirclePoint(mCirclepoint2,6)
```

**Old way:**

```kotlin

//Old way For ViewPager
val mCirclepoint = findViewById<View>(R.id.circlepoint) as CirclePoint

mViewpager.addOnPageChangeListener(object : OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //note this
        mCirclepoint.setonPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {}
    override fun onPageScrollStateChanged(state: Int) {}
})


//Old way For ViewPager2:
val mCirclepoint = findViewById<View>(R.id.circlepoint) as CirclePoint

vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //note this
        mCirclepoint.setonPageScrolled(position, positionOffset, positionOffsetPixels)
    }
})
```

## view properties

|property				|description							|default|
|--						|--										|--		|
|size					|the size of showing circle point		|8dp	|
|point_unselected_color	|the color of unselected circle point	|0		|
|point_selected_color	|the color of selected circle			|0		|
|cout					|the item of your viewpager				|3		|



