package myapp.kc.com.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kc.kuanglibrary.BaseActivity;

/**
 * @author kc create on 11/17/17.
 * @copyright litebrowser
 */
public class PaintShadowActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyClass v = new MyClass(this);
        setContentView(v);
    }



    public static class MyClass extends View {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        public MyClass(Context context) {
            super(context);

            p.setColor(Color.YELLOW);
            LinearGradient linearGradient = new LinearGradient(
                    200, 0, 350, 0,new int[]{Color.RED, Color.YELLOW}, null, LinearGradient.TileMode.CLAMP);
            p.setShader(linearGradient);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawRect(0, 0, 150, 10, p);
        }
    }
}
