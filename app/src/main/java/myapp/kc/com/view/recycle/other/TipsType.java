package myapp.kc.com.view.recycle.other;

import android.content.Context;

import myapp.kc.com.kuang2016_go.R;


public enum TipsType {

  LOADING(R.layout.tips_loading),
  LOADING_FAILED(R.layout.tips_loading_failed),
  EMPTY(R.layout.tips_empty);

  protected int mLayoutRes;

  TipsType(int layoutRes) {
    this.mLayoutRes = layoutRes;
  }

  protected Tips createTips(Context context) {
    return new Tips(context, mLayoutRes);
  }

}
