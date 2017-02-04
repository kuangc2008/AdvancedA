package myapp.kc.com.self_control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuangcheng on 17/2/5.
 */

public class DataManager {

    public DataManager() {

    }

    public CurrentData getAllDatas() {
        CurrentData data = new CurrentData();

        data.texts = new ArrayList<>();
        data.texts.add("到底是你在看小说、新闻； 还是小说、新闻在嘲笑你的自控力");
//        data.texts.add("今天不运动，将来可能就会生病； 跑步不能坚持5公里，那又怎么让女友爽10分钟？");
        data.texts.add("比你优秀的人，还远比你努力，你还每天为手机无限的分神吗？");
        data.texts.add("如果今天不努力，可能就面不上uc，可能就会薪水少几万一年，将来要付出太多太多");

        data.totalTime = 0;

        data.currentStartTime = System.currentTimeMillis();

        return data;
    }
}
