package myapp.kc.com.self_control;

import java.util.List;
import java.util.Random;

/**
 * Created by kuangcheng on 17/2/4.
 */

public class CurrentData {

    public long totalTime;
    public long currentStartTime;
    public String text;


    public List<String> texts;

    private int mIndex;
    private long indexTime;


    public void changeText() {
        if (texts == null || texts.size() <= 0) {
            return;
        }

        if (indexTime == 0) {
            indexTime = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - indexTime > 1000 * 30) {
            indexTime = System.currentTimeMillis();
            mIndex++;
        }


        if (mIndex >= texts.size()) {
            mIndex = 0;
        }
    }

    public String getText() {

       if (texts == null || texts.size() <= 0) {
           return "";
       }
       return texts.get(mIndex);
    }
}
