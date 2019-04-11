package myapp.kc.com.activity

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log

import com.kc.kuanglibrary.BaseActivity
import kotlinx.android.synthetic.main.image_view_bg.*

import myapp.kc.com.kuang2016_go.R

/**
 * @author kc create on 3/28/19.
 * @copyright litebrowser
 */
class ImageViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_view_bg)

        image_view1.setBackgroundResource(R.drawable.run);
        image_view2.setBackgroundResource(R.drawable.run);


        runOnUiThread {
            Log.i("kcc", " first:" + image_view1.background  + "  width " +  image_view1.background.intrinsicWidth  + "  matrix->" + image_view1.background.ma)
            Log.i("kcc", " second:" + image_view2.background  + "  width " +  image_view2.background.intrinsicWidth)

            if (image_view1.background is BitmapDrawable) {
                val b = image_view1.background as BitmapDrawable
                Log.i("kcc", " first bitmap:" + b.bitmap.width + "  height->" + b.bitmap );
            }

            if (image_view2.background is BitmapDrawable) {
                val b = image_view2.background as BitmapDrawable
                Log.i("kcc", " first bitmap:" + b.bitmap.width + " height->" + b.bitmap );
            }
        }
    }
}
