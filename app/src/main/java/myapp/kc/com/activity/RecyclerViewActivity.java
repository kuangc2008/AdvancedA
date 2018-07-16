package myapp.kc.com.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kc.kuanglibrary.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import myapp.kc.com.kuang2016_go.MainActivity;
import myapp.kc.com.kuang2016_go.R;
import myapp.kc.com.view.MyWebView;

/**
 * @author kc create on 7/16/18.
 * @copyright litebrowser
 */
public class RecyclerViewActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView ll = new RecyclerView(this);
        ll.setLayoutManager(new LinearLayoutManager(this));
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

    public static class MainAdapter extends RecyclerView.Adapter {
        private List mAllActivity = new ArrayList();
        private Context mContext = null;

        public MainAdapter(Context context) {
            mContext = context;
            mAllActivity.add(100);
            mAllActivity.addAll(ActivityFinder.getAllActivity(mContext));
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                MyWebView webView = new MyWebView(parent.getContext());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.jianshu.com/p/4564be81a108");
                webView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  10000));
                return new AViewHolder(webView);
            } else if (viewType == 1) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.my_textview, null);
                return new AViewHolder(convertView);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type == 0) {
            } else if (type == 1) {
                View convertView = holder.itemView;
                convertView.setTag(R.id.text1, position);
                final ActivityInfo info = (ActivityInfo) getItem(position);
                TextView listTitleTV = (TextView) convertView.findViewById(R.id.text1);
                listTitleTV.setText(info.loadLabel(mContext.getPackageManager()));
                listTitleTV.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(info.packageName,
                                info.name));
                        mContext.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        public Object getItem(int pos) {
            return mAllActivity.get(pos);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mAllActivity.size();
        }
    }

    public static class AViewHolder extends RecyclerView.ViewHolder {
        public AViewHolder(View itemView) {
            super(itemView);
        }
    }
}


