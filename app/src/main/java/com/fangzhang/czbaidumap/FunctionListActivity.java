package com.fangzhang.czbaidumap;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baidu.mapapi.SDKInitializer;
import com.fangzhang.czbaidumap.util.Util;

/**
 * Created by Administrator on 2017/11/21.
 */

public class FunctionListActivity extends ListActivity {
    private BroadcastReceiver receiver;

    private ClassAndName[] datas = {
        new ClassAndName("HelloBaiDuMapActivity",HelloBaiDuMapActivity.class)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册SDK的广播接收者监听
        registerSDKCheckReceiver();
        ArrayAdapter<ClassAndName> adapter = new ArrayAdapter(FunctionListActivity.this,android.R.layout.simple_list_item_1,datas);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ClassAndName ca = (ClassAndName) l.getItemAtPosition(position);
        startActivity(new Intent(FunctionListActivity.this,ca.clazz));
    }

    class ClassAndName{
        String functionName;
        Class clazz;
        public ClassAndName(String functionName, Class clazz) {
            this.functionName = functionName;
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return functionName;
        }
    }
    private void registerSDKCheckReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)) {
                    Util.showToast(FunctionListActivity.this,"网络错误");
                } else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)) {
                    Util.showToast(FunctionListActivity.this,"key验证失败");
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        // 监听网络错误
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        // 监听百度地图sdk的key是否正确
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        registerReceiver(receiver, filter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
