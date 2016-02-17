# 1 Activity的setContentView

1 会new出来一个PhoneWinow到Activity中，

2 在PhoneWindow中，setContentView时，会new出来一个contentParentView，并将layoutid添加到其上
contentParentView是DecorView，id为conent

# 2 conentParentView的widthSpec和heightSpec是如何获取的？
1 ViewRootImpl承担控制器重任，如

<pre><code>
    private void performTraversals() {
        if (!mStopped) {
            int childWidthMeasureSpec = getRootMeasureSpec(mWidth, lp.width);
            int childHeightMeasureSpec = getRootMeasureSpec(mHeight, lp.height);
            performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
        }
    }
</code></pre>

<pre><code>
private static int getRootMeasureSpec(int windowSize, int rootDimension) {
    int measureSpec;
    switch (rootDimension) {

    case ViewGroup.LayoutParams.MATCH_PARENT:
    	// Window不能调整其大小，强制使根视图大小与Window一致
        measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.EXACTLY);
        break;
    case ViewGroup.LayoutParams.WRAP_CONTENT:
    	// Window可以调整其大小，为根视图设置一个最大值
        measureSpec = MeasureSpec.makeMeasureSpec(windowSize, MeasureSpec.AT_MOST);
        break;
    default:
    	// Window想要一个确定的尺寸，强制将根视图的尺寸作为其尺寸
        measureSpec = MeasureSpec.makeMeasureSpec(rootDimension, MeasureSpec.EXACTLY);
        break;
    }
    return measureSpec;
}
</code></pre>

所以根视图，永远是全屏的，并且拿到了一个全屏的widthSpec和heightSpec

2 拿到根视图大小，最终会调用View的measure，代码如下：

<pre><code>
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
            getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
}

public static int getDefaultSize(int size, int measureSpec) {
    // 将我们获得的最小值赋给result
    int result = size;

    // 从measureSpec中解算出测量规格的模式和尺寸
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    /*
     * 根据测量规格模式确定最终的测量尺寸
     */
    switch (specMode) {
    case MeasureSpec.UNSPECIFIED:
        result = size;
        break;
    case MeasureSpec.AT_MOST:
    case MeasureSpec.EXACTLY:
        result = specSize;
        break;
    }
    return result;
}
</code></pre>

# 3 一个最重要的问题是：view拿到的widthSpec、heightSpec是如何生成的？

在ViewGroup的measureChildren中，由以下代码得到

    protected void measureChild(View child, int parentWidthMeasureSpec,
            int parentHeightMeasureSpec) {
        // 获取子元素的布局参数
        final LayoutParams lp = child.getLayoutParams();
    
        /*
         * 将父容器的测量规格已经上下和左右的边距还有子元素本身的布局参数传入getChildMeasureSpec方法计算最终测量规格
         */
        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                mPaddingLeft + mPaddingRight, lp.width);
        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
                mPaddingTop + mPaddingBottom, lp.height);
    
        // 调用子元素的measure传入计算好的测量规格
        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

所以实际上由以下方法获得！

    public static int getChildMeasureSpec(int spec, int padding, int childDimension) {
        // 获取父容器的测量模式和尺寸大小
        int specMode = MeasureSpec.getMode(spec);
        int specSize = MeasureSpec.getSize(spec);
    
        // 这个尺寸应该减去内边距的值
        int size = Math.max(0, specSize - padding);
        /*
         * 根据模式判断
         */
        switch (specMode) {
        case MeasureSpec.EXACTLY: // 父容器尺寸大小是一个确定的值
            /*
             * 根据子元素的布局参数判断
             */
            if (childDimension >= 0) { //如果childDimension是一个具体的值
                // 那么就将该值作为结果
                resultSize = childDimension;
    
                // 而这个值也是被确定的
                resultMode = MeasureSpec.EXACTLY;
            } else if (childDimension == LayoutParams.MATCH_PARENT) { //如果子元素的布局参数为MATCH_PARENT
                // 那么就将父容器的大小作为结果
                resultSize = size;
    
                // 因为父容器的大小是被确定的所以子元素大小也是可以被确定的
                resultMode = MeasureSpec.EXACTLY;
            } else if (childDimension == LayoutParams.WRAP_CONTENT) { //如果子元素的布局参数为WRAP_CONTENT
                // 那么就将父容器的大小作为结果
                resultSize = size;
    
                // 但是子元素的大小包裹了其内容后不能超过父容器
                resultMode = MeasureSpec.AT_MOST;
            }
            break;
    
        case MeasureSpec.AT_MOST: // 父容器尺寸大小拥有一个限制值
            /*
             * 根据子元素的布局参数判断
             */
            if (childDimension >= 0) { //如果childDimension是一个具体的值
                // 那么就将该值作为结果
                resultSize = childDimension;
    
                // 而这个值也是被确定的
                resultMode = MeasureSpec.EXACTLY;
            } else if (childDimension == LayoutParams.MATCH_PARENT) { //如果子元素的布局参数为MATCH_PARENT
                // 那么就将父容器的大小作为结果
                resultSize = size;
    
                // 因为父容器的大小是受到限制值的限制所以子元素的大小也应该受到父容器的限制
                resultMode = MeasureSpec.AT_MOST;
            } else if (childDimension == LayoutParams.WRAP_CONTENT) { //如果子元素的布局参数为WRAP_CONTENT
                // 那么就将父容器的大小作为结果
                resultSize = size;
    
                // 但是子元素的大小包裹了其内容后不能超过父容器
                resultMode = MeasureSpec.AT_MOST;
            }
            break;
    
        case MeasureSpec.UNSPECIFIED: // 父容器尺寸大小未受限制
            /*
             * 根据子元素的布局参数判断
             */
            if (childDimension >= 0) { //如果childDimension是一个具体的值
                // 那么就将该值作为结果
                resultSize = childDimension;
    
                // 而这个值也是被确定的
                resultMode = MeasureSpec.EXACTLY;
            } else if (childDimension == LayoutParams.MATCH_PARENT) { //如果子元素的布局参数为MATCH_PARENT
                // 因为父容器的大小不受限制而对子元素来说也可以是任意大小所以不指定也不限制子元素的大小
                resultSize = 0;
                resultMode = MeasureSpec.UNSPECIFIED;
            } else if (childDimension == LayoutParams.WRAP_CONTENT) { //如果子元素的布局参数为WRAP_CONTENT
                // 因为父容器的大小不受限制而对子元素来说也可以是任意大小所以不指定也不限制子元素的大小
                resultSize = 0;
                resultMode = MeasureSpec.UNSPECIFIED;
            }
            break;
        }
    
        // 返回封装后的测量规格
        return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
    }
    
<strong>所以，在线性布局中。 如果中间某个自定义的布局，onMeasure是wrap_conent的话，则会占满整个屏幕.</strong>


# 4 总结 [链接](http://blog.csdn.net/aigestudio/article/details/42989325)
首先是流程上：

    ViewRootImpl.performTraversals   //以全屏姿态绘制decoderView
        customChild.measure              //第一个根节点，则传入父亲的 spec 以及 儿子的layoutParam
            child.measure       //传入的节点，会根据View.getChildMeasureSpec得到最终spec
                child.onMeasure     //ViewGroup的话，由其子类来实现
                    measureChildren     //必定在某个时刻会测量所有儿子
                        循环


# 5 ViewGroup的测量

ViewGroup处理自己的padding、子空间的margin



