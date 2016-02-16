package myapp.kc.com.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import myapp.kc.com.kuang2016_go.R;
import myapp.kc.com.view.ImgView;

/**
 * Created by kuangcheng01 on 2016/2/14.
 */
public class ViewMeasureActivity extends Activity {
    private ImgView mImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_measure_layout);

        mImgView = (ImgView) findViewById(R.id.img_view);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.run);
        mImgView.setBitmap(bitmap);
    }
}
