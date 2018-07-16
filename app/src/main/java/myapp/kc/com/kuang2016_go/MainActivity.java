package myapp.kc.com.kuang2016_go;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import myapp.kc.com.activity.ActivityFinder;
import myapp.kc.com.view.MyWebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView ll = new ListView(this);
        MyWebView webView = new MyWebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.jianshu.com/p/4564be81a108");
        webView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  3000));
        ll.addHeaderView(webView);
        ll.setAdapter(new MainAdapter(this));
        setContentView(ll);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static class MainAdapter extends BaseAdapter {
        private List<ActivityInfo> mAllActivity = null;
        private Context mContext = null;

        public MainAdapter(Context context) {
            mContext = context;
            mAllActivity = ActivityFinder.getAllActivity(mContext);
        }

        @Override
        public int getCount() {
            return mAllActivity != null ? mAllActivity.size() : 0;
        }

        @Override
        public ActivityInfo getItem(int position) {
            return mAllActivity.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.my_textview, null);
                holder.listTitleTV = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            convertView.setTag(R.id.text1, position);
            final ActivityInfo info = getItem(position);
            holder.listTitleTV.setText(info.loadLabel(mContext.getPackageManager()));
            holder.listTitleTV.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName(info.packageName,
                            info.name));
                    mContext.startActivity(intent);
                }
            });
            return convertView;
        }
    }

    public static class ViewHolder {
        TextView listTitleTV;
    }
}
