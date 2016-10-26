package com.kc.kuanglibrary;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * Created by chengkuang on 16/10/21.
 */
public class BaseActivity extends Activity {


    private int mAboveKeyboardHeight = 0;

    protected void addGlobalLayoutListener() {
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                try {
                    // 1. Phone.DecorView永远都是全屏的,它的子view是android.R.id.content也是全屏的
                    int rootViewHeight = getWindow().getDecorView().getRootView().getHeight();
                    raiseOnKeyboardVisibilityChanged(rootViewHeight);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            private void raiseOnKeyboardVisibilityChanged(int rootViewHeight) {
                View rootView = getRootView();
                if (!(rootView instanceof ViewGroup)) {
                    return;
                }
                // 2. android.R.id.content是全屏的,但它的子View不一样了
                View content = ((ViewGroup) rootView).getChildAt(0);
                if (content == null) {
                    return;
                }
                int newAboveKeyboardHeight = content.getHeight();
                if (newAboveKeyboardHeight != mAboveKeyboardHeight) {
                    boolean isKeyboardVisible = newAboveKeyboardHeight < rootViewHeight * 2 / 3;
                    boolean isKeyboardPreVisible = mAboveKeyboardHeight == 0 ? false
                            : mAboveKeyboardHeight < rootViewHeight * 2 / 3;
                    if (isKeyboardVisible != isKeyboardPreVisible) {
                        onKeyboardVisibilityChanged(isKeyboardVisible);
                    }
                    mAboveKeyboardHeight = newAboveKeyboardHeight;
                }
            }
        });
    }

    private View getRootView() {
        try {
            return findViewById(android.R.id.content);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }


    protected void onKeyboardVisibilityChanged(boolean isVisible) {

    }
}
