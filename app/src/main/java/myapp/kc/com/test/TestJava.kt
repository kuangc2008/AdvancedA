package myapp.kc.com.test

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * @author kc create on 3/28/19.
 * @copyright litebrowser
 */
class TestJava {

    fun a() {
        val a: Drawable? = null
        if (a is BitmapDrawable) {
            val b = a as BitmapDrawable?
            val c = b!!.bitmap
        }
    }
}
