# CirclePointMove

A simple ViewPager indicator.What can it do?Well,it can let the circle point move together with the move of viewpager move(support ViewPaper and ViewPaper2)

一个简单的Viewpager指示器，它能实现什么？它能使圆点跟随ViewPager移动(兼容Viewpager和Viewpager2)

<img src="https://jitpack.io/v/stars-one/CirclePointMove.svg" />

## Picture 
![]( https://github.com/Stars-One/CirclePointMove/raw/master/app/picture/b.gif)

## Usage 
  
1.添加Jitpack仓库源

```	
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```  

2.添加依赖

```
dependencies {
    implementation 'com.github.stars-One:CirclePointMove:最新版本'
}
```  

3.xml布局文件使用CirclePointMove
```xml
<com.wan.movecirclepoint.CirclePoint
        android:id="@+id/circlepoint"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:point_selected_color="@color/colorAccent"
        app:point_unselected_color="@color/colorPrimary"
        app:size="8dp" />
```

4. 安装圆点指示器

```kotlin
val mCirclepoint = findViewById<View>(R.id.circlepoint) as CirclePoint

//setupCirclePoint是本库给ViewPager和ViewPager增加的扩展方法,调用传入即可
mViewpager.setupCirclePoint(mCirclepoint)

//因为vp2如果采用懒加载方式,无法获取正确页面数量,所以需要传递有个数量参数
//数量和你vp2里面的页面一致即可,示例代码只是随便写的
vp2.setupCirclePoint(mCirclepoint,6)
```

**旧版本方法记录:**

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
|size					|圆点的大小(宽高)		|8dp	|
|point_unselected_color	|圆点未选择颜色(默认是黑色)	|0		|
|point_selected_color	|圆点选择的颜色(默认是白色)		|0		|
|count					|圆点数量		|3		|



