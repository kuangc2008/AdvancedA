# 1 触控传递
在ViewGroup.dispatchToiuchEvent中的处理

           //对于mFirstTouchTarget，如果有子child在down时，return true了，则就有控件了.
           if (actionMasked == MotionEvent.ACTION_DOWN || mFirstTouchTarget != null) {
                if (!disallowIntercept) {
                    intercepted = onInterceptTouchEvent(ev);
                } else {
                    intercepted = false;
                }
            } else {
                // There are no touch targets and this action is not an initial down
                // so this view group continues to intercept touches.
                intercepted = true;
            }
            if (!canceled && !intercepted) {
                // 子控件的处理逻辑
                // 如果有子控件return true，则mFirstTouchTarget != null了
            }
            .....
            final boolean cancelChild = resetCancelNextUpFlag(target.child) || intercepted;
            // 自己的onTouchEvent事件
            if (dispatchTransformedTouchEvent(ev, cancelChild,
                    target.child, target.pointerIdBits)) {
            }
            
  <br><br><br>          

* 1 如果没有down时，任何子控件return true，则intercepted为true，后续就不再处理了
   如果在move或up时，子控件的onTouchEvent中return true，则无作用了
   
        02-25 09:37:41.748 19156 19156 I kcc     : activity dispatchTouchEvent 0
        02-25 09:37:41.748 19156 19156 W kcc     :  TestTouchFrameLayoutu dispatchTouchEvent 0
        02-25 09:37:41.748 19156 19156 W kcc     :  TestTouchFrameLayoutu onInterceptTouchEvent 0
        02-25 09:37:41.748 19156 19156 I kcc     : up  onTouchEvent0
        02-25 09:37:41.748 19156 19156 I kcc     : up  onTouchEvent0
        02-25 09:37:41.748 19156 19156 I kcc     : below  onTouchEvent0
        02-25 09:37:41.748 19156 19156 I kcc     : below  onTouchEvent0
        02-25 09:37:41.748 19156 19156 W kcc     :  TestTouchFrameLayoutu onTouchEvent 0
        02-25 09:37:41.748 19156 19156 I kcc     : activity onTouch 0
        02-25 09:37:42.980 19156 19156 I kcc     : activity dispatchTouchEvent 2
        02-25 09:37:42.980 19156 19156 I kcc     : activity onTouch 2

<Br>
<br>

* 2 如果down时，第二个控件return true，则后续还会传给子控件;  但是是直接传递，兄弟已收不到事件了

        02-25 10:16:37.080 21956 21956 I kcc     : activity dispatchTouchEvent 0
        02-25 10:16:37.080 21956 21956 W kcc     :  TestTouchFrameLayoutu dispatchTouchEvent 0
        02-25 10:16:37.080 21956 21956 W kcc     :  TestTouchFrameLayoutu onInterceptTouchEvent 0
        02-25 10:16:37.080 21956 21956 I kcc     : up  onTouchEvent0
        02-25 10:16:37.080 21956 21956 I kcc     : up  onTouchEvent0
        02-25 10:16:37.080 21956 21956 I kcc     : below  onTouchEvent0
        02-25 10:16:37.080 21956 21956 I kcc     : below  onTouchEvent0
        02-25 10:16:38.648 21956 21956 I kcc     : activity dispatchTouchEvent 2
        02-25 10:16:38.648 21956 21956 W kcc     :  TestTouchFrameLayoutu dispatchTouchEvent 2
        02-25 10:16:38.648 21956 21956 W kcc     :  TestTouchFrameLayoutu onInterceptTouchEvent 2
        02-25 10:16:38.648 21956 21956 I kcc     : below  onTouchEvent2
        02-25 10:16:38.648 21956 21956 I kcc     : below  onTouchEvent2
        02-25 10:16:38.648 21956 21956 I kcc     : activity onTouch 2


<br>
<br>
* 3 如果在move时，被截断呢，则怎么处理？ 
   被截断时，正在触控的view会收到cancle事件，同时viewgroup会开始onTouch事件的获取

        02-25 10:24:53.600 28573 28573 I kcc     : activity dispatchTouchEvent 2
        02-25 10:24:53.604 28573 28573 W kcc     :  TestTouchFrameLayoutu dispatchTouchEvent 2
        02-25 10:24:53.604 28573 28573 W kcc     :  TestTouchFrameLayoutu onInterceptTouchEvent 2
        02-25 10:24:53.604 28573 28573 I kcc     : below  onTouchEvent2
        02-25 10:24:53.604 28573 28573 I kcc     : below  onTouchEvent2
        02-25 10:24:53.604 28573 28573 I kcc     : activity onTouch 2
        02-25 10:24:53.620 28573 28573 I kcc     : activity dispatchTouchEvent 2
        02-25 10:24:53.620 28573 28573 W kcc     :  TestTouchFrameLayoutu dispatchTouchEvent 2
        02-25 10:24:53.620 28573 28573 W kcc     :  TestTouchFrameLayoutu onInterceptTouchEvent 2
        02-25 10:24:53.620 28573 28573 I kcc     : below  onTouchEvent3
        02-25 10:24:53.620 28573 28573 I kcc     : below  onTouchEvent3
        02-25 10:24:53.620 28573 28573 I kcc     : activity onTouch 2
        02-25 10:24:53.632 28573 28573 I kcc     : activity dispatchTouchEvent 2
        02-25 10:24:53.632 28573 28573 W kcc     :  TestTouchFrameLayoutu dispatchTouchEvent 2
        02-25 10:24:53.632 28573 28573 W kcc     :  TestTouchFrameLayoutu onTouchEvent 2
        02-25 10:24:53.632 28573 28573 I kcc     : activity onTouch 2





