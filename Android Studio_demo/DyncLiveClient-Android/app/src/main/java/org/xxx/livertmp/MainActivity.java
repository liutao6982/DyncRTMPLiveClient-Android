package org.xxx.livertmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void OnBtnClick(View view) {
        Button btn = (Button) view;
        if(btn.getId() == R.id.btn_push) {
            Intent it = new Intent(this, PushActivity.class);
            Bundle bd = new Bundle();
            bd.putString("url", "rtmp://www.teameeting.cn/live/f001");
//            bd.putString("url", "rtmp://192.168.7.207:1935/live1/room");
            it.putExtras(bd);
            startActivity(it);
        } else if(btn.getId() == R.id.btn_pull) {
            Intent it = new Intent(this, PullActivity.class);
            Bundle bd = new Bundle();
//            bd.putString("url", "rtmp://live.hkstv.hk.lxdns.com/live/hks");
//            bd.putString("url", "rtmp://192.168.7.207:1935/live1/room");
            bd.putString("url", "rtmp://www.teameeting.cn/live/f001");
            it.putExtras(bd);
            startActivity(it);
        }
    }
}
