package org.xxx.livertmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.avxxx.RendererCommon;
import org.avxxx.api.DyncLivePlayer;
import org.avxxx.core.AvApp;
import org.avxxx.core.PlayHelper;
import org.avxxx.core.VideoView;
import org.xxx.livertmp.R;

/**
 * 拉流播放demo
 * Created by Skyline on 2016/6/24.
 */
public class PullActivity extends AppCompatActivity implements PlayHelper {

    private RelativeLayout rl_videos;
    private TextView txt_staus;
    private VideoView videoView;
    private String mUrl = "rtmp://www.teameeting.cn/live/f001";
    private boolean mStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        {
            rl_videos = (RelativeLayout) findViewById(R.id.rl_videos);
            txt_staus = (TextView) findViewById(R.id.txt_status);

            /**
             * 初始化 VideoView 时
             * RendererCommon.ScalingType.SCALE_ASPECT_FIT（适应屏幕大小填充）,
             * RendererCommon.ScalingType.SCALE_ASPECT_FILL（根据图像大小填充）,
             * RendererCommon.ScalingType.SCALE_ASPECT_BALANCED（平衡FIT和FILL）
             */
            videoView = new VideoView(this, AvApp.Inst().Egl(), 0, 0, 0, 100, 100, RendererCommon.ScalingType.SCALE_ASPECT_FIT);
            rl_videos.addView(videoView.mLayout);
        }
        mUrl = getIntent().getExtras().getString("url");
        DyncLivePlayer.Instance().init(this, videoView, this);
    }

    public void OnBtnClick(View view) {
        Button btn = (Button) view;
        if (btn.getId() == R.id.btn_click) {
            if(mStarted) {
                DyncLivePlayer.Instance().stop();
                finish();
            } else {
                DyncLivePlayer.Instance().play(mUrl);
                ((Button) view).setText("停止");
            }
            mStarted = !mStarted;
        }
    }

    /**
     * Implements for PlayHelper
     */
    @Override
    public void OnRtmplayerOK() {

    }

    @Override
    public void OnRtmplayerStatus(int cacheTime, int curBitrate) {

    }

    @Override
    public void OnRtmplayerCache(int time) {

    }

    @Override
    public void OnRtmplayerClose(int errcode) {

    }
}

