## 为什么要学习CoordinatorLayout？

Toolbar、AppBarLayout、CollapsingToolbarLayout、NestedScrollView。过于诱人。自己现在做的APP毫无动画和美感。作为一个有伟大理想抱负的人儿。决定自己好好学学Android的CoordinatorLayout。方面以后使用到项目中。

## 目前实现的有：

1. 类似于网页云音乐的上滑隐藏图片，改变字体。
2. Toolbar上文字大小转变。
3. webView的滑动隐藏Toolbar，类似于知乎日报。（实现的方式不太优美，如果statusbarColor为transparent，标题会残留在Toolbar上）


## 借鉴的文章有：

1. [AppBar布局的使用方式](http://www.jianshu.com/p/4ce0f3419ca8)
2. [CoordinatorLayout与滚动的处理](http://www.open-open.com/lib/view/open1437312265428.html)
3. [HideOnScrollExample](https://github.com/mzgreen/HideOnScrollExample)
4. 其他的忘记了。


**动态图：**

![image](https://github.com/bigbigpeng3/AppBarPractice/blob/master/art/AppBarPractice.gif)



需要注意的地方：

1. Main2Activity展示的是图片上滑的效果。
	
		//添加一个后退的按钮,太棒了,不过要在manifest中设置一下
 		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 		//不过谷歌不推荐使用这种方式
 		
 		需要在Manifest文件中对Activity的主题设置一下
 		
 		 <activity android:name=".Main2Activity"
                  android:theme="@style/AppTheme.NoActionBar"
                  android:parentActivityName=".MainActivity"
            >
            <meta-data android:name="android.support.PARENT_ACTIVITY"
                       android:value=".MainActivity" />
        </activity>
	
2. WebView上滑隐藏Toolbar的实现方式比较丑陋。

	在values目录中的styles.xml中是
	
		<style name="MyAppTheme" parent="AppTheme">
	        <item name="windowActionBar">false</item>
	        <item name="windowNoTitle">true</item>
	    </style>
	
	没有设置android:statusBarColor为透明的状态
	
		<item name="android:windowTranslucentStatus">false</item>
        <item name="android:windowTranslucentNavigation">true</item>
        <item name="android:statusBarColor">@android:color/transparent</item>
        <item name="android:windowDrawsSystemBarBackgrounds">true</item>
        
    
   如果大家有更优美的方式实现，不介意的话，能写个Demo告诉我吗？
   
## 还想完成的效果

类似于pocket，阅读类APP那样。能够同时隐藏statusbar和ToolBar。实在是没有找到更优美的方式了。如果聪明的你找到了，或者已经实现了。可以给我个Demo或者是源码吗？谢谢！


因为 Talk is Cheap ，Show me the code的原因。我就不贴太多的源码了。

本篇文章的源码在[这里](https://github.com/bigbigpeng3/AppBarPractice)

